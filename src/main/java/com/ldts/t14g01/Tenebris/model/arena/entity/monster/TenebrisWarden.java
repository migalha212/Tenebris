package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisPeonController;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisWardenView;

public class TenebrisWarden extends Monster {
    private static final int RANGE = 50;
    private static final int HP = 75;
    private static final int VELOCITY = 1;
    private static final int DAMAGE = 35;

    public TenebrisWarden(Vector2D position) {
        super(position, HP, VELOCITY, DAMAGE, RANGE);
        this.view = new TenebrisWardenView(this);
        this.controller = new TenebrisPeonController.TenebrisWardenController(this);
    }
}
