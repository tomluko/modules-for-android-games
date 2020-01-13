package com.tomluk.properties;

public interface ObservableProperty<TYPE> {

    static <T> ObservableProperty<T> of(T initialValue){
        return new ObservablePropertyImpl<>(initialValue);
    }

    void setValue(TYPE value);

    TYPE getValue();

    void addPropertyChangeListener(ObservablePropertyChangeListener<TYPE> listener);

    void removePropertyChangeListener(ObservablePropertyChangeListener<TYPE> listener);

    void dispose();
}
