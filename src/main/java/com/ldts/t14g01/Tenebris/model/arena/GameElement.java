package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.animation.Animation;
import com.ldts.t14g01.Tenebris.utils.HitBox;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

import java.util.List;

public abstract class GameElement {
    protected ElementView view;
    protected Animation animation;
    protected Vector2D position;
    protected HitBox hitBox;

    public GameElement(Vector2D position, HitBox hitBox) {
        this.position = position;
        this.hitBox = hitBox;
        this.animation = null;
    }

    public ElementView getView() {
        return this.view;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Vector2D getPosition() {
        return position;
    }

    public HitBox getHitBox() {
        return this.hitBox;
    }

    public abstract List<Command> interact(GameElement other);
}
