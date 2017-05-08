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