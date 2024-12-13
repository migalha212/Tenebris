package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisHarbingerController;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisHarbingerView;

public class TenebrisHarbinger extends Monster {
    private static final int RANGE = 70;
    private static final int HP = 20;
    private static final int VELOCITY = 1;
    private static final int DAMAGE = 35;

    public TenebrisHarbinger(Vector2D position) {
        super(position, HP, VELOCITY, DAMAGE, RANGE);
        this.view = new TenebrisHarbingerView(this);
        this.controller = new TenebrisHarbingerController(this);
    }
}
