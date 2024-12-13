package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;

import java.util.Set;
import java.util.TreeSet;

public class DylanController {
    private final Dylan model;

    public DylanController(Dylan model) {
        this.model = model;
    }

    public void setMoving(Set<Action> actions) {
        Set<Dylan.State> moving = new TreeSet<>();
        actions.forEach(action -> {
            switch (action) {
                case MOVE_UP -> moving.add(Dylan.State.BACK);
                case MOVE_DOWN -> moving.add(Dylan.State.FRONT);
                case MOVE_LEFT -> moving.add(Dylan.State.LEFT);
                case MOVE_RIGHT -> moving.add(Dylan.State.RIGHT);
                case null, default -> {
                }
            }
        });
        this.model.setMoving(moving);
    }

    public void setLooking(Action action) {
        switch (action) {
            case LOOK_UP -> this.model.setLooking(Dylan.State.BACK);
            case LOOK_DOWN -> this.model.setLooking(Dylan.State.FRONT);
            case LOOK_LEFT -> this.model.setLooking(Dylan.State.LEFT);
            case LOOK_RIGHT -> this.model.setLooking(Dylan.State.RIGHT);
            case null, default -> this.model.setLooking(Dylan.State.IDLE);
        }
    }

    public void move() {
        this.model.move();
    }
}
