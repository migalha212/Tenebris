package com.ldts.t14g01.Tenebris.model.arena.projectile;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;

public class Projectile extends GameElement {
    private int damage;
    private double creationTime;
    private double lifeTime;

    public Projectile(int x, int y, int damage, double creationTime, double lifeTime) {
        super(x, y);
        this.damage = damage;
        this.creationTime = creationTime;
        this.lifeTime = lifeTime;
    }

    public double getLifeTime() {
        return lifeTime;
    }

    public int getDamage() {
        return damage;
    }

    public double getCreationTime() {
        return creationTime;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setLifeTime(double lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void setCreationTime(double creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isAlive(double currentTime){
        return currentTime - creationTime < lifeTime;
    }
}
