package net.roddrim.number5.web.api;/*
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

import org.openqa.selenium.Keys;

public interface BrowserSession {

    WebBot getBrowserBot();

    default void selectAndWait(final String text) {
        getBrowserBot().waitForElementToBeClickable();
        getBrowserBot().select(text);
        getBrowserBot().waitForDefault();
    }

    default void clearSendTextAndWait(final CharSequence text) {
        getBrowserBot().waitForElementToBeClickable();
        getBrowserBot().click();
        getBrowserBot().clear();
        getBrowserBot().sendText(text);
        getBrowserBot().waitForDefault();
    }

    default void clearSendTextEnterAndWait(final CharSequence text) {
        getBrowserBot().waitForElementToBeClickable();
        getBrowserBot().click();
        getBrowserBot().clear();
        getBrowserBot().sendText(text);
        getBrowserBot().sendText(Keys.ENTER);
        getBrowserBot().waitForDefault();
    }

    default void clickAndWait() {
        getBrowserBot().waitForElementToBeClickable();
        getBrowserBot().click();
        getBrowserBot().waitForDefault();
    }

}
