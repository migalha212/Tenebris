package com.ldts.t14g01.Tenebris.utils;

public class Bounce {
    private static final int BOUNCE_DURATION = 10;

    private final Vector2D.Direction direction;
    private int countDown;

    public Bounce(Vector2D.Direction direction) {
        this.direction = direction;
        this.countDown = BOUNCE_DURATION;
    }

    public Vector2D.Direction getDirection() {
        return direction;
    }

    public void tick() {
        this.countDown--;
    }

    public boolean isOver() {
        return this.countDown <= 0;
    }
}
