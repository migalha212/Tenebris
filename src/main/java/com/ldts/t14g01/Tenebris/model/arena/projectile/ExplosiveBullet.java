package com.ldts.t14g01.Tenebris.model.arena.projectile;

public class ExplosiveBullet extends Projectile{
    private int explosionRadius;

    public ExplosiveBullet(int x, int y, int damage, double creationTime, double lifeTime, int explosionRadius) {
        super(x, y, damage, creationTime, lifeTime);
        this.explosionRadius = explosionRadius;
    }

    public void setExplosionRadius(int explosionRadius) {
        this.explosionRadius = explosionRadius;
    }

    public int getExplosionRadius() {
        return explosionRadius;
    }
}
