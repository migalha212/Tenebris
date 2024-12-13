package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.model.arena.entity.Entity;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.TenebrisPeon;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.Set;
import java.util.TreeSet;

public class TenebrisPeonController extends MonsterController<TenebrisPeon> {
    public TenebrisPeonController(TenebrisPeon model) {
        super(model);
    }

    @Override
    public void update(Vector2D dylanPosition) {
        Vector2D monsterPosition = this.model.getPosition();
        Vector2D direction = dylanPosition.minus(monsterPosition);

        // Calculate where to move to
        Set<Entity.State> movingState = new TreeSet<>();

        // Calculate moving states if not too far to reach
        if (direction.magnitude() <= this.model.getRange())
            switch (direction.majorDirection(10)) {
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

        // Update state and move
        this.model.setLooking(null);
        this.model.setMoving(movingState);
        this.model.move();
    }
}
