package com.ldts.t14g01.Tenebris.model.arena.entity;

public class Enemy extends Entity {
    private int range;
    private int detectionRadius;
    private int damage;

    public Enemy(int x, int y, int maxHp, double maxEnergy, int range, int detectionRadius, int damage) {
        super(x, y, maxHp, maxEnergy);
        this.range = range;
        this.detectionRadius = detectionRadius;
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public int getDetectionRadius() {
        return detectionRadius;
    }

    public int getDamage() {
        return damage;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setDetectionRadius(int detectionRadius) {
        this.detectionRadius = detectionRadius;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
