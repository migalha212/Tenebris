package com.ldts.t14g01.Tenebris.model.arena.entity;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.Moves;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public abstract class Entity extends GameElement implements Moves, TakesDamage {
    private final int velocity;
    private int hp;

    public Entity(Vector2D position, int hp, int velocity) {
        super(position);
        this.velocity = velocity;
        this.hp = hp;
    }

    protected int getVelocity() {
        return this.velocity;
    }

    @Override
    public void interact(GameElement other) {
        // ToDo: Add Interactions with Damages Entities Objects
    }

    @Override
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }
}