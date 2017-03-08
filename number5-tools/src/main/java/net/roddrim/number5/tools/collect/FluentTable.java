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

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class FluentTable<R, C, V> implements Table<R, C, V> {

    private final Table<R, C, V> delegate;

    public V getOrPutDefault(final R rowKey, final C columnKey, @NonNull final Supplier<V> defaultValues) {
        final V v = delegate.get(rowKey, columnKey);
        if (v == null) {
            delegate.put(rowKey, columnKey, defaultValues.get());
        }
        return delegate.get(rowKey, columnKey);
    }

    public FluentTable<R, C, V> map(final R rowKey, final C columnKey, final V value) {
        delegate.put(rowKey, columnKey, value);
        return this;
    }

    public static <R, C, V> FluentTable<R, C, V> ofNewHashBasedTable() {
        return of(HashBasedTable.<R, C, V>create());
    }

    public static <R, C, V> FluentTable<R, C, V> of(@NonNull final Table<R, C, V> table) {
        return new FluentTable<>(table);
    }

    @Override
    public boolean contains(final Object rowKey, final Object columnKey) {
        return delegate.contains(rowKey, columnKey);
    }

    @Override
    public boolean containsRow(final Object rowKey) {
        return delegate.containsRow(rowKey);
    }

    @Override
    public boolean containsColumn(Object columnKey) {
        return delegate.containsColumn(columnKey);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegate.containsValue(value);
    }

    @Override
    public V get(Object rowKey, Object columnKey) {
        return delegate.get(rowKey, columnKey);
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public V put(R rowKey, C columnKey, V value) {
        return delegate.put(rowKey, columnKey, value);
    }

    @Override
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        delegate.putAll(table);
    }

    @Override
    public V remove(Object rowKey, Object columnKey) {
        return delegate.remove(rowKey, columnKey);
    }

    @Override
    public Map<C, V> row(R rowKey) {
        return delegate.row(rowKey);
    }

    @Override
    public Map<R, V> column(C columnKey) {
        return delegate.column(columnKey);
    }

    @Override
    public Set<Cell<R, C, V>> cellSet() {
        return delegate.cellSet();
    }

    @Override
    public Set<R> rowKeySet() {
        return delegate.rowKeySet();
    }

    @Override
    public Set<C> columnKeySet() {
        return delegate.columnKeySet();
    }

    @Override
    public Collection<V> values() {
        return delegate.values();
    }

    @Override
    public Map<R, Map<C, V>> rowMap() {
        return delegate.rowMap();
    }

    @Override
    public Map<C, Map<R, V>> columnMap() {
        return delegate.columnMap();
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public boolean equals(final Object another) {
        return delegate.equals(another);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

}
