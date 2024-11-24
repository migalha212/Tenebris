package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.utils.Position;

public abstract class GameElement {
    protected Position position;
    protected final int size;

    public GameElement(Position position, int size) {
        this.position = position;
        this.size = size;
    }

    public Position getPosition() {
        return position;
    }

    public int getSize() {
        return this.size;
    }

    public abstract void interact(GameElement other);
}
