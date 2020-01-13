package com.tomluk.properties;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

public class ObservableListTest {

    private ObservableList<String> list;
    private TestObservableListChangeListener listener;

    @Before
    public void setUp() {
        list = ObservableList.of(new ArrayList<>());
        listener = new TestObservableListChangeListener();
        list.addListChangeListener(listener);
    }

    @Test
    public void add() {
        list.add("a");
        assertEquals(1, list.size());
        assertEquals("a", list.get(0));
        assertEquals(ObservableListChangeEvent.EventType.ADD, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertNull(listener.oldValue);
        assertEquals("a", listener.newValue);
        assertNull(listener.bulkOldValue);
        assertNull(listener.bulkNewValue);
    }

    @Test
    public void addWithIndex() {
        list.addAll(Arrays.asList("a", "b", "c"));
        listener.bulkNewValue = null;
        list.add(1, "d");
        assertEquals(4, list.size());
        assertEquals(Arrays.asList("a", "d", "b", "c"), list);
        assertEquals(ObservableListChangeEvent.EventType.ADD, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertNull(listener.oldValue);
        assertEquals("d", listener.newValue);
        assertNull(listener.bulkOldValue);
        assertNull(listener.bulkNewValue);
    }

    @Test
    public void set() {
        list.addAll(Arrays.asList("a", "b", "c"));
        listener.bulkNewValue = null;
        list.set(1, "d");
        assertEquals(3, list.size());
        assertEquals(Arrays.asList("a", "d", "c"), list);
        assertEquals(ObservableListChangeEvent.EventType.SET, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertEquals("b", listener.oldValue);
        assertEquals("d", listener.newValue);
        assertNull(listener.bulkOldValue);
        assertNull(listener.bulkNewValue);
    }

    @Test
    public void remove() {
        list.addAll(Arrays.asList("a", "b", "c"));
        listener.bulkNewValue = null;
        list.remove("b");
        assertEquals(2, list.size());
        assertEquals(Arrays.asList("a", "c"), list);
        assertEquals(ObservableListChangeEvent.EventType.REMOVE, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertEquals("b", listener.oldValue);
        assertNull(listener.newValue);
        assertNull(listener.bulkOldValue);
        assertNull(listener.bulkNewValue);
    }

    @Test
    public void removeWithIndex() {
        list.addAll(Arrays.asList("a", "b", "c"));
        listener.bulkNewValue = null;
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals(Arrays.asList("a", "c"), list);
        assertEquals(ObservableListChangeEvent.EventType.REMOVE, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertEquals("b", listener.oldValue);
        assertNull(listener.newValue);
        assertNull(listener.bulkOldValue);
        assertNull(listener.bulkNewValue);
    }

    @Test
    public void removeNotExisting() {
        list.addAll(Arrays.asList("a", "b", "c"));
        listener.bulkNewValue = null;
        listener.event = null;
        list.remove("d");
        assertEquals(3, list.size());
        assertEquals(Arrays.asList("a", "b", "c"), list);
        assertNull(listener.event);
        assertNull(listener.oldValue);
        assertNull(listener.newValue);
        assertNull(listener.bulkOldValue);
        assertNull(listener.bulkNewValue);
    }

    @Test
    public void addAll() {
        list.addAll(Arrays.asList("a", "b", "c"));
        assertEquals(3, list.size());
        assertEquals(Arrays.asList("a", "b", "c"), list);
        assertEquals(ObservableListChangeEvent.EventType.ADD, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertNull(listener.oldValue);
        assertNull(listener.newValue);
        assertNull(listener.bulkOldValue);
        assertEquals(Arrays.asList("a", "b", "c"), listener.bulkNewValue);
    }

    @Test
    public void addAllWithIndex() {
        list.addAll(Arrays.asList("x", "y"));
        list.addAll(1, Arrays.asList("a", "b", "c"));
        assertEquals(5, list.size());
        assertEquals(Arrays.asList("x", "a", "b", "c", "y"), list);
        assertEquals(ObservableListChangeEvent.EventType.ADD, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertNull(listener.oldValue);
        assertNull(listener.newValue);
        assertNull(listener.bulkOldValue);
        assertEquals(Arrays.asList("a", "b", "c"), listener.bulkNewValue);
    }

    @Test
    public void removeAll() {
        list.addAll(Arrays.asList("a", "b", "c"));
        listener.bulkNewValue = null;
        list.removeAll(Arrays.asList("b", "c", "d"));
        assertEquals(1, list.size());
        assertEquals(Collections.singletonList("a"), list);
        assertEquals(ObservableListChangeEvent.EventType.REMOVE, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertNull(listener.oldValue);
        assertNull(listener.newValue);
        assertEquals(Arrays.asList("b", "c"), listener.bulkOldValue);
        assertNull(listener.bulkNewValue);
    }

    @Test
    public void retainAll() {
        list.addAll(Arrays.asList("a", "b", "c"));
        listener.bulkNewValue = null;
        list.retainAll(Arrays.asList("b", "c", "d"));
        assertEquals(2, list.size());
        assertEquals(Arrays.asList("b", "c"), list);
        assertEquals(ObservableListChangeEvent.EventType.REMOVE, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertNull(listener.oldValue);
        assertNull(listener.newValue);
        assertEquals(Collections.singletonList("a"), listener.bulkOldValue);
        assertNull(listener.bulkNewValue);
    }

    @Test
    public void clear() {
        list.addAll(Arrays.asList("a", "b", "c"));
        listener.bulkNewValue = null;
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertEquals(ObservableListChangeEvent.EventType.REMOVE, listener.event.getEventType());
        assertSame(list, listener.event.getSource());
        assertNull(listener.oldValue);
        assertNull(listener.newValue);
        assertEquals(Arrays.asList("a", "b", "c"), listener.bulkOldValue);
        assertNull(listener.bulkNewValue);
    }

    private static class TestObservableListChangeListener implements ObservableListChangeListener<String> {

        ObservableListChangeEvent<String> event;
        String oldValue;
        String newValue;
        Collection<? extends String> bulkOldValue;
        Collection<? extends String> bulkNewValue;

        @Override
        public void onChange(ObservableListChangeEvent<String> event, String oldValue, String newValue) {
            this.event = event;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        @Override
        public void onChange(ObservableListChangeEvent<String> event, Collection<? extends String> oldValue, Collection<? extends String> newValue) {
            this.event = event;
            bulkOldValue = oldValue;
            bulkNewValue = newValue;
        }
    }

}