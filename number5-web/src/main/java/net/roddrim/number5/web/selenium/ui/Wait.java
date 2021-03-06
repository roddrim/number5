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
package net.roddrim.number5.web.selenium.ui;

import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Wait implements Function<WebDriver, Boolean> {

    private final long millis;

    public Wait(final long amount, final TimeUnit unit) {
        this.millis = unit.toMillis(amount);
    }

    @Override
    public Boolean apply(final WebDriver driver) {
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return true;
    }
}
