package com.ldts.t14g01.Tenebris.model.arena.interfaces;

import com.ldts.t14g01.Tenebris.utils.Vector2D;

public interface Moves {
    void move();

    void accelerate();

    void bounce(Vector2D.Direction direction);
}
