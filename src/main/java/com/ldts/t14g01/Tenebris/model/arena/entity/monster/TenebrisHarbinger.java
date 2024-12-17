package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisHarbingerController;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisHarbingerView;

public class TenebrisHarbinger extends Monster {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-8,-7), new Vector2D(14,14));
    private static final int HP = 20;
    private static final int VELOCITY = 1;
    private static final int DAMAGE = 35;
    private static final int VISION_RANGE = 250;
    public static final int SHOOTING_RANGE = 100;

    private final int shootingCoolDown;

    public TenebrisHarbinger(Vector2D position) {
        super(position, hitBoX, HP, VELOCITY, DAMAGE, VISION_RANGE);
        this.view = new TenebrisHarbingerView(this);
        this.controller = new TenebrisHarbingerController(this);
        this.shootingCoolDown = 30;
    }

    public int getShootingCoolDown() {
        return this.shootingCoolDown;
    }
}
