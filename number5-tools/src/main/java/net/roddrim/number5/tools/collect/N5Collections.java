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
package net.roddrim.number5.tools.collect;

import com.google.common.collect.Table;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.function.Predicate;

@UtilityClass
public class N5Collections {

    public static <E> Predicate<? extends Iterable<E>> isNullOrEmpty() {
        return i -> isNullOrEmpty(i);
    }

    public static boolean isNullOrEmpty(@NonNull final Iterable<?> i) {
        return i == null || i.iterator().hasNext();
    }

    public static <T> Iterable<T> iterable(@NonNull final Iterator<T> i) {
        return () -> i;
    }

    public static <T> Iterable<T> iterable(@NonNull final Enumeration<T> enumeration) {
        return () -> {
            return new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return enumeration.hasMoreElements();
                }

                @Override
                public T next() {
                    return enumeration.nextElement();
                }
            };
        };
    }

    @SafeVarargs
    public static <E> E[] array(@NonNull final E... elements) {
        return elements;
    }

    public static <E> FluentList<E> fluent(@NonNull final List<E> list) {
        return FluentList.of(list);
    }

    public static <E> FluentSet<E> fluent(@NonNull final Set<E> set) {
        return FluentSet.of(set);
    }

    public static <K, V> FluentMap<K, V> fluent(@NonNull final Map<K, V> map) {
        return FluentMap.of(map);
    }

    public static <R, C, V> FluentTable<R, C, V> fluent(@NonNull final Table<R, C, V> table) {
        return FluentTable.of(table);
    }

}
