package com.ldts.t14g01.Tenebris.model.arena.projectiles;

import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.projectiles.BulletView;

public class Bullet extends Projectile {
    private static final HitBoX HIT_BOX = new HitBoX(new Vector2D(-3, -3), new Vector2D(5, 4));
    private static final int DAMAGE = 10;
    private static final int VELOCITY = 8;

    public Bullet(Vector2D position, Vector2D.Direction direction) {
        super(position, HIT_BOX, direction, VELOCITY, DAMAGE);
        this.view = new BulletView(this);
    }
}
