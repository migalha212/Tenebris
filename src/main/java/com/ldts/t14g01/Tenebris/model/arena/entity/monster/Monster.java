package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.model.arena.entity.Entity;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesPlayer;
import com.ldts.t14g01.Tenebris.utils.Position;

public class Monster extends Entity implements DamagesPlayer {
    private final int damage;

    public Monster(Position position, int size, int hp, int maxVelocity, int damage) {
        super(position, size, hp, maxVelocity);
        this.damage = damage;
    }

    @Override
    public int getPlayerDamage() {
        return this.damage;
    }
}
