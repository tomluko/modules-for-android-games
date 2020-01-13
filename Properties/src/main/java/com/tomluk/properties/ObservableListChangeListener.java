package com.tomluk.properties;

import java.util.Collection;

public interface ObservableListChangeListener<TYPE> {

    /**
     * Receives single element change events.
     * @param oldValue value that was removed from list.
     * @param newValue value that was added to list.
     */
    void onChange(ObservableListChangeEvent<TYPE> event, TYPE oldValue, TYPE newValue);

    /**
     * Receives bulk element change events, like addAll, removeAll, etc.
     * @param oldValue stuff that been removed from list.
     * @param newValue stuff that been added to list.
     */
    default void onChange(ObservableListChangeEvent<TYPE> event, Collection<? extends TYPE> oldValue, Collection<? extends TYPE> newValue) {

    }
}
