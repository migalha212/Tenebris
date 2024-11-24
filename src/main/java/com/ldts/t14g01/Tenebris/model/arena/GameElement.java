package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.utils.Vector2D;

public abstract class GameElement {
    protected Vector2D position;
    protected final int size;

    public GameElement(Vector2D position, int size) {
        this.position = position;
        this.size = size;
    }

    public Vector2D getPosition() {
        return position;
    }

    public int getSize() {
        return this.size;
    }

    public abstract void interact(GameElement other);
}
