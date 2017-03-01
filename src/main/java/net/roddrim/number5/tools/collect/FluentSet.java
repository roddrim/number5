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

import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.*;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FluentSet<E> implements Set<E> {

    private final Set<E> delegate;

    public FluentSet<E> set(final E element) {
        delegate.add(element);
        return this;
    }

    public FluentSet<E> set(final Collection<E> c) {
        delegate.addAll(c);
        return this;
    }

    public FluentSet<E> copy() {
        return copyOf(this.delegate);
    }

    public Set<E> unmodifiableView() {
        return Collections.unmodifiableSet(this.delegate);
    }

    public static <E> FluentSet<E> ofNewLinkedHashSet() {
        return of(Sets.newLinkedHashSet());
    }

    public static <E> FluentSet<E> ofNewHashSet() {
        return of(Sets.newHashSet());
    }

    public static <E> FluentSet<E> ofNewTreeSet() {
        return of(new TreeSet<E>());
    }

    public static <E> FluentSet<E> of(final E... elements) {
        return of(Sets.newHashSet(elements));
    }

    public static <E> FluentSet<E> of(final Set<E> set) {
        return new FluentSet<>(set);
    }

    public static <E> FluentSet<E> copyOf(final Set<E> set) {
        return of(Sets.newLinkedHashSet(set));
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
    public boolean contains(final Object o) {
        return delegate.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return delegate.toArray(a);
    }

    @Override
    public boolean add(final E e) {
        return delegate.add(e);
    }

    @Override
    public boolean remove(final Object o) {
        return delegate.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return delegate.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        return delegate.addAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return delegate.retainAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return delegate.removeAll(c);
    }

    @Override
    public void clear() {
        delegate.clear();
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
