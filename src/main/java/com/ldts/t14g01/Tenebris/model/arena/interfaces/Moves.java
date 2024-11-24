package com.ldts.t14g01.Tenebris.model.arena.interfaces;

import com.ldts.t14g01.Tenebris.utils.Vector;

public interface Moves {
    void move();

    void accelerate();

    void bounce(Vector.Direction direction);
}
