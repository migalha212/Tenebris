package com.ldts.t14g01.Tenebris.model.arena.particles;

import com.ldts.t14g01.Tenebris.controller.arena.ParticleController;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public abstract class Particle extends GameElement {
    protected ParticleController controller;
    private int currentFrame = 1;

    public Particle(Vector2D position) {
        super(position, new HitBoX(new Vector2D(0, 0), new Vector2D(0, 0)));
        this.controller = new ParticleController(this);
    }

    public int getCurrentFrame() {
        return this.currentFrame;
    }

    public abstract boolean isOver();

    public void update() {
        this.currentFrame++;
    }

    public ParticleController getController() {
        return this.controller;
    }

    // This elements don't interact
    @Override
    public List<Command> interact(GameElement other) {
        return new ArrayList<>();
    }
}
