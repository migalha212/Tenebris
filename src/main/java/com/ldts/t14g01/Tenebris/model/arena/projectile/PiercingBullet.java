package com.ldts.t14g01.Tenebris.model.arena.projectile;

public class PiercingBullet extends Projectile {
    private int collisionsRemaining;
    public PiercingBullet(int x, int y, int damage, double creationTime, double lifeTime,int collisionsRemaining) {
        super(x, y, damage, creationTime, lifeTime);
        this.collisionsRemaining = collisionsRemaining;
    }

    public int getCollisionsRemaining() {
        return collisionsRemaining;
    }

    public void setCollisionsRemaining(int collisionsRemaining) {
        this.collisionsRemaining = collisionsRemaining;
    }


}
