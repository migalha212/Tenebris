package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.Entity;
import com.ldts.t14g01.Tenebris.utils.Bounce;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.Set;
import java.util.TreeSet;

public class DylanController {
    private final Dylan model;

    public DylanController(Dylan model) {
        this.model = model;
    }

    public void setMoving(Set<Action> actions) {
        // Translate from Actions into States
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
            case null, default -> this.model.setLooking(null);
        }
    }

    public void overwriteMoving(Vector2D.Direction direction) {
        // Translate from Direction into States
        Set<Entity.State> moving = new TreeSet<>();
        switch (direction) {
            case UP -> moving.add(Dylan.State.BACK);
            case DOWN -> moving.add(Dylan.State.FRONT);
            case LEFT -> moving.add(Dylan.State.LEFT);
            case RIGHT -> moving.add(Dylan.State.RIGHT);
            case UP_RIGHT -> {
                moving.add(Dylan.State.BACK);
                moving.add(Dylan.State.RIGHT);
            }
            case UP_LEFT -> {
                moving.add(Dylan.State.BACK);
                moving.add(Dylan.State.LEFT);
            }
            case DOWN_RIGHT -> {
                moving.add(Dylan.State.FRONT);
                moving.add(Dylan.State.RIGHT);
            }
            case DOWN_LEFT -> {
                moving.add(Dylan.State.FRONT);
                moving.add(Dylan.State.LEFT);
            }
            case null, default -> {}
        }
        this.model.setMoving(moving);
    }

    public void move() {
        // If Bouncing
        // Tick Bounce
        // Overwrite moving and looking
        Bounce bounce = this.model.getBounce();
        if (bounce != null) {
            bounce.tick();
            if (bounce.isOver()) this.model.setBounce(null);
            else {
                this.overwriteMoving(bounce.getDirection());
                this.model.setLooking(Entity.State.IDLE);
            }
        }

        this.model.move();
    }
}
