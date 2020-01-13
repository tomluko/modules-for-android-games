package com.tomluk.entities;

public class TestComponent implements Component<TestGameObject>{

    @Override
    public void onAdd(Entity<TestGameObject> entity) {
        entity.getGameObject().sequence += "a";
    }

    @Override
    public void onRemove(Entity<TestGameObject> entity) {
        entity.getGameObject().sequence += "r";
    }

    @Override
    public void update(Entity<TestGameObject> entity, double time) {
        entity.getGameObject().sequence += "u";
    }
}
