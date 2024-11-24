package com.ldts.t14g01.Tenebris.utils;

public record Position(int x, int y) {

    public Position add(Vector velocity) {
        Position v = velocity.toXY();
        return new Position(
                this.x + v.x,
                this.y + v.y
        );
    }

    public static boolean inRange(Position p1, Position p2, int range) {
        int distance = (int) Math.sqrt(
                (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)
        );
        return distance <= range;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
