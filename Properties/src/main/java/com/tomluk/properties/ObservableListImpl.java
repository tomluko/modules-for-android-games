package com.tomluk.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@SuppressWarnings("NullableProblems")
class ObservableListImpl<TYPE> implements ObservableList<TYPE> {

    private final List<TYPE> delegate;
    private final List<ObservableListChangeListener<TYPE>> listeners;

    ObservableListImpl(List<TYPE> list) {
        delegate = list;
        listeners = new ArrayList<>(2);
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
    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    @Override
    public Iterator<TYPE> iterator() {
        return delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        //noinspection SuspiciousToArrayCall
        return delegate.toArray(ts);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean add(TYPE e) {
        boolean added = delegate.add(e);
        if (added) {
            fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.ADD), null, e);
        }
        return added;
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = delegate.remove(o);
        if (removed) {
            //noinspection unchecked
            fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.REMOVE), (TYPE) o, null);
        }
        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return delegate.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends TYPE> collection) {
        boolean added = delegate.addAll(collection);
        if (added) {
            fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.ADD), null, collection);
        }
        return added;
    }

    @Override
    public boolean addAll(int i, Collection<? extends TYPE> collection) {
        boolean added = delegate.addAll(i, collection);
        if (added) {
            fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.ADD), null, collection);
        }
        return added;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        List<TYPE> oldValue = new ArrayList<>(delegate);
        boolean removed = delegate.removeAll(collection);
        oldValue.removeAll(delegate);
        if (removed && !oldValue.isEmpty()) {
            fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.REMOVE), oldValue, null);
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        List<TYPE> oldValue = new ArrayList<>(delegate);
        boolean retained = delegate.retainAll(collection);
        oldValue.removeAll(delegate);
        if (retained && !oldValue.isEmpty()) {
            fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.REMOVE), oldValue, null);
        }
        return retained;
    }

    @Override
    public void clear() {
        List<TYPE> oldValue = new ArrayList<>(delegate);
        delegate.clear();
        fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.REMOVE), oldValue, null);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public TYPE get(int i) {
        return delegate.get(i);
    }

    @Override
    public TYPE set(int i, TYPE e) {
        TYPE oldValue = delegate.set(i, e);
        fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.SET), oldValue, e);
        return oldValue;
    }

    @Override
    public void add(int i, TYPE e) {
        delegate.add(i, e);
        fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.ADD), null, e);
    }

    @Override
    public TYPE remove(int i) {
        TYPE oldValue = delegate.remove(i);
        fireOnChangeEvent(new ObservableListChangeEvent<>(this, ObservableListChangeEvent.EventType.REMOVE), oldValue, null);
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        return delegate.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return delegate.lastIndexOf(o);
    }

    @Override
    public ListIterator<TYPE> listIterator() {
        return delegate.listIterator();
    }

    @Override
    public ListIterator<TYPE> listIterator(int i) {
        return delegate.listIterator(i);
    }

    @Override
    public List<TYPE> subList(int i, int i1) {
        return delegate.subList(i, i1);
    }

    @Override
    public void addListChangeListener(ObservableListChangeListener<TYPE> listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListChangeListener(ObservableListChangeListener<TYPE> listener) {
        listeners.remove(listener);
    }

    private void fireOnChangeEvent(ObservableListChangeEvent<TYPE> event, TYPE oldValue, TYPE newValue) {
        for (ObservableListChangeListener<TYPE> listener : listeners) {
            listener.onChange(event, oldValue, newValue);
        }
    }

    private void fireOnChangeEvent(ObservableListChangeEvent<TYPE> event, Collection<? extends TYPE> oldValue, Collection<? extends TYPE> newValue) {
        for (ObservableListChangeListener<TYPE> listener : listeners) {
            listener.onChange(event, oldValue, newValue);
        }
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
