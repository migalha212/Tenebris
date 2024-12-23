package com.ldts.t14g01.Tenebris.model.arena.entities.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisSpikedScoutController;
import com.ldts.t14g01.Tenebris.utils.HitBox;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisSpikedScoutView;

public class TenebrisSpikedScout extends Monster {
    private static final HitBox hitBoX = new HitBox(new Vector2D(-5, -5), new Vector2D(8, 12));

    public TenebrisSpikedScout(Vector2D position, int hp, int velocity, int damage, int visionRange) {
        super(position, hitBoX, hp, velocity, damage, visionRange);
        this.view = new TenebrisSpikedScoutView(this);
        this.controller = new TenebrisSpikedScoutController(this);
    }
}
