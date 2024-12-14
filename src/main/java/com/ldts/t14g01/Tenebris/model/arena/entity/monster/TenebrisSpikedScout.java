package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisSpikedScoutController;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisSpikedScoutView;

public class TenebrisSpikedScout extends Monster {
    private static final int RANGE = 45;
    private static final int HP = 15;
    private static final int VELOCITY = 2;
    private static final int DAMAGE = 35;

    public TenebrisSpikedScout(Vector2D position) {
        super(position, HP, VELOCITY, DAMAGE, RANGE);
        this.view = new TenebrisSpikedScoutView(this);
        this.controller = new TenebrisSpikedScoutController(this);
    }
}
