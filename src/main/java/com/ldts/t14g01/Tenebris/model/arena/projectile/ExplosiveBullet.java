package com.ldts.t14g01.Tenebris.model.arena.projectile;

import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class ExplosiveBullet extends Projectile {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(0, 0), new Vector2D(0, 0));
    private final Vector2D velocity;
    private final int damage;

    public ExplosiveBullet(Vector2D position, int damage, Vector2D velocity) {
        super(position, hitBoX);
        this.velocity = velocity;
        this.damage = damage;
    }

    @Override
    public int getEntityDamage() {
        return this.damage;
    }
}
