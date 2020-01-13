package com.tomluk.math;

import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2fTest {

    @Test
    public void of() {
        Vector2f vec = Vector2f.of(1f, 2f);
        assertEquals(1f, vec.getX(), 0f);
        assertEquals(2f, vec.getY(), 0f);
        Vector2f copy = Vector2f.of(vec);
        assertEquals(1f, copy.getX(), 0f);
        assertEquals(2f, copy.getY(), 0f);
        vec = Vector2f.ofX(3f);
        assertEquals(3f, vec.getX(), 0f);
        assertEquals(0f, vec.getY(), 0f);
        vec = Vector2f.ofY(4f);
        assertEquals(0f, vec.getX(), 0f);
        assertEquals(4f, vec.getY(), 0f);
    }

    @Test
    public void negate() {
        Vector2f vec = Vector2f.of(1f, 2f);
        Vector2f neg = vec.negate();
        assertNotSame(vec, neg);
        assertEquals(-1f, neg.getX(), 0f);
        assertEquals(-2f, neg.getY(), 0f);
    }

    @Test
    public void scale() {
        Vector2f vec = Vector2f.of(1f, 2f);
        Vector2f scaled = vec.scale(2f);
        assertNotSame(vec, scaled);
        assertEquals(2f, scaled.getX(), 0f);
        assertEquals(4f, scaled.getY(), 0f);
    }

    @Test
    public void add() {
        Vector2f vec = Vector2f.of(1f, 2f);
        Vector2f sum = vec.add(Vector2f.of(2f, 3f));
        assertNotSame(vec, sum);
        assertEquals(3f, sum.getX(), 0f);
        assertEquals(5f, sum.getY(), 0f);
    }

    @Test
    public void sub() {
        Vector2f vec = Vector2f.of(1f, 2f);
        Vector2f delta = vec.sub(Vector2f.of(2f, 3f));
        assertNotSame(vec, delta);
        assertEquals(-1f, delta.getX(), 0f);
        assertEquals(-1f, delta.getY(), 0f);
    }

    @Test
    public void length() {
        Vector2f vec = Vector2f.of(3f, 4f);
        assertEquals(5f, vec.length(), 0.0001f);
        vec = vec.negate();
        assertEquals(5f, vec.length(), 0.0001f);
    }

    @Test
    public void normalize() {
        Vector2f vec = Vector2f.of(3f, 4f);
        Vector2f answer = vec.normalize();
        assertEquals(1f, answer.length(), 0.0001f);
        assertEquals(3f / 4f, answer.getX() / answer.getY(), 0.0001f);
    }

    @Test
    public void toVector2i() {
        Vector2i vec = Vector2f.of(1f, 2f).toVector2i();
        assertEquals(1, vec.getX());
        assertEquals(2, vec.getY());
    }

    @Test
    public void testEquals() {
        Vector2f vec1 = Vector2f.of(1f, 2f);
        Vector2f vec2 = Vector2f.of(1f, 2f);
        assertNotSame(vec1, vec2);
        assertEquals(vec1, vec2);
    }

    @Test
    public void testHashCode() {
        Vector2f vec1 = Vector2f.of(1f, 2f);
        Vector2f vec2 = Vector2f.of(1f, 2f);
        assertNotSame(vec1, vec2);
        assertEquals(vec1.hashCode(), vec2.hashCode());
    }
}