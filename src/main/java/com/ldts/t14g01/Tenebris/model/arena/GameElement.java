package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public abstract class GameElement {
    protected ElementView view;
    protected Vector2D position;

    public GameElement(Vector2D position) {
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }

    public ElementView getView() {
        return this.view;
    }

    public abstract void interact(GameElement other);
}
