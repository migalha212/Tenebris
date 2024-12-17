package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisPeonController;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisPeonView;

public class TenebrisPeon extends Monster {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-5, -5), new Vector2D(8, 13));

    public TenebrisPeon(Vector2D position, int hp, int velocity, int damage, int visionRange) {
        super(position, hitBoX, hp, velocity, damage, visionRange);
        this.view = new TenebrisPeonView(this);
        this.controller = new TenebrisPeonController(this);
    }
}
