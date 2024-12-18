package com.ldts.t14g01.Tenebris.model.arena.entities.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisHeavyController;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisHeavyView;

public class TenebrisHeavy extends Monster {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-6, -6), new Vector2D(10, 13));

    public TenebrisHeavy(Vector2D position, int hp, int velocity, int damage, int visionRange) {
        super(position, hitBoX, hp, velocity, damage, visionRange);
        this.view = new TenebrisHeavyView(this);
        this.controller = new TenebrisHeavyController(this);
    }
}
