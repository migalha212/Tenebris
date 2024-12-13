package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.model.arena.entity.Entity;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesPlayer;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class Monster extends Entity implements DamagesPlayer {
    private final int damage;

    public Monster(Vector2D position, int hp, int velocity, int damage) {
        super(position, hp, velocity);
        this.damage = damage;
    }

    @Override
    public int getPlayerDamage() {
        return this.damage;
    }

    @Override
    public void move() {
    }

    @Override
    public void bounce(Vector2D.Direction direction) {
    }
}
