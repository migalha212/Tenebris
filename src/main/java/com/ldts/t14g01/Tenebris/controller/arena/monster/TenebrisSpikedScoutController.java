package com.ldts.t14g01.Tenebris.controller.arena.monster;

import com.ldts.t14g01.Tenebris.model.arena._commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena.entities.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entities.Entity;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.TenebrisSpikedScout;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.ElementProvider;
import com.ldts.t14g01.Tenebris.utils.Bounce;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.Set;
import java.util.TreeSet;

public class TenebrisSpikedScoutController extends MonsterController<TenebrisSpikedScout> {
    public TenebrisSpikedScoutController(TenebrisSpikedScout model) {
        super(model);
    }

    @Override
    public void update(Vector2D dylanPosition, ElementProvider elementProvider, CommandHandler commandHandler) {
        Set<Entity.State> movingState = new TreeSet<>();
        this.model.setLooking(null);

        // Calculate where to move to
        Vector2D monsterPosition = this.model.getPosition();
        Vector2D direction = dylanPosition.minus(monsterPosition);

        // Calculate moving states if not too far to reach
        if (isDylanVisible(monsterPosition, dylanPosition, elementProvider)) {
            if (direction.magnitude() <= this.model.getVisionRange()) switch (direction.getMajorDirection()) {
                case UP -> movingState.add(Entity.State.BACK);
                case DOWN -> movingState.add(Entity.State.FRONT);
                case LEFT -> movingState.add(Entity.State.LEFT);
                case RIGHT -> movingState.add(Entity.State.RIGHT);
                case UP_RIGHT -> {
                    movingState.add(Entity.State.BACK);
                    movingState.add(Entity.State.RIGHT);
                }
                case UP_LEFT -> {
                    movingState.add(Entity.State.BACK);
                    movingState.add(Entity.State.LEFT);
                }
                case DOWN_RIGHT -> {
                    movingState.add(Entity.State.FRONT);
                    movingState.add(Entity.State.RIGHT);
                }
                case DOWN_LEFT -> {
                    movingState.add(Entity.State.FRONT);
                    movingState.add(Entity.State.LEFT);
                }
            }
        }

        // If bouncing overwrite
        Bounce bounce = this.model.getBounce();
        if (bounce != null) {
            bounce.tick();
            if (bounce.isOver()) this.model.setBounce(null);
            else {
                movingState = new TreeSet<>();
                this.model.setLooking(Entity.State.IDLE);
                switch (bounce.getDirection()) {
                    case UP -> movingState.add(Dylan.State.BACK);
                    case DOWN -> movingState.add(Dylan.State.FRONT);
                    case LEFT -> movingState.add(Dylan.State.LEFT);
                    case RIGHT -> movingState.add(Dylan.State.RIGHT);
                    case UP_RIGHT -> {
                        movingState.add(Dylan.State.BACK);
                        movingState.add(Dylan.State.RIGHT);
                    }
                    case UP_LEFT -> {
                        movingState.add(Dylan.State.BACK);
                        movingState.add(Dylan.State.LEFT);
                    }
                    case DOWN_RIGHT -> {
                        movingState.add(Dylan.State.FRONT);
                        movingState.add(Dylan.State.RIGHT);
                    }
                    case DOWN_LEFT -> {
                        movingState.add(Dylan.State.FRONT);
                        movingState.add(Dylan.State.LEFT);
                    }
                    case null, default -> {
                    }
                }
                this.model.setMoving(movingState);
            }
        }

        // Update state and move
        this.model.setMoving(movingState);
        this.model.move();
    }
}
