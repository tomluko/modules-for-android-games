package com.tomluk.properties;

public interface ObservablePropertyChangeListener<TYPE> {

    void onValueChange(TYPE oldValue, TYPE newValue);

}
