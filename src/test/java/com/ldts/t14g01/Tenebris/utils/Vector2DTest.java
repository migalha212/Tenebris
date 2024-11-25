package com.ldts.t14g01.Tenebris.utils;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Vector2DTest {
    private Vector2D vector2D;
    private int x;
    private int y;

    @BeforeEach
    void setUp() {
        this.x = 4;
        this.y = 3;
        this.vector2D = new Vector2D(x, y);
    }

    @Property
    void addTest(@ForAll int x, @ForAll int y) {
        Vector2D vector2D = new Vector2D(x, y);
        Assertions.assertEquals(vector2D.x() + x, vector2D.add(new Vector2D(x, y)).x());
        Assertions.assertEquals(vector2D.y() + y, vector2D.add(new Vector2D(x, y)).y());
    }

    @Property
    void multiplyTest(@ForAll int x, @ForAll int y, @ForAll double k) {
        Vector2D vector2D = new Vector2D(x, y);
        Assertions.assertEquals((int) (vector2D.x() * k), vector2D.multiply(k).x());
        Assertions.assertEquals((int) (vector2D.y() * k), vector2D.multiply(k).y());
    }

    //magnitude equal or smaller than the limit
    @Test
    void equalLimitTest() {
        double magnitude = vector2D.magnitude();
        Assertions.assertEquals(vector2D, vector2D.limit(magnitude));
    }

    @Test
    void biggerLimitTest() {
        double magnitude = vector2D.magnitude() + 10;
        Assertions.assertEquals(vector2D, vector2D.limit(magnitude));
    }

    @Test
    void smallerLimitTest() {
        double magnitude = vector2D.magnitude() - 4;
        Assertions.assertEquals(new Vector2D((int) (vector2D.x() / 5.0),(int) (vector2D.y() / 5.0)), vector2D.limit(magnitude));
    }

    @Test
    void notInRangeTest(){
        int range = 2;
        Vector2D vector2D1 = new Vector2D(7, 3);
        Assertions.assertFalse(vector2D.inRange(vector2D1,range));
        vector2D1 = new Vector2D(4,6);
        Assertions.assertFalse(vector2D.inRange(vector2D1,range));
    }
    @Test
    void inRangeTest(){
        int range = 2;
        Vector2D vector2D1 = new Vector2D(2, 3);
        Assertions.assertTrue(vector2D.inRange(vector2D1,range));
        vector2D1 = new Vector2D(2,2);
        Assertions.assertTrue(vector2D.inRange(vector2D1,range));
        vector2D1 = new Vector2D(4,3);
        Assertions.assertTrue(vector2D.inRange(vector2D1,range));
    }

    @Test
    void fromDirectionTest() {
        double magnitude = 2;
        Vector2D.Direction direction = Vector2D.Direction.UP;
        Assertions.assertEquals(new Vector2D(0, (int) (-1 * magnitude)), Vector2D.fromDirection(direction, magnitude));
        direction = Vector2D.Direction.DOWN;
        Assertions.assertEquals(new Vector2D(0, (int) (1 * magnitude)), Vector2D.fromDirection(direction, magnitude));
        direction = Vector2D.Direction.LEFT;
        Assertions.assertEquals(new Vector2D((int) (-1 * magnitude), 0), Vector2D.fromDirection(direction, magnitude));
        direction = Vector2D.Direction.RIGHT;
        Assertions.assertEquals(new Vector2D((int) (1 * magnitude), 0), Vector2D.fromDirection(direction, magnitude));

        magnitude = 10;
        direction = Vector2D.Direction.UP_RIGHT;
        Assertions.assertEquals(
                new Vector2D((int) (((Math.sqrt(2) * magnitude) / 2)),
                        (int) ((-Math.sqrt(2) * magnitude) / 2)),
                Vector2D.fromDirection(direction, magnitude));

        direction = Vector2D.Direction.UP_LEFT;
        Assertions.assertEquals(
                new Vector2D((int) ((-(Math.sqrt(2) * magnitude) / 2)),
                        (int) ((-Math.sqrt(2) * magnitude) / 2)),
                Vector2D.fromDirection(direction, magnitude));

        direction = Vector2D.Direction.DOWN_RIGHT;
        Assertions.assertEquals(
                new Vector2D((int) (((Math.sqrt(2) * magnitude) / 2)),
                        (int) ((Math.sqrt(2) * magnitude) / 2)),
                Vector2D.fromDirection(direction, magnitude));

        direction = Vector2D.Direction.DOWN_LEFT;
        Assertions.assertEquals(
                new Vector2D((int) (((-Math.sqrt(2) * magnitude) / 2)),
                        (int) ((Math.sqrt(2) * magnitude) / 2)),
                Vector2D.fromDirection(direction, magnitude));

        direction = null;
        Assertions.assertEquals(new Vector2D((int) (magnitude), 0), Vector2D.fromDirection(direction, magnitude));
    }
}
