package com.ldts.t14g01.Tenebris.model.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.staticelement.BreakableWall;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BreakableWallTest {
    private BreakableWall breakableWall;

    @BeforeEach
    void setUp() {
        Vector2D position = new Vector2D(1, 1);
        breakableWall = new BreakableWall(position, 10);
    }

    @Test
    void isAliveTest() {
        Assertions.assertTrue(breakableWall.isAlive());
    }

    @Test
    void isAliveTest2() {
        breakableWall.takeDamage(9);
        Assertions.assertTrue(breakableWall.isAlive());
    }

    @Test
    void isNotAliveTest() {
        breakableWall.takeDamage(20);
        Assertions.assertFalse(breakableWall.isAlive());
    }
}
