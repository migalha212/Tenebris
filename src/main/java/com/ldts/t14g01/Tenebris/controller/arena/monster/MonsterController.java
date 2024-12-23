package com.ldts.t14g01.Tenebris.controller.arena.monster;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena.animation.Animation;
import com.ldts.t14g01.Tenebris.model.arena.entities.Entity;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.Monster;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.BlocksVision;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.ElementProvider;
import com.ldts.t14g01.Tenebris.utils.HitBox;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.Set;
import java.util.TreeSet;

public abstract class MonsterController<T extends Monster> {
    T model;

    public MonsterController(T model) {
        this.model = model;
    }

    public void update(Vector2D dylanPosition, ElementProvider elementProvider, CommandHandler commandHandler) {
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

        // Calculate where to move to
        Vector2D monsterPosition = this.model.getPosition();
        Vector2D direction = dylanPosition.minus(monsterPosition);

        // Calculate moving states if not too far to reach
        if (this.isDylanVisible(monsterPosition, dylanPosition, elementProvider))
            if (this.isPathClear(monsterPosition, dylanPosition, elementProvider))
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

        // Update state and move
        this.model.setMoving(movingState);
        this.model.move();
    }

    protected boolean isDylanVisible(Vector2D monsterPosition, Vector2D dylanPosition, ElementProvider elementProvider) {
        Vector2D direction = dylanPosition.minus(monsterPosition);
        int steps = (int) direction.magnitude();
        Vector2D unitStep = direction.multiply(1.0 / steps);

        Vector2D currentPoint = monsterPosition;

        for (int i = 0; i <= steps; i++) {
            for (GameElement element : elementProvider.getElements()) {
                if (element instanceof BlocksVision)
                    if (isCircleIntersectingLine(monsterPosition, dylanPosition, element.getPosition(), 9))
                        return false;
            }
            currentPoint = currentPoint.add(unitStep);
        }

        return true;
    }

    protected boolean isPathClear(Vector2D monsterPosition, Vector2D dylanPosition, ElementProvider elementProvider) {
        Vector2D.Direction direction = dylanPosition.minus(monsterPosition).getMajorDirection();

        Vector2D newPosition;

        switch (direction) {
            case DOWN -> newPosition = this.model.getPosition().add(new Vector2D(0, this.model.getVelocity()));
            case UP -> newPosition = this.model.getPosition().add(new Vector2D(0, -this.model.getVelocity()));
            case LEFT -> newPosition = this.model.getPosition().add(new Vector2D(-this.model.getVelocity(), 0));
            case RIGHT -> newPosition = this.model.getPosition().add(new Vector2D(this.model.getVelocity(), 0));
            case DOWN_RIGHT -> newPosition = this.model.getPosition().add(new Vector2D(this.model.getVelocity(), 0));
            case UP_RIGHT -> newPosition = this.model.getPosition().add(new Vector2D(this.model.getVelocity(), 0));
            case UP_LEFT -> newPosition = this.model.getPosition().add(new Vector2D(this.model.getVelocity(), 0));
            case DOWN_LEFT -> newPosition = this.model.getPosition().add(new Vector2D(this.model.getVelocity(), 0));
            case null, default -> throw new RuntimeException("Invalid Direction");
        }

        // Check if moving will cause damage
        for (GameElement element : elementProvider.getElements())
            if (HitBox.collide(element.getPosition(), element.getHitBox(), newPosition, this.model.getHitBox()))
                return false;

        return true;
    }

    private boolean isCircleIntersectingLine(Vector2D p1, Vector2D p2, Vector2D circleCenter, double radius) {
        Vector2D lineVector = p2.minus(p1);
        Vector2D pointToCircle = circleCenter.minus(p1);
        double t = (double) pointToCircle.dot(lineVector) / lineVector.dot(lineVector);
        t = Math.max(0, Math.min(1, t));
        Vector2D closestPoint = p1.add(lineVector.multiply(t));
        double distance = closestPoint.minus(circleCenter).magnitude();
        return distance <= radius;
    }

}