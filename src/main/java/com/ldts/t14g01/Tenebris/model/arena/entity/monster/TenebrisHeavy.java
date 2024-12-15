package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.TenebrisHeavyController;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.monster.TenebrisHeavyView;

public class TenebrisHeavy extends Monster {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-6, -6), new Vector2D(10, 13));
    private static final int HP = 50;
    private static final int VELOCITY = 1;
    private static final int DAMAGE = 10;
    private static final int VISION_RANGE = 50;

    public TenebrisHeavy(Vector2D position) {
        super(position, hitBoX, HP, VELOCITY, DAMAGE, VISION_RANGE);
        this.view = new TenebrisHeavyView(this);
        this.controller = new TenebrisHeavyController(this);
    }
}
