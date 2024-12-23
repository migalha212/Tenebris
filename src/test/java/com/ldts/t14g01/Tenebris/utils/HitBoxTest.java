package com.ldts.t14g01.Tenebris.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HitBoxTest {
    private HitBox hitBox1;
    private HitBox hitBox2;

    @BeforeEach
    void setUp() {
        hitBox1 = new HitBox(new Vector2D(-1, -1), new Vector2D(2, 2));
        hitBox2 = new HitBox(new Vector2D(-2, -2), new Vector2D(4, 4));
    }

    @Test
    void collideTest() {
        Vector2D position1 = new Vector2D(0, 0);

        // HitBox In the same position collide
        Assertions.assertTrue(HitBox.collide(position1, hitBox1, position1, hitBox2));

        // HitBox 1 covers x from -1 to 1, HitBox 2 covers x from 1 to 5
        Vector2D position2 = new Vector2D(3, 0);
        Assertions.assertTrue(HitBox.collide(position1, hitBox1, position2, hitBox2));

        // HitBox 1 covers x from -1 to 1, HitBox 2 covers x from 2 to 6
        position2 = new Vector2D(4, 0);
        Assertions.assertFalse(HitBox.collide(position1, hitBox1, position2, hitBox2));

        // HitBox 1 covers x from -1 to 1, HitBox 2 covers x from -1 to -5
        position2 = new Vector2D(-3, 0);
        Assertions.assertTrue(HitBox.collide(position1, hitBox1, position2, hitBox2));

        // HitBox 1 covers x from -1 to 1, HitBox 2 covers x from -2 to -6
        position2 = new Vector2D(-4, 0);
        Assertions.assertFalse(HitBox.collide(position1, hitBox1, position2, hitBox2));

        // HitBox 1 covers y from -1 to 1, HitBox 2 covers y from 1 to 5
        position2 = new Vector2D(0, 3);
        Assertions.assertTrue(HitBox.collide(position1, hitBox1, position2, hitBox2));

        // HitBox 1 covers y from -1 to 1, HitBox 2 covers y from 6 to 2
        position2 = new Vector2D(0, 4);
        Assertions.assertFalse(HitBox.collide(position1, hitBox1, position2, hitBox2));

        // HitBox 1 covers y from -1 to 1, HitBox 2 covers y from -5 to -1
        position2 = new Vector2D(0, -3);
        Assertions.assertTrue(HitBox.collide(position1, hitBox1, position2, hitBox2));

        // HitBox 1 covers y from -1 to 1, HitBox 2 covers y from -6 to -2
        position2 = new Vector2D(0, -4);
        Assertions.assertFalse(HitBox.collide(position1, hitBox1, position2, hitBox2));
    }
}