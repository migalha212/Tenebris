package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena.Commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena.Commands.CreateProjectile;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.Entity;
import com.ldts.t14g01.Tenebris.model.arena.projectile.Bullet;
import com.ldts.t14g01.Tenebris.model.arena.projectile.ExplosiveBullet;
import com.ldts.t14g01.Tenebris.utils.Bounce;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.Set;
import java.util.TreeSet;

public class DylanController {
    private final Dylan model;

    public DylanController(Dylan model) {
        this.model = model;
    }

    public void setSelectedWeapon(Action action) {
        switch (action) {
            case SELECT_1 -> this.model.setSelectedWeapon(1);
            case SELECT_2 -> this.model.setSelectedWeapon(2);
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
            case null, default -> {
            }
        }
        this.model.setMoving(moving);
    }

    public void shoot(CommandHandler commandHandler) {
        Vector2D bulletPosition = this.model.getPosition();

        Entity.State aimState = this.model.getLooking();
        if (aimState == null || aimState == Entity.State.IDLE) aimState = this.model.getMoving();

        Vector2D.Direction direction;
        switch (aimState) {
            case FRONT -> {
                direction = Vector2D.Direction.DOWN;
                bulletPosition = bulletPosition.add(new Vector2D(0, 10));
            }
            case BACK -> {
                direction = Vector2D.Direction.UP;
                bulletPosition = bulletPosition.add(new Vector2D(0, -10));
            }
            case LEFT -> {
                direction = Vector2D.Direction.LEFT;
                bulletPosition = bulletPosition.add(new Vector2D(-10, 0));
            }
            case null, default -> {
                direction = Vector2D.Direction.RIGHT;
                bulletPosition = bulletPosition.add(new Vector2D(10, 0));
            }
        }

        // If cooldown is over, shoot
        int weapon = this.model.getSelectedWeapon();
        if (this.model.canShoot(weapon)) {
            this.model.resetTimer(weapon);

            if (weapon == 1)
                commandHandler.handleCommand(new CreateProjectile(new Bullet(bulletPosition, direction, 10)));
            if (weapon == 2)
                commandHandler.handleCommand(new CreateProjectile(new ExplosiveBullet(bulletPosition, direction, 20)));
        }
    }

    public void update() {
        // Tick Fire CoolDown Counter
        this.model.tickWeaponTimers();

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
