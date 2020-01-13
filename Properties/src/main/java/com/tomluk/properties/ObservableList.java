package com.tomluk.properties;

import java.util.List;

public interface ObservableList<TYPE> extends List<TYPE> {

    static <TYPE> ObservableList<TYPE> of (List<TYPE> list) {
        return new ObservableListImpl<>(list);
    }

    void addListChangeListener(ObservableListChangeListener<TYPE> listener);

    void removeListChangeListener(ObservableListChangeListener<TYPE> listener);

}
