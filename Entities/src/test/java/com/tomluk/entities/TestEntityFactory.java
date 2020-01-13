package com.tomluk.entities;

public class TestEntityFactory {

    public static Entity<TestGameObject> createTestEntityWithTestComponent() {
        final Entity<TestGameObject> entity = createTestEntity();
        entity.addComponent(new TestComponent());
        return entity;
    }

    public static Entity<TestGameObject> createTestEntity() {
        return new Entity<>(new TestGameObject());
    }
}
