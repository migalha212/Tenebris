package com.ldts.t14g01.Tenebris.model.arena.projectile;

import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.projectiles.ExplosiveBulletView;

public class ExplosiveBullet extends Projectile {
    private static final HitBoX HIT_BOX = new HitBoX(new Vector2D(-2, -2), new Vector2D(4, 4));
    private static final int VELOCITY = 3;

    public ExplosiveBullet(Vector2D position, Vector2D.Direction direction, int damage) {
        super(position, HIT_BOX, direction, VELOCITY, damage);
        this.view = new ExplosiveBulletView(this);
    }
}
