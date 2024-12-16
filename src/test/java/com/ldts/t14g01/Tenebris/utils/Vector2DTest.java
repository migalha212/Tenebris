package com.ldts.t14g01.Tenebris.utils;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Negative;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.*;

public class Vector2DTest {
    private static final int BASE_TEST_NUMBER = 1_000;
    private Vector2D vector;

    // Data
    @Property(tries = BASE_TEST_NUMBER)
    void magnitudeProperty1(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals(abs((double) x), new Vector2D(x, 0).magnitude());
        Assertions.assertEquals(abs((double) y), new Vector2D(0, y).magnitude());
    }

    @Test
    void magnitudeTest1() {
        vector = new Vector2D(0, 0);
        Assertions.assertEquals(0, vector.magnitude());
    }

    @Test
    void magnitudeTest2() {
        vector = new Vector2D(3, 4);
        Assertions.assertEquals(5, vector.magnitude());
    }

    @Test
    void magnitudeTest3() {
        vector = new Vector2D(-3, -4);
        Assertions.assertEquals(5, vector.magnitude());
    }

    // Math
    @Property(tries = BASE_TEST_NUMBER)
    void addProperty1(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals(vector, vector.add(new Vector2D(0, 0)));
    }

    @Property(tries = BASE_TEST_NUMBER)
    void addProperty2(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals(new Vector2D(0, 0), vector.add(new Vector2D(-x, -y)));
    }

    @Property(tries = BASE_TEST_NUMBER)
    void addProperty3(@ForAll int x, @ForAll int y, @ForAll int a) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals(vector.x() + a, vector.add(new Vector2D(a, 0)).x());
        Assertions.assertEquals(vector.y() + a, vector.add(new Vector2D(0, a)).y());
    }

    @Property(tries = BASE_TEST_NUMBER)
    void minusProperty1(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals(vector, vector.minus(new Vector2D(0, 0)));
    }

    @Property(tries = BASE_TEST_NUMBER)
    void minusProperty2(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals(new Vector2D(0, 0), vector.minus(vector));
    }

    @Property(tries = BASE_TEST_NUMBER)
    void minusProperty1(@ForAll int x, @ForAll int y, @ForAll int a) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals(vector.x() - a, vector.minus(new Vector2D(a, 0)).x());
        Assertions.assertEquals(vector.y() - a, vector.minus(new Vector2D(0, a)).y());
    }

    @Property(tries = BASE_TEST_NUMBER)
    void multiplyProperty1(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals(new Vector2D(0, 0), vector.multiply(0));
    }

    @Property(tries = BASE_TEST_NUMBER)
    void multiplyProperty2(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals(vector, vector.multiply(1));
    }

    @Property(tries = BASE_TEST_NUMBER)
    void multiplyProperty3(@ForAll int x, @ForAll int y, @ForAll double k) {
        vector = new Vector2D(x, y);
        Assertions.assertEquals((int) (vector.x() * k), vector.multiply(k).x());
        Assertions.assertEquals((int) (vector.y() * k), vector.multiply(k).y());
    }

    // Util
    @Test
    void getMajorDirectionTest1() {
        vector = new Vector2D(0, 0);
        Assertions.assertEquals(Vector2D.Direction.UP, vector.getMajorDirection());
    }

    @Test
    void getMajorDirectionTest2() {
        vector = new Vector2D(1, 0);
        Assertions.assertEquals(Vector2D.Direction.RIGHT, vector.getMajorDirection());
        vector = new Vector2D(-1, 0);
        Assertions.assertEquals(Vector2D.Direction.LEFT, vector.getMajorDirection());
        vector = new Vector2D(0, 1);
        Assertions.assertEquals(Vector2D.Direction.DOWN, vector.getMajorDirection());
        vector = new Vector2D(0, -1);
        Assertions.assertEquals(Vector2D.Direction.UP, vector.getMajorDirection());
    }

    @Test
    void getMajorDirectionTest3() {
        vector = new Vector2D(1, 1);
        Assertions.assertEquals(Vector2D.Direction.DOWN_RIGHT, vector.getMajorDirection());
        vector = new Vector2D(1, -1);
        Assertions.assertEquals(Vector2D.Direction.UP_RIGHT, vector.getMajorDirection());
        vector = new Vector2D(-1, 1);
        Assertions.assertEquals(Vector2D.Direction.DOWN_LEFT, vector.getMajorDirection());
        vector = new Vector2D(-1, -1);
        Assertions.assertEquals(Vector2D.Direction.UP_LEFT, vector.getMajorDirection());
    }

    @Property(tries = BASE_TEST_NUMBER)
    void getMajorDirectionProperty1(@ForAll @Positive int x) {
        vector = new Vector2D(x, 0);
        Assertions.assertEquals(Vector2D.Direction.RIGHT, vector.getMajorDirection());
    }

    @Property(tries = BASE_TEST_NUMBER)
    void getMajorDirectionProperty2(@ForAll @Negative int x) {
        vector = new Vector2D(x, 0);
        Assertions.assertEquals(Vector2D.Direction.LEFT, vector.getMajorDirection());
    }

    @Property(tries = BASE_TEST_NUMBER)
    void getMajorDirectionProperty3(@ForAll @Positive int y) {
        vector = new Vector2D(0, y);
        Assertions.assertEquals(Vector2D.Direction.DOWN, vector.getMajorDirection());
    }

    @Property(tries = BASE_TEST_NUMBER)
    void getMajorDirectionProperty4(@ForAll @Negative int y) {
        vector = new Vector2D(0, y);
        Assertions.assertEquals(Vector2D.Direction.UP, vector.getMajorDirection());
    }

    @Property(tries = BASE_TEST_NUMBER * 100)
    void getMajorDirectionProperty5(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);

        double at = atan2(y, x);
        Vector2D.Direction direction;
        if (x == 0 && y == 0) direction = Vector2D.Direction.UP;
        else if (at > 7 * PI / 8) direction = Vector2D.Direction.LEFT;
        else if (at > 5 * PI / 8) direction = Vector2D.Direction.DOWN_LEFT;
        else if (at > 3 * PI / 8) direction = Vector2D.Direction.DOWN;
        else if (at > 1 * PI / 8) direction = Vector2D.Direction.DOWN_RIGHT;
        else if (at > -1 * PI / 8) direction = Vector2D.Direction.RIGHT;
        else if (at > -3 * PI / 8) direction = Vector2D.Direction.UP_RIGHT;
        else if (at > -5 * PI / 8) direction = Vector2D.Direction.UP;
        else if (at > -7 * PI / 8) direction = Vector2D.Direction.UP_LEFT;
        else direction = Vector2D.Direction.LEFT;

        Assertions.assertEquals(direction, vector.getMajorDirection());
    }

    @Property(tries = BASE_TEST_NUMBER)
    void dotProperty1(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);
        Vector2D zero = new Vector2D(0, 0);
        Assertions.assertEquals(0, vector.dot(zero));
        Assertions.assertEquals(0, zero.dot(vector));
    }

    @Property(tries = BASE_TEST_NUMBER)
    void dotProperty2(@ForAll int x, @ForAll int y) {
        vector = new Vector2D(x, y);
        Vector2D zero = new Vector2D(1, 1);
        Assertions.assertEquals((long) x + y, vector.dot(zero));
        Assertions.assertEquals((long) x + y, zero.dot(vector));
    }

    @Property(tries = BASE_TEST_NUMBER * 10)
    void dotProperty3(@ForAll int x, @ForAll int y, @ForAll int a, @ForAll int b) {
        vector = new Vector2D(x, y);
        Vector2D vector2 = new Vector2D(a, b);
        Assertions.assertEquals(vector.dot(vector2), vector2.dot(vector));
    }

    @Test
    void dotTest1() {
        vector = new Vector2D(1, 1);
        Assertions.assertEquals(2, vector.dot(vector));
    }
}