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

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public final class Locate {

    public enum Type {
        ID,
        XPATH,
        LABEL,
        LINK_TEXT,
        CSS_SELECTOR;
    }

    @Getter
    final Type type;

    @Getter
    final String representation;

    public static Locate id(@NonNull String locator) {
        return new Locate(Type.ID, locator);
    }

    public static Locate xpath(@NonNull String locator) {
        return new Locate(Type.XPATH, locator);
    }

    public static Locate label(@NonNull String locator) {
        return new Locate(Type.LABEL, locator);
    }

    public static Locate linkText(@NonNull String locator) {
        return new Locate(Type.LINK_TEXT, locator);
    }

    public static Locate cssSelector(@NonNull String locator) {
        return new Locate(Type.CSS_SELECTOR, locator);
    }

}
