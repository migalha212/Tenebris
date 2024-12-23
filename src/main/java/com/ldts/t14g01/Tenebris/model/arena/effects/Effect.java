package com.ldts.t14g01.Tenebris.model.arena.effects;

import com.ldts.t14g01.Tenebris.controller.arena.EffectController;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.utils.HitBox;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public abstract class Effect extends GameElement {
    protected EffectController controller;
    private int currentFrame = 1;

    public Effect(Vector2D position, HitBox hitBox) {
        super(position, hitBox);
        this.controller = new EffectController(this);
    }

    public EffectController getController() {
        return this.controller;
    }

    public int getCurrentFrame() {
        return this.currentFrame;
    }

    public void update() {
        this.currentFrame++;
    }

    public abstract boolean isOver();

    // This elements don't react
    @Override
    public List<Command> interact(GameElement other) {
        return new ArrayList<>();
    }
}
