package com.ldts.t14g01.Tenebris.utils;

import static java.lang.Math.min;

public class Vector {
    private final double magnitude;
    private final double angle;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        UP_RIGHT,
        UP_LEFT,
        DOWN_RIGHT,
        DOWN_LEFT
    }

    public Vector(Position position) {
        this.magnitude = Math.sqrt(position.x() * position.x() + position.y() * position.y());
        this.angle = Math.atan2(position.y(), position.x());
    }

    public Vector(Direction direction, double magnitude) {
        this.magnitude = magnitude;
        switch (direction) {
            case UP -> this.angle = -Math.PI / 2;
            case DOWN -> this.angle = Math.PI / 2;
            case LEFT -> this.angle = Math.PI;
            case UP_RIGHT -> this.angle = -Math.PI / 4;
            case UP_LEFT -> this.angle = -Math.PI * 3 / 4;
            case DOWN_RIGHT -> this.angle = Math.PI / 4;
            case DOWN_LEFT -> this.angle = Math.PI * 3 / 4;
            case null, default -> this.angle = 0;
        }
    }

    private Vector(double angle, double magnitude) {
        this.angle = angle;
        this.magnitude = magnitude;
    }

    public Position toXY() {
        return new Position(
                (int) (this.magnitude * Math.cos(this.angle)),
                (int) (this.magnitude * Math.sin(this.angle))
        );
    }

    public Vector add(Vector v) {
        double x1 = this.magnitude * Math.cos(this.angle);
        double y1 = this.magnitude * Math.sin(this.angle);
        double x2 = v.magnitude * Math.cos(v.angle);
        double y2 = v.magnitude * Math.sin(v.angle);
        double x3 = x1 + x2;
        double y3 = y1 + y2;
        return new Vector(
                Math.sqrt(x3 * x3 + y3 * y3),
                Math.atan2(y3, x3)
        );

    }

    public Vector limit(double magnitude) {
        return new Vector(
                this.angle,
                min(this.magnitude, magnitude)
        );
    }
}
