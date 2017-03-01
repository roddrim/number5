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

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.*;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class FluentList<E> implements List<E> {

    private final List<E> delegate;

    public FluentList<E> list(final E element) {
        delegate.add(element);
        return this;
    }

    public FluentList<E> list(final Collection<E> c) {
        delegate.addAll(c);
        return this;
    }

    public FluentList<E> copy() {
        return copyOf(this.delegate);
    }

    public List<E> unmodifiableView() {
        return Collections.unmodifiableList(this.delegate);
    }

    public static <E> FluentList<E> ofNewArrayList() {
        return of(Lists.newArrayList());
    }

    public static <E> FluentList<E> ofNewLinkedList() {
        return of(Lists.newLinkedList());
    }

    public static <E> FluentList<E> of(final E... elements) {
        return of(Lists.newArrayList(elements));
    }

    public static <E> FluentList<E> of(final List<E> list) {
        return new FluentList<>(list);
    }

    public static <E> FluentList<E> copyOf(final List<E> list) {
        return of(Lists.newArrayList(list));
    }

    @Override
    public E get(final int index) {
        return delegate.get(index);
    }

    @Override
    public E set(final int index, final E element) {
        return delegate.set(index, element);
    }

    @Override
    public void add(final int index, final E element) {
        delegate.add(index, element);
    }

    @Override
    public E remove(final int index) {
        return delegate.remove(index);
    }

    @Override
    public int indexOf(final Object o) {
        return delegate.indexOf(o);
    }

    @Override
    public int lastIndexOf(final Object o) {
        return delegate.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return delegate.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        return delegate.listIterator(index);
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex) {
        return delegate.subList(fromIndex, toIndex);
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
    public boolean addAll(final int index, final Collection<? extends E> c) {
        return delegate.addAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return delegate.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return delegate.retainAll(c);
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
