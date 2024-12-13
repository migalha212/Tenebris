package com.ldts.t14g01.Tenebris.model.arena.entity;

import com.ldts.t14g01.Tenebris.controller.arena.DylanController;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.DylanView;

import java.io.IOException;

public class Dylan extends Entity implements TakesDamage {
    private final DylanController controller;

    public Dylan(Vector2D position, int hp, int velocity) throws IOException {
        super(position, hp, velocity);
        this.view = new DylanView(this);
        this.controller = new DylanController(this);
    }

    public DylanController getController() {
        return this.controller;
    }

    @Override
    public void interact(GameElement other) {
        // Call Entity interaction handler
        super.interact(other);

        // ToDo: Implement Interactions with DamagesPlayer Objects
    }
}
