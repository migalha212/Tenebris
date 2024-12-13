package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.TenebrisPeonController;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.TenebrisPeonView;

public class TenebrisPeon extends Monster {
    private static final int RANGE = 150;

    public TenebrisPeon(Vector2D position, int hp, int velocity, int damage) {
        super(position, hp, velocity, damage, RANGE);
        this.view = new TenebrisPeonView(this);
        this.controller = new TenebrisPeonController(this);
    }
}
