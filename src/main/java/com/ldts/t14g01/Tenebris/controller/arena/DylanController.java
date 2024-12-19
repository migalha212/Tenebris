package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena._commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena.animation.Animation;
import com.ldts.t14g01.Tenebris.model.arena.entities.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entities.Entity;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.Set;
import java.util.TreeSet;

public class DylanController {
    private final Dylan model;

    public DylanController(Dylan model) {
        this.model = model;
    }

    public void updateWeapon(Action action) {
        switch (action) {
            case SELECT_1 -> this.model.setSelectedWeapon(0);
            case SELECT_2 -> this.model.setSelectedWeapon(1);
            case RELOAD -> this.model.getEquipedWeapon().getController().reload();
            case null, default -> {
            }
        }
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

    public void shoot(CommandHandler commandHandler) {
        Vector2D bulletPosition = this.model.getPosition();

        Entity.State aimState = this.model.getLooking();
        if (aimState == null || aimState == Entity.State.IDLE) aimState = this.model.getMoving();

        Vector2D.Direction direction;
        switch (aimState) {
            case FRONT -> {
                direction = Vector2D.Direction.DOWN;
                bulletPosition = bulletPosition.add(new Vector2D(0, 13));
            }
            case BACK -> {
                direction = Vector2D.Direction.UP;
                bulletPosition = bulletPosition.add(new Vector2D(0, -15));
            }
            case LEFT -> {
                direction = Vector2D.Direction.LEFT;
                bulletPosition = bulletPosition.add(new Vector2D(-14, 0));
            }
            case null, default -> {
                direction = Vector2D.Direction.RIGHT;
                bulletPosition = bulletPosition.add(new Vector2D(10, 0));
            }
        }

        this.model.getEquipedWeapon().getController().shoot(commandHandler, bulletPosition, direction);
    }

    public void update() {
        // Tick Weapon Timer
        this.model.getEquipedWeapon().getController().update();

        // If in animation execute
        Animation animation = this.model.getAnimation();
        if (animation != null) {
            if (animation.isOver()) this.model.setAnimation(null);
            else {
                animation.execute();
                return;
            }
        }

        this.model.move();
    }
}
