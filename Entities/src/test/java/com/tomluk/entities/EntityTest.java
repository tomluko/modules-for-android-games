package com.tomluk.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EntityTest {

    private Entity<TestGameObject> entity;
    private TestComponent component;

    @Before
    public void setUp() {
        entity = TestEntityFactory.createTestEntity();
        component = new TestComponent();
    }

    @Test
    public void getGameObject() {
        TestGameObject gameObject = new TestGameObject();
        Entity<TestGameObject> entity = new Entity<>(gameObject);
        assertEquals(gameObject, entity.getGameObject());
    }

    @Test
    public void addComponentToEntity() {
        entity.addComponent(component);
        assertEquals("a", entity.getGameObject().sequence);
        entity.addComponent(component);
        assertEquals("a", entity.getGameObject().sequence);
    }

    @Test
    public void removeComponentFromEntity() {
        entity.addComponent(component);
        entity.removeComponent(component);
        assertEquals("ar", entity.getGameObject().sequence);
        entity.removeComponent(component);
        assertEquals("ar", entity.getGameObject().sequence);
    }

    @Test
    public void updateComponentInEntity() {
        entity.addComponent(component);
        entity.update(0d);
        assertEquals("au", entity.getGameObject().sequence);
        entity.update(0d);
        assertEquals("auu", entity.getGameObject().sequence);
        entity.removeComponent(component);
        entity.update(0d);
        assertEquals("auur", entity.getGameObject().sequence);
    }

    @Test
    public void disposeEntity() {
        entity.addComponent(component);
        entity.dispose();
        assertEquals("ar", entity.getGameObject().sequence);
        entity.dispose();
        assertEquals("ar", entity.getGameObject().sequence);
    }

    @Test
    public void noComponentUpdateAfterDisposeEntity() {
        entity.addComponent(component);
        entity.dispose();
        entity.update(0d);
        assertEquals("ar", entity.getGameObject().sequence);
    }
}