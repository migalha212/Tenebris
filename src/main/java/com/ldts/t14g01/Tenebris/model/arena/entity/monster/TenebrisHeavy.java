package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisHeavyController;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisHeavyView;

public class TenebrisHeavy extends Monster {
    private static final int RANGE = 50;
    private static final int HP = 50;
    private static final int VELOCITY = 1;
    private static final int DAMAGE = 10;

    public TenebrisHeavy(Vector2D position) {
        super(position, HP, VELOCITY, DAMAGE, RANGE);
        this.view = new TenebrisHeavyView(this);
        this.controller = new TenebrisHeavyController(this);
    }
}
