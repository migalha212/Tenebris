package com.ldts.t14g01.Tenebris.utils;

import static java.lang.Math.abs;

public record Vector2D(int x, int y) {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT
    }

    // Data
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    // Math
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D minus(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }

    public Vector2D multiply(double k) {
        return new Vector2D((int) (x * k), (int) (y * k));
    }

    // Util
    public Vector2D limit(double maxMagnitude) {
        double magnitude = this.magnitude();
        if (magnitude > maxMagnitude)
            return new Vector2D((int) (x / magnitude * maxMagnitude), (int) (y / magnitude * maxMagnitude));
        return new Vector2D(x, y);
    }

    public boolean inRange(Vector2D p2, int range) {
        int distance = (int) Math.sqrt((x - p2.x) * (x - p2.x) + (y - p2.y) * (y - p2.y));
        return distance <= range;
    }

    public Direction majorDirection(int diagonalThreshold) {
        // If distance is almost diagonal
        if (abs(abs(x) - abs(y)) < diagonalThreshold) {
            if (x > 0) {
                if (y > 0) return Direction.DOWN_RIGHT;
                else return Direction.UP_RIGHT;
            } else {
                if (y > 0) return Direction.DOWN_LEFT;
                else return Direction.UP_LEFT;
            }
        }

        // Mostly Horizontal
        if (abs(x) > abs(y)) {
            if (x > 0) return Direction.RIGHT;
            else return Direction.LEFT;
        }

        // Mostly Vertical
        if (y > 0) return Direction.DOWN;
        else return Direction.UP;
    }

    public static Vector2D fromDirection(Direction direction, double magnitude) {
        double angle = 0;
        switch (direction) {
            case UP -> angle = -Math.PI / 2;
            case DOWN -> angle = Math.PI / 2;
            case LEFT -> angle = Math.PI;
            case UP_RIGHT -> angle = -Math.PI / 4;
            case UP_LEFT -> angle = -Math.PI * 3 / 4;
            case DOWN_RIGHT -> angle = Math.PI / 4;
            case DOWN_LEFT -> angle = Math.PI * 3 / 4;
            case null, default -> angle = 0;
        }
        return new Vector2D((int) (magnitude * Math.cos(angle)), (int) (magnitude * Math.sin(angle)));
    }

    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
