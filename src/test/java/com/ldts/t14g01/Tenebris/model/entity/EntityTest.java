package com.ldts.t14g01.Tenebris.model.entity;

import com.ldts.t14g01.Tenebris.model.arena.entities.Entity;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntityTest {
    private Entity entity;

    @BeforeEach
    void setUp() {
        Vector2D position = new Vector2D(1, 1);
        int size = 18 / 2;
        int hp = 10;
        int maxVelocity = 5;
        entity = new Entity(position, new HitBoX(new Vector2D(0, 0), new Vector2D(0, 0)), hp, maxVelocity) {
            @Override
            public void move() {
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
