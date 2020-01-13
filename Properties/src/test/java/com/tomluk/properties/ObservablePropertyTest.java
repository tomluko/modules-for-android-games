package com.tomluk.properties;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

public class ObservablePropertyTest {

    @Test
    public void initialValue() {
        assertTrue(ObservableProperty.of(true).getValue());
        assertEquals(Short.valueOf((short) 1), ObservableProperty.of((short) 1).getValue());
        assertEquals(Integer.valueOf(2), ObservableProperty.of(2).getValue());
        assertEquals(Long.valueOf(3L), ObservableProperty.of(3L).getValue());
        assertEquals(Float.valueOf(4f), ObservableProperty.of(4f).getValue());
        assertEquals(Double.valueOf(5d), ObservableProperty.of(5d).getValue());
        assertEquals("str", ObservableProperty.of("str").getValue());
        Object obj = new Object();
        assertEquals(obj, ObservableProperty.of(obj).getValue());
    }

    @Test
    public void setValue() {
        ObservableProperty<Integer> property = ObservableProperty.of(0);
        property.setValue(1);
        assertEquals(Integer.valueOf(1), property.getValue());
    }

    @Test
    public void addPropertyChangeListener() {
        ObservableProperty<Integer> property = ObservableProperty.of(1);
        AtomicBoolean listenerCalled = new AtomicBoolean(false);
        property.addPropertyChangeListener((oldValue, newValue) -> {
            assertEquals(Integer.valueOf(1), oldValue);
            assertEquals(Integer.valueOf(2), newValue);
            listenerCalled.set(true);
        });
        property.setValue(2);
        assertTrue(listenerCalled.get());
    }

    @Test
    public void removePropertyChangeListener() {
        ObservableProperty<Integer> property = ObservableProperty.of(1);
        ObservablePropertyChangeListener<Integer> propertyChangeListener = (oldValue, newValue) -> fail();
        property.addPropertyChangeListener(propertyChangeListener);
        property.removePropertyChangeListener(propertyChangeListener);
        property.setValue(2);
    }

    @Test
    public void dispose() {
        ObservableProperty<Integer> property = ObservableProperty.of(0);
        property.addPropertyChangeListener((oldValue, newValue) -> fail());
        property.addPropertyChangeListener((oldValue, newValue) -> fail());
        property.dispose();
        property.setValue(1);
    }
}