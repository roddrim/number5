/*
 * Copyright 2017 roddrim.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.roddrim.number5.web.selenium;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class SeleniumWebDriverBot implements WebBot {

    private final Supplier<WebDriver> driverSupplier;
    @Getter
    private final Pair<Long, TimeUnit> defaultTimeoutInSeconds;
    @Getter
    private final Pair<Long, TimeUnit> defaultWaitOnAction;

    private boolean started = false;

    @Getter
    private WebDriver driver;
    private WebDriverWait wait;
    private Function<Locate, By> translate;
    private By current;

    @Override
    public void startBrowser() {
        if (!started) {
            this.driver = driverSupplier.get();
            this.wait = new WebDriverWait(driver, defaultTimeoutInSeconds.getValue().toSeconds(defaultTimeoutInSeconds.getKey()));
            this.translate = translate(driver);
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
    public String getText() {
        return driver.findElement(current).getText();
    }

    @Override
    public String getTagName() {
        return driver.findElement(current).getTagName();
    }

    @Override
    public String getAttribute(@NonNull final String attributeName) {
        return driver.findElement(current).getAttribute(attributeName);
    }

    @Override
    public List<String> getTexts(final Locate locate) {
        final List<WebElement> l = driver.findElements(translate.apply(locate));
        return l.stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAttributeValues(final Locate locate, final String attributeName) {
        final List<WebElement> l = driver.findElements(translate.apply(locate));
        return l.stream().map(e -> e.getAttribute(attributeName)).collect(Collectors.toList());
    }

    @Override
    public List<String> getTagNames(final Locate locate) {
        final List<WebElement> l = driver.findElements(translate.apply(locate));
        return l.stream().map(e -> e.getTagName()).collect(Collectors.toList());
    }

    @Override
    public void target(@NonNull Locate locate) {
        this.current = translate.apply(locate);
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
        this.current = null;
        this.translate = null;
        this.wait = null;
    }

    private Function<Locate, By> translate(final WebDriver driver) {

        return locate -> {

            final String representation = locate.getRepresentation();

            switch (locate.getType()) {
                case CSS_SELECTOR:
                    return By.cssSelector(representation);
                case ID:
                    return By.id(representation);
                case LINK_TEXT:
                    return By.xpath("//a[text()='" + representation + "']");
                case XPATH:
                    return By.xpath(representation);
                case LABEL:
                    return By.id(driver.findElement(By.xpath("//label[text()='" + representation + "']")).getAttribute("for"));
            }
            throw new IllegalArgumentException(locate.getType() + " not implemented.");
        };

    }


}