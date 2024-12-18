package com.ldts.t14g01.Tenebris.controller.arena.monster;

import com.ldts.t14g01.Tenebris.model.arena._commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena._commands.CreateProjectile;
import com.ldts.t14g01.Tenebris.model.arena.animation.Animation;
import com.ldts.t14g01.Tenebris.model.arena.entities.Entity;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.TenebrisHarbinger;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.ElementProvider;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.Spell;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.Set;
import java.util.TreeSet;

public class TenebrisHarbingerController extends MonsterController<TenebrisHarbinger> {
    private int shootingTick;

    public TenebrisHarbingerController(TenebrisHarbinger model) {
        super(model);
        this.shootingTick = 0;
    }

    @Override
    public void update(Vector2D dylanPosition, ElementProvider elementProvider, CommandHandler commandHandler) {
        this.shootingTick++;

        // If in animation execute
        Animation animation = this.model.getAnimation();
        if (animation != null) {
            if (animation.isOver()) this.model.setAnimation(null);
            else {
                animation.execute();
                return;
            }
        }

        Set<Entity.State> movingState = new TreeSet<>();
        this.model.setLooking(null);

        // Calculate direction to Dylan
        Vector2D monsterPosition = this.model.getPosition();
        Vector2D direction = dylanPosition.minus(monsterPosition);

        // If Monster can see Dylan
        if (this.isDylanVisible(monsterPosition, dylanPosition, elementProvider)) {
            // If close enough to shoot
            if (direction.magnitude() <= this.model.getShootingRange()) {
                Vector2D position = this.model.getPosition();

                switch (direction.getMajorDirection()) {
                    case UP -> position = position.add(new Vector2D(0, -10));
                    case DOWN -> position = position.add(new Vector2D(0, 10));
                    case LEFT -> position = position.add(new Vector2D(-10, 0));
                    case RIGHT -> position = position.add(new Vector2D(10, 0));

                    case UP_RIGHT -> position = position.add(new Vector2D(10, -10));
                    case UP_LEFT -> position = position.add(new Vector2D(-10, -10));
                    case DOWN_RIGHT -> position = position.add(new Vector2D(10, 10));
                    case DOWN_LEFT -> position = position.add(new Vector2D(-10, 10));
                }

                if (this.shootingTick >= this.model.getShootingCoolDown()) {
                    commandHandler.handleCommand(new CreateProjectile(new Spell(position, direction.getMajorDirection(), 10)));
                    this.shootingTick = 0;
                }
            }

            // If Close Enough to see move towards him
            else if (direction.magnitude() <= this.model.getVisionRange())
                if (this.isPathClear(monsterPosition, dylanPosition, elementProvider))
                    switch (direction.getMajorDirection()) {
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

        // Update state and move
        this.model.setMoving(movingState);
        this.model.move();
    }
}
