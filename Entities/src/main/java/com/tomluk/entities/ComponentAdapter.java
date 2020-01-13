package com.tomluk.entities;

public abstract class ComponentAdapter<GAME_OBJECT> implements Component<GAME_OBJECT> {

    @Override
    public void onAdd(Entity<GAME_OBJECT> entity) {

    }

    @Override
    public void onRemove(Entity<GAME_OBJECT> entity) {

    }

    @Override
    public void update(Entity<GAME_OBJECT> entity, double time) {

    }
}
