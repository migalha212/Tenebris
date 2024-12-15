package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisSpikedScoutController;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisSpikedScoutView;

public class TenebrisSpikedScout extends Monster {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-5,-5), new Vector2D(8,12));
    private static final int HP = 15;
    private static final int VELOCITY = 1;
    private static final int DAMAGE = 35;
    private static final int VISION_RANGE = 45;

    public TenebrisSpikedScout(Vector2D position) {
        super(position, hitBoX, HP, VELOCITY, DAMAGE, VISION_RANGE);
        this.view = new TenebrisSpikedScoutView(this);
        this.controller = new TenebrisSpikedScoutController(this);
    }
}
