package com.ldts.t14g01.Tenebris.model.arena.entity;

import com.ldts.t14g01.Tenebris.controller.arena.DylanController;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.DylanView;

import java.io.IOException;
import java.util.Set;

public class Dylan extends Entity implements TakesDamage {
    private final DylanController controller;
    private State looking;
    private Set<State> moving;

    public enum State {
        IDLE,
        LEFT,
        RIGHT,
        FRONT,
        BACK
    }

    public Dylan(Vector2D position, int hp, int velocity) throws IOException {
        super(position, hp, velocity);
        this.view = new DylanView(this);
        this.controller = new DylanController(this);
    }

    public DylanController getController() {
        return this.controller;
    }

    public State getLooking() {
        return looking;
    }

    public void setLooking(State looking) {
        this.looking = looking;
    }

    public void setMoving(Set<State> moving) {
        this.moving = moving;
    }

    public State getMoving() {
        // When moving in multiple directions
        // The left and right movement have a higher visual priority
        if (this.moving.contains(State.LEFT) && !this.moving.contains(State.RIGHT)) return State.LEFT;
        if (this.moving.contains(State.RIGHT) && !this.moving.contains(State.LEFT)) return State.RIGHT;
        if (this.moving.contains(State.FRONT) && !this.moving.contains(State.BACK)) return State.FRONT;
        if (this.moving.contains(State.BACK) && !this.moving.contains(State.FRONT)) return State.BACK;
        return State.IDLE;
    }

    @Override
    public void move() {
        this.moving.forEach(moveState -> {
            switch (moveState) {
                case FRONT -> this.position = this.position.add(new Vector2D(0, this.getVelocity()));
                case BACK -> this.position = this.position.add(new Vector2D(0, -this.getVelocity()));
                case LEFT -> this.position = this.position.add(new Vector2D(-this.getVelocity(), 0));
                case RIGHT -> this.position = this.position.add(new Vector2D(this.getVelocity(), 0));
                case null, default -> {
                }
            }
        });
    }

    @Override
    public void bounce(Vector2D.Direction direction) {

    }

    @Override
    public void interact(GameElement other) {
        super.interact(other);
        // ToDo: Implement Interactions with DamagesPlayer Objects
    }
}
