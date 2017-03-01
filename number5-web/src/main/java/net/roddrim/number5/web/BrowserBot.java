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
package net.roddrim.number5.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Supplier;

import net.roddrim.number5.web.ui.CustomConditions;

public class BrowserBot {

    private final long defaultTimeoutInSeconds;
    private final Supplier<WebDriver> driverSupplier;

    private boolean started = false;
    private WebDriver driver;
    private WebDriverWait wait;
    private By current;
    private int defaultWaitOnAction;

    public BrowserBot(final Supplier<WebDriver> driver, final long defaultTimeoutInSeconds) {
        this.driverSupplier = driver;
        this.defaultTimeoutInSeconds = defaultTimeoutInSeconds;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void defaultWaitOnAction(final int defaultWaitOnAction) {
        this.defaultWaitOnAction = defaultWaitOnAction;
    }

    public void start() {
        if (!started) {
            this.driver = driverSupplier.get();
            this.wait = new WebDriverWait(driver, defaultTimeoutInSeconds);
            this.started = true;
        }
    }

    public void get(final String page) {
        driver.get(page);
    }

    public void waitFor(final long seconds) {
        if (seconds > 0) {
            wait.until(CustomConditions.waitForSeconds(seconds));
        }
    }

    public void waitForElementToBeClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(current));
    }

    public void clickAndWait() {
        waitForElementToBeClickable();
        click();
        waitFor(defaultWaitOnAction);
    }

    public void click() {
        driver.findElement(current).click();
    }

    public void clear() {
        driver.findElement(current).clear();
    }

    public void clearSendTextAndWait(final String text) {
        waitForElementToBeClickable();
        clear();
        sendText(text);
        waitFor(defaultWaitOnAction);
    }

    public void sendText(final String text) {
        driver.findElement(current).sendKeys(text);
    }

    public void elementById(final String id) {
        this.current = By.id(id);
    }

    public void elementByXPath(final String xpath) {
        this.current = By.xpath(xpath);
    }

    public void elementByLinkText(final String linkText) {
        this.current = By.xpath("//a[text()='" + linkText + "']");
    }

    public void elementByCssSelector(final String css) {
        this.current = By.cssSelector(css);
    }

    public void selectAndWait(final String text) {
        waitForElementToBeClickable();
        select(text);
        waitFor(defaultWaitOnAction);
    }

    public void select(final String text) {
        new Select(driver.findElement(current)).selectByVisibleText(text);
    }

    public void switchToFrame() {
        driver.switchTo().frame(driver.findElement(current));
    }

    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    public void end() {
        driver.quit();
        this.started = false;
    }

}
