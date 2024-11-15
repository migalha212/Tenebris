package com.ldts.t14g01.Tenebris.utils;

import java.util.Objects;
import java.util.Random;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void set(Position p) {
        this.x = p.x;
        this.y = p.y;
    }

    public void moveUp() {
        this.y--;
    }

    public void moveDown() {
        this.y++;
    }

    public void moveRight() {
        this.x++;
    }

    public void moveLeft() {
        this.x--;
    }

    public Position plus(Position position) {
        return new Position(
                this.x + position.x,
                this.y + position.y
        );
    }

    public Position minus(Position position) {
        return new Position(
                this.x - position.x,
                this.y - position.y
        );
    }

    public static Position random(int maxX, int maxY) {
        return new Position(
                new Random().nextInt(maxX),
                new Random().nextInt(maxY)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
