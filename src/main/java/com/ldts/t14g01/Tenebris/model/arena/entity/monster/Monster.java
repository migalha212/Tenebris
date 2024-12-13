package com.ldts.t14g01.Tenebris.model.arena.entity.monster;

import com.ldts.t14g01.Tenebris.controller.arena.monster.MonsterController;
import com.ldts.t14g01.Tenebris.model.arena.entity.Entity;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesPlayer;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public abstract class Monster extends Entity implements DamagesPlayer {
    protected MonsterController controller;
    private final int damage;
    private final int range;

    public Monster(Vector2D position, int hp, int velocity, int damage, int range) {
        super(position, hp, velocity);
        this.controller = null;
        this.damage = damage;
        this.range = range;
    }

    public MonsterController getController() {
        return this.controller;
    }

    public int getRange() {
        return this.range;
    }

    @Override
    public int getPlayerDamage() {
        return this.damage;
    }
}
