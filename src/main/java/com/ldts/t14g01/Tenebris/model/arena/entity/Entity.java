package com.ldts.t14g01.Tenebris.model.arena.entity;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.Moves;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class Entity extends GameElement implements Moves, TakesDamage {
    public Vector2D velocity, acceleration;
    private final int maxVelocity;
    private int hp;


    public Entity(Vector2D position, int size, int hp, int maxVelocity) {
        super(position, size);
        this.hp = hp;
        this.maxVelocity = maxVelocity;
        this.acceleration = this.velocity = new Vector2D(0, 0);
    }

    @Override
    public void interact(GameElement other) {
        // ToDo: Add Interactions with Damages Entities Objects
    }

    @Override
    public void move() {
        this.position = this.position.add(this.velocity);
    }

    @Override
    public void accelerate() {
        this.velocity = this.velocity.add(this.acceleration).multiply(0.8).limit(this.maxVelocity);
    }

    @Override
    public void bounce(Vector2D.Direction direction) {
        double vx = this.velocity.x();
        double vy = this.velocity.y();

        // This logic assures the velocity gets the right direction no mather the case
        switch (direction) {
            case UP -> vy = Math.min(vy, vy * -1);
            case DOWN -> vy = Math.max(vy, vy * -1);
            case LEFT -> vx = Math.max(vx, vx * -1);
            case RIGHT -> vx = Math.min(vx, vx * -1);
        }

        this.velocity = new Vector2D((int) vx, (int) vy).limit(this.maxVelocity);
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