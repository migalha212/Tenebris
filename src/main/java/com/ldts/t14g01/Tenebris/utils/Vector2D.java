package com.ldts.t14g01.Tenebris.utils;

import static java.lang.Math.abs;

public record Vector2D(int x, int y) {
    private static final double TG_22D = 0.414213562373;
    private static final double TG_67D = 2.41421356237;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT
    }

    // Data
    public double magnitude() {
        return Math.sqrt((long) x * x + (long) y * y);
    }

    // Math
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D minus(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D multiply(double k) {
        return new Vector2D((int) (x * k), (int) (y * k));
    }

    public long dot(Vector2D other) {
        return (long) this.x * other.x + (long) this.y * other.y;
    }

    // Util
    public Direction getMajorDirection() {
        // By Default the Direction is UP
        // Vertical Vector
        if (this.x == 0) {
            if (this.y > 0) return Direction.DOWN;
            else return Direction.UP;
        }

        // Get absolute values
        long x = abs((long) this.x);
        long y = abs((long) this.y);

        // Calculate the tangent of the angle
        double tg = (double) y / (double) x;

        // Horizontal
        if (tg < TG_22D) {
            if (this.x > 0) return Direction.RIGHT;
            else return Direction.LEFT;
        }

        // Vertical
        if (tg > TG_67D) {
            if (this.y > 0) return Direction.DOWN;
            else return Direction.UP;
        }

        // Diagonal
        if (this.x > 0) {
            if (this.y > 0) return Direction.DOWN_RIGHT;
            else return Direction.UP_RIGHT;
        } else {
            if (this.y > 0) return Direction.DOWN_LEFT;
            else return Direction.UP_LEFT;
        }
    }
}