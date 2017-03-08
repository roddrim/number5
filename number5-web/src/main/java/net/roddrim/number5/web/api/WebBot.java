package net.roddrim.number5.web.api;

import net.roddrim.number5.tools.lang.Pair;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface WebBot {

    Pair<Long, TimeUnit> getDefaultWaitOnAction();

    void startBrowser();

    void getPage(final Object page);

    void getPage(final Object... objects);

    void target(final Locate locate);

    void waitFor(final long amount, final TimeUnit unit);

    default void waitForDefault() {
        waitFor(getDefaultWaitOnAction());
    }

    default void waitFor(final Pair<Long, TimeUnit> time) {
        waitFor(time.getKey(), time.getValue());
    }

    void waitForElementToBeClickable();

    void waitForElementToBePresent();

    void click();

    void clear();

    void sendText(final CharSequence text);

    String getText();

    String getAttribute(final String attributeName);

    String getTagName();

    List<String> getTexts(final Locate locate);

    List<String> getAttributeValues(final Locate locate, final String attributeName);

    List<String> getTagNames(final Locate locate);

    void select(final String text);

    void switchToFrame();

    void switchToDefaultFrame();

    Optional<File> takeScreenShot();

    void endBrowser();

}