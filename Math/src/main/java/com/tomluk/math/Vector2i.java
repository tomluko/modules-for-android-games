package com.tomluk.math;

import java.util.Objects;

public class Vector2i {

    public static Vector2i ZERO = of(0, 0);
    public static Vector2i ONE = of(1, 1);
    public static Vector2i X = of(1, 0);
    public static Vector2i Y = of(0, 1);

    private int x, y;

    public static Vector2i of(Vector2i vector) {
        return of(vector.x, vector.y);
    }

    public static Vector2i of(int x, int y) {
        return new Vector2i(x, y);
    }

    public static Vector2i ofX(int x) {
        return of(x, 0);
    }

    public static Vector2i ofY(int y) {
        return of(0, y);
    }

    private Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2f toVector2f() {
        return Vector2f.of(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2i vector2i = (Vector2i) o;
        return x == vector2i.x &&
                y == vector2i.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector2i{" + "x=" + x + ", y=" + y + '}';
    }
}
