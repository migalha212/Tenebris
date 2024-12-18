package com.ldts.t14g01.Tenebris.model.arena.entities.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisHarbingerController;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisHarbingerView;

public class TenebrisHarbinger extends Monster {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-8, -7), new Vector2D(14, 14));
    private final int shootingRange;
    private final int shootingCoolDown;

    public TenebrisHarbinger(Vector2D position, int hp, int velocity, int damage, int visionRange, int shootingRange) {
        super(position, hitBoX, hp, velocity, damage, visionRange);
        this.view = new TenebrisHarbingerView(this);
        this.controller = new TenebrisHarbingerController(this);
        this.shootingCoolDown = 30;
        this.shootingRange = shootingRange;
    }

    public int getShootingRange() {
        return this.shootingRange;
    }

    public int getShootingCoolDown() {
        return this.shootingCoolDown;
    }
}
