package com.tomluk.entities;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EntitiesTest {

    private Entity<TestGameObject> entity;
    private final Entities entities = Entities.Instance;

    @Before
    public void setUp() {
        entity = TestEntityFactory.createTestEntityWithTestComponent();
    }

    @After
    public void tearDown() {
        entities.removeEntity(entity);
    }

    @Test
    public void add() {
        entities.addEntity(entity);
        entities.update(0d);
        assertEquals("au", entity.getGameObject().sequence);
        entities.update(0d);
        assertEquals("auu", entity.getGameObject().sequence);
        entities.addEntity(entity);
        assertEquals("auu", entity.getGameObject().sequence);
    }

    @Test
    public void remove() {
        entities.addEntity(entity);
        entities.removeEntity(entity);
        entities.update(0d);
        assertEquals("ar", entity.getGameObject().sequence);
        entities.removeEntity(entity);
        assertEquals("ar", entity.getGameObject().sequence);
    }

    @Test
    public void dispose() {
        entities.addEntity(entity);
        entities.dispose();
        entities.update(0d);
        assertEquals("ar", entity.getGameObject().sequence);
        entities.dispose();
        assertEquals("ar", entity.getGameObject().sequence);
    }
}