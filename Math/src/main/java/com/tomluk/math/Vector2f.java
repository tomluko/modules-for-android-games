package com.tomluk.math;

import java.util.Objects;

public class Vector2f {

    public static Vector2f ZERO = of(0f, 0f);
    public static Vector2f ONE = of(1f, 1f);
    public static Vector2f X = of(1f, 0f);
    public static Vector2f Y = of(0f, 1f);

    private float x, y;

    public static Vector2f of(Vector2f vector) {
        return of(vector.x, vector.y);
    }

    public static Vector2f of(float x, float y) {
        return new Vector2f(x, y);
    }

    public static Vector2f ofX(float x) {
        return X.scale(x);
    }

    public static Vector2f ofY(float y) {
        return Y.scale(y);
    }

    private Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f negate() {
        return scale(-1f);
    }

    public Vector2f scale(float scalar) {
        return of(x * scalar, y * scalar);
    }

    public Vector2f add(Vector2f vector) {
        return of(x + vector.x, y + vector.y);
    }

    public Vector2f sub(Vector2f vector) {
        return of(x - vector.x, y - vector.y);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2f normalize() {
        return scale(1f / length());
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2f getXVec() {
        return X.scale(x);
    }

    public Vector2f getYVec() {
        return Y.scale(y);
    }

    public Vector2i toVector2i() {
        return Vector2i.of((int) x, (int) y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return Float.compare(vector2f.x, x) == 0 &&
                Float.compare(vector2f.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector2f{" + "x=" + x + ", y=" + y + '}';
    }
}
