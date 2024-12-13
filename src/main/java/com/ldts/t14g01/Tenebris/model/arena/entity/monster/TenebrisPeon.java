package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisPeonController;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisPeonView;

public class TenebrisPeon extends Monster {
    private static final int RANGE = 80;
    private static final int HP = 20;
    private static final int VELOCITY = 1;
    private static final int DAMAGE = 15;

    public TenebrisPeon(Vector2D position) {
        super(position, HP, VELOCITY, DAMAGE, RANGE);
        this.view = new TenebrisPeonView(this);
        this.controller = new TenebrisPeonController(this);
    }
}
