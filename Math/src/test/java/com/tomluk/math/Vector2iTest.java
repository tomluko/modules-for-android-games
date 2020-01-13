package com.tomluk.math;

import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2iTest {

    @Test
    public void of() {
        Vector2i vec = Vector2i.of(1, 2);
        assertEquals(1, vec.getX());
        assertEquals(2, vec.getY());
        Vector2i copy = Vector2i.of(vec);
        assertEquals(1, copy.getX());
        assertEquals(2, copy.getY());
        vec = Vector2i.ofX(3);
        assertEquals(3, vec.getX());
        assertEquals(0, vec.getY());
        vec = Vector2i.ofY(4);
        assertEquals(0, vec.getX());
        assertEquals(4, vec.getY());
    }

    @Test
    public void toVector2f() {
        Vector2f vec = Vector2i.of(1, 2).toVector2f();
        assertEquals(1f, vec.getX(), 0f);
        assertEquals(2f, vec.getY(), 0f);
    }

    @Test
    public void testEquals() {
        Vector2i vec1 = Vector2i.of(1, 2);
        Vector2i vec2 = Vector2i.of(1, 2);
        assertNotSame(vec1, vec2);
        assertEquals(vec1, vec2);
    }

    @Test
    public void testHashCode() {
        Vector2i vec1 = Vector2i.of(1, 2);
        Vector2i vec2 = Vector2i.of(1, 2);
        assertNotSame(vec1, vec2);
        assertEquals(vec1.hashCode(), vec2.hashCode());
    }
}