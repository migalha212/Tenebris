package com.ldts.t14g01.Tenebris.model.entity;

import com.ldts.t14g01.Tenebris.model.arena.entity.Entity;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntityTest {
    private Entity entity;

    @BeforeEach
    void setUp() {
        Vector2D position = new Vector2D(1, 1);
        int size = 18/2;
        int hp = 10;
        int maxVelocity = 5;
        entity = new Entity(position, size, hp, maxVelocity) {
            @Override
            public void move() {
            }

            @Override
            public void bounce(Vector2D.Direction direction) {
            }
        };
    }

    @Test
    void isAliveTest() {
        Assertions.assertTrue(entity.isAlive());
    }

    @Test
    void isAliveTest2() {
        entity.takeDamage(9);
        Assertions.assertTrue(entity.isAlive());
    }

    @Test
    void isNotAliveTest() {
        entity.takeDamage(20);
        Assertions.assertFalse(entity.isAlive());

    }
}
