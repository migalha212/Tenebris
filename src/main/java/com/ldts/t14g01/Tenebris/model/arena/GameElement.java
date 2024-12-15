package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.Commands.Command;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

import java.util.List;

public abstract class GameElement {
    protected ElementView view;
    protected Vector2D position;
    protected HitBoX hitBox;

    public GameElement(Vector2D position, HitBoX hitBox) {
        this.position = position;
        this.hitBox = hitBox;
    }

    public Vector2D getPosition() {
        return position;
    }

    public ElementView getView() {
        return this.view;
    }

    public HitBoX getHitBox() {
        return this.hitBox;
    }

    public abstract List<Command> interact(GameElement other);
}
