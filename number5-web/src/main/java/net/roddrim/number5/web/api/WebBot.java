package net.roddrim.number5.web.api;

import net.roddrim.number5.tools.lang.Pair;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface WebBot {

    public Pair<Long, TimeUnit> getDefaultWaitOnAction();

    public void startBrowser();

    public void getPage(final Object page);

    public void getPage(final Object... objects);

    public void target(final Locate locate);

    public void waitFor(final long amount, final TimeUnit unit);

    default void waitForDefault() {
        waitFor(getDefaultWaitOnAction());
    }

    default void waitFor(final Pair<Long, TimeUnit> time) {
        waitFor(time.getKey(), time.getValue());
    }

    public void waitForElementToBeClickable();

    public void waitForElementToBePresent();

    public void click();

    public void clear();

    public void sendText(final CharSequence text);

    public String getTextValue();

    public String getAttributeValue(final String attributeName);

    public void select(final String text);

    public void switchToFrame();

    public void switchToDefaultFrame();

    public Optional<File> takeScreenShot();

    public void endBrowser();

}