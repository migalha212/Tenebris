package com.ldts.t14g01.Tenebris.model.arena.entities.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisWardenController;
import com.ldts.t14g01.Tenebris.utils.HitBox;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisWardenView;

public class TenebrisWarden extends Monster {
    private static final HitBox hitBoX = new HitBox(new Vector2D(-12, -11), new Vector2D(22, 25));

    public TenebrisWarden(Vector2D position, int hp, int velocity, int damage, int visionRange) {
        super(position, hitBoX, hp, velocity, damage, visionRange);
        this.view = new TenebrisWardenView(this);
        this.controller = new TenebrisWardenController(this);
    }
}
