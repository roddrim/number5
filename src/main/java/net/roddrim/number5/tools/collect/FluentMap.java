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

import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FluentMap<K, V> implements Map<K, V> {

    private final Map<K, V> delegate;

    public FluentMap<K, V> map(final K key, final V value) {
        delegate.put(key, value);
        return this;
    }

    public FluentMap<K, V> map(final Entry<K, V> e) {
        delegate.put(e.getKey(), e.getValue());
        return this;
    }

    public FluentMap<K, V> map(final Map<K, V> m) {
        delegate.putAll(m);
        return this;
    }

    public FluentMap<K, V> copy() {
        return copyOf(this.delegate);
    }

    public Map<K, V> unmodifiableView() {
        return Collections.unmodifiableMap(this.delegate);
    }

    public V getOrPutDefault(K key, final Supplier<V> defaultValues) {
        final V v = delegate.get(key);
        if (v == null) {
            delegate.put(key, defaultValues.get());
        }
        return delegate.get(key);
    }

    public static <K, V> FluentMap<K, V> ofNewHashMap() {
        return of(Maps.newHashMap());
    }

    public static <K, V> FluentMap<K, V> ofNewLinkedHashMap() {
        return of(Maps.newLinkedHashMap());
    }

    public static <K, V> FluentMap<K, V> of(final Map<K, V> map) {
        return new FluentMap<>(map);
    }

    public static <K, V> FluentMap<K, V> copyOf(final Map<K, V> map) {
        return new FluentMap<>(Maps.newLinkedHashMap(map));
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return delegate.containsValue(value);
    }

    @Override
    public V get(final Object key) {
        return delegate.get(key);
    }

    @Override
    public V put(final K key, final V value) {
        return delegate.put(key, value);
    }

    @Override
    public V remove(final Object key) {
        return delegate.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        delegate.putAll(m);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Set<K> keySet() {
        return delegate.keySet();
    }

    @Override
    public Collection<V> values() {
        return delegate.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return delegate.entrySet();
    }

    @Override
    public boolean equals(final Object another) {
        return delegate.equals(another);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

}
