package com.tomluk.properties;

public class ObservableListChangeEvent<TYPE> {

    private ObservableList<TYPE> source;
    private EventType eventType;

    ObservableListChangeEvent(ObservableList<TYPE> source, EventType eventType) {
        this.source = source;
        this.eventType = eventType;
    }

    public ObservableList<TYPE> getSource() {
        return source;
    }

    public EventType getEventType() {
        return eventType;
    }

    enum EventType{
        ADD,
        REMOVE,
        SET;
    }
}
