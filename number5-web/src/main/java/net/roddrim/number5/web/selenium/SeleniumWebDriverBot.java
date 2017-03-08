package net.roddrim.number5.web.selenium;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.roddrim.number5.tools.lang.Pair;
import net.roddrim.number5.web.api.Locate;
import net.roddrim.number5.web.api.WebBot;
import net.roddrim.number5.web.selenium.ui.CustomConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Log4j2
public class SeleniumWebDriverBot implements WebBot {

    private final long defaultTimeoutInSeconds;
    private final Supplier<WebDriver> driverSupplier;

    private boolean started = false;
    private WebDriver driver;
    private WebDriverWait wait;
    private By current;

    @Getter
    @Setter
    private Pair<Long, TimeUnit> defaultWaitOnAction;

    public SeleniumWebDriverBot(final Supplier<WebDriver> driver, final long defaultTimeoutInSeconds) {
        this.driverSupplier = driver;
        this.defaultTimeoutInSeconds = defaultTimeoutInSeconds;
    }

    @Override
    public void startBrowser() {
        if (!started) {
            this.driver = driverSupplier.get();
            this.wait = new WebDriverWait(driver, defaultTimeoutInSeconds);
            this.started = true;
        }
    }

    @Override
    public void getPage(@NonNull final Object page) {
        driver.get(page.toString());
    }

    @Override
    public void getPage(@NonNull final Object... parts) {
        final StringBuilder page = new StringBuilder();
        for (final Object o : parts) {
            final String s = (o == null) ? "" : o.toString();
            page.append(s);
        }
        getPage((Object) page);
    }

    @Override
    public void waitFor(final long amount, @NonNull final TimeUnit unit) {
        if (amount > 0) {
            wait.until(CustomConditions.waitFor(amount, unit));
        }
    }

    @Override
    public void waitForElementToBeClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(current));
    }

    @Override
    public void waitForElementToBePresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(current));
    }

    @Override
    public void click() {
        driver.findElement(current).click();
    }

    @Override
    public void clear() {
        driver.findElement(current).clear();
    }

    @Override
    public void sendText(@NonNull final CharSequence text) {
        driver.findElement(current).sendKeys(text);
    }

    @Override
    public String getTextValue() {
        return driver.findElement(current).getText();
    }

    @Override
    public String getAttributeValue(@NonNull final String attributeName) {
        return driver.findElement(current).getAttribute(attributeName);
    }


    @Override
    public void target(@NonNull Locate locate) {

        final String representation = locate.getRepresentation();

        switch (locate.getType()) {
            case CSS_SELECTOR:
                elementByCssSelector(representation);
                return;
            case ID:
                elementById(representation);
                return;
            case LINK_TEXT:
                elementByLinkText(representation);
                return;
            case XPATH:
                elementByXPath(representation);
                return;
            case LABEL:
                elementByLabel(representation);
                return;
        }
        throw new IllegalArgumentException(locate.getType() + " not implemented.");
    }

    private void elementById(final String id) {
        this.current = By.id(id);

    }

    private void elementByLabel(final String label) {
        elementByLabel(label, null);
    }

    private void elementByLabel(final String label, final String suffix) {
        final String id = driver.findElement(By.xpath("//label[text()='" + label + "']")).getAttribute("for") + (Strings.isNullOrEmpty(suffix) ? "" : suffix);
        this.current = By.id(id);

    }

    private void elementByXPath(final String xpath) {
        this.current = By.xpath(xpath);
    }

    private void elementByLinkText(final String linkText) {
        this.current = By.xpath("//a[text()='" + linkText + "']");
    }

    private void elementByCssSelector(final String css) {
        this.current = By.cssSelector(css);
    }


    @Override
    public void select(@NonNull final String text) {
        new Select(driver.findElement(current)).selectByVisibleText(text);
    }

    @Override
    public void switchToFrame() {
        driver.switchTo().frame(driver.findElement(current));
    }

    @Override
    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();

    }

    @Override
    public Optional<File> takeScreenShot() {
        if (driver instanceof TakesScreenshot) {
            final TakesScreenshot ts = (TakesScreenshot) driver;
            try {
                final File tmpFile = File.createTempFile("screenshot", ".png");
                Files.copy(ts.getScreenshotAs(OutputType.FILE).toPath(), tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return Optional.of(tmpFile);
            } catch (final IOException | WebDriverException e) {
                log.error("An exception occurred when trying to take a screen-shot:" + System.getProperty("line.separator") + e);
                return Optional.empty();
            }
        } else {
            log.warn("Screenshot functionality not available.");
            return Optional.empty();
        }
    }

    @Override
    public void endBrowser() {
        driver.quit();
        this.started = false;
    }

}