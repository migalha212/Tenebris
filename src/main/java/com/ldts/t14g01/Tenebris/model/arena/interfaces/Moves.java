package com.ldts.t14g01.Tenebris.model.arena.interfaces;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;

public interface Moves {
    void move();

    void collide(GameElement other);
}
