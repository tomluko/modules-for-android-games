package com.tomluk.entities;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public enum Entities {

    Instance;

    private final List<Entity> entities;

    Entities() {
        entities = new ArrayList<>();
    }

    public void update(double time) {
        for (Entity entity : new ArrayList<>(entities)) {
            entity.update(time);
        }
    }

    public void addEntity(Entity entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        entity.dispose();
    }

    public void dispose() {
        for (Entity entity : new ArrayList<>(entities)) {
            entity.dispose();
        }
        entities.clear();
    }
}
