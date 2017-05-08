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

import lombok.NonNull;
import net.roddrim.number5.tools.lang.Pair;

import java.util.concurrent.TimeUnit;

public interface BrowserSession {

    char ENTER = '\uE007';

    WebBot getBrowserBot();

    Pair<Long, TimeUnit> getDefaultWaitAfterClick();

    default void selectAndWait(@NonNull final String text) {
        getBrowserBot().waitForElementToBeClickable();
        getBrowserBot().select(text);
        getBrowserBot().waitForDefault();
    }

    default void clearSendTextAndWait(@NonNull final CharSequence text) {
        getBrowserBot().waitForElementToBeClickable();
        getBrowserBot().click();
        getBrowserBot().clear();
        getBrowserBot().sendText(text);
        getBrowserBot().waitForDefault();
    }

    default void clearSendTextEnterAndWait(@NonNull final CharSequence text) {
        getBrowserBot().waitForElementToBeClickable();
        getBrowserBot().click();
        getBrowserBot().clear();
        getBrowserBot().sendText(text);
        getBrowserBot().sendText(String.valueOf(ENTER));
        getBrowserBot().waitForDefault();
    }

    default void clickAndWait() {
        getBrowserBot().waitForElementToBeClickable();
        getBrowserBot().click();
        getBrowserBot().waitForDefault();
    }

    default void end() {
        getBrowserBot().endBrowser();
    }

    default void start() {
        getBrowserBot().startBrowser();
        getBrowserBot().waitForDefault();
    }

    default void sendTextTo(@NonNull final Locate locate, @NonNull final CharSequence text) {
        getBrowserBot().target(locate);
        clearSendTextAndWait(text);
    }

    default void clickElement(@NonNull final Locate locate) {
        getBrowserBot().target(locate);
        getBrowserBot().waitForElementToBeClickable();
        clickAndWait();
        getBrowserBot().waitFor(getDefaultWaitAfterClick());
    }

    default Locate locateInputWithValue(@NonNull final String value) {
        return Locate.xpath("//input[@value='" + value + "']");
    }

}
