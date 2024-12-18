package com.ldts.t14g01.Tenebris.controller.arena.monster;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.Monster;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.BlocksVision;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.ElementProvider;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public abstract class MonsterController<T extends Monster> {
    T model;

    public MonsterController(T model) {
        this.model = model;
    }

    public abstract void update(Vector2D dylanPosition, ElementProvider elementProvider, CommandHandler commandHandler);

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