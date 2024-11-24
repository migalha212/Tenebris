package com.ldts.t14g01.Tenebris.model.arena.entity;

import com.ldts.t14g01.Tenebris.controller.arena.entity.DylanController;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.utils.Position;
import com.ldts.t14g01.Tenebris.view.arena.entity.DylanView;

public class Dylan extends Entity implements TakesDamage {
    public Dylan(Position position, int size, int hp, int maxVelocity) {
        super(position, size, hp, maxVelocity);
    }

    public DylanController getController() {
        return new DylanController(this);
    }

    public DylanView getView() {
        return new DylanView(this);
    }

    @Override
    public void interact(GameElement other) {
        super.interact(other);
        // ToDo: Implement Interactions with DamagesPlayer Objects
    }
}
