package com.tomluk.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Entity<GAME_OBJECT> {

    private List<Component<GAME_OBJECT>> components;
    private final GAME_OBJECT gameObject;

    public Entity(GAME_OBJECT gameObject) {
        this.gameObject = gameObject;
        components = new ArrayList<>(5);
    }

    public void addComponent(Component<GAME_OBJECT> component) {
        if (!components.contains(component)) {
            components.add(component);
            component.onAdd(this);
        }
    }

    public void removeComponent(Component<GAME_OBJECT> component) {
        if (components.remove(component)) {
            component.onRemove(this);
        }
    }

    /**
     * @param time time in ms
     */
    public void update(double time) {
        for (Component<GAME_OBJECT> component : new ArrayList<>(components)) {
            component.update(this, time);
        }
    }

    public void dispose() {
        Iterator<Component<GAME_OBJECT>> iterator = components.iterator();
        while (iterator.hasNext()) {
            Component<GAME_OBJECT> component = iterator.next();
            component.onRemove(this);
            iterator.remove();
        }
    }

    public GAME_OBJECT getGameObject() {
        return gameObject;
    }
}
