package com.tomluk.properties;

import java.util.ArrayList;
import java.util.List;

class ObservablePropertyImpl<TYPE> implements ObservableProperty<TYPE> {

    private TYPE value;
    private final List<ObservablePropertyChangeListener<TYPE>> listeners;

    ObservablePropertyImpl(TYPE initialValue) {
        this.value = initialValue;
        listeners = new ArrayList<>(5);
    }

    @Override
    public void setValue(TYPE value) {
        final TYPE oldValue = this.value;
        this.value = value;
        notifyValueSet(oldValue, this.value);
    }

    @Override
    public TYPE getValue() {
        return value;
    }

    private void notifyValueSet(TYPE oldValue, TYPE newValue) {
        for (ObservablePropertyChangeListener<TYPE> listener : listeners) {
            listener.onValueChange(oldValue, newValue);
        }
    }

    @Override
    public void addPropertyChangeListener(ObservablePropertyChangeListener<TYPE> listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removePropertyChangeListener(ObservablePropertyChangeListener<TYPE> listener) {
        listeners.remove(listener);
    }

    @Override
    public void dispose() {
        listeners.clear();
    }
}
