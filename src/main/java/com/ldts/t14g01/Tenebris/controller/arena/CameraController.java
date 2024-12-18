package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.Camera;
import com.ldts.t14g01.Tenebris.model.arena.animation.Animation;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class CameraController {
    private static final int BORDER_RATIO = 100;
    Camera model;

    public CameraController(Camera model) {
        this.model = model;
    }

    public void update(Vector2D dylanPosition) {
        // Execute animation
        Animation animation = this.model.getAnimation();
        if (animation != null) {
            if (animation.isOver()) this.model.setAnimation(null);
            else {
                animation.execute();
                return;
            }
        }

        Vector2D cameraPosition = this.model.getPosition();
        dylanPosition = dylanPosition.minus(cameraPosition);
        Vector2D arenaSize = GUI.getGUI().getWindowSize();

        Vector2D max = arenaSize.multiply(0.5 + (double) BORDER_RATIO / 200);
        Vector2D min = arenaSize.minus(max);

        if (dylanPosition.x() < min.x())
            if (BORDER_RATIO == 100) cameraPosition = triggerArenaChange(cameraPosition, Vector2D.Direction.LEFT);
            else
                cameraPosition = new Vector2D(
                        cameraPosition.x() - (min.x() - dylanPosition.x()),
                        cameraPosition.y()
                );
        if (dylanPosition.x() > max.x())
            if (BORDER_RATIO == 100) cameraPosition = triggerArenaChange(cameraPosition, Vector2D.Direction.RIGHT);
            else
                cameraPosition = new Vector2D(
                        cameraPosition.x() + (dylanPosition.x() - max.x()),
                        cameraPosition.y()
                );

        if (dylanPosition.y() < min.y())
            if (BORDER_RATIO == 100) cameraPosition = triggerArenaChange(cameraPosition, Vector2D.Direction.DOWN);
            else
                cameraPosition = new Vector2D(
                        cameraPosition.x(),
                        cameraPosition.y() - (min.y() - dylanPosition.y())
                );
        if (dylanPosition.y() > max.y())
            if (BORDER_RATIO == 100) cameraPosition = triggerArenaChange(cameraPosition, Vector2D.Direction.UP);
            else
                cameraPosition = new Vector2D(
                        cameraPosition.x(),
                        cameraPosition.y() + (dylanPosition.y() - max.y())
                );

        this.model.setPosition(cameraPosition);
    }

    private Vector2D triggerArenaChange(Vector2D cameraPosition, Vector2D.Direction direction) {
        Vector2D screenSize = GUI.getGUI().getWindowSize();
        switch (direction) {
            case UP -> {
                return cameraPosition.add(new Vector2D(
                        0,
                        screenSize.y()
                ));
            }
            case DOWN -> {
                return cameraPosition.minus(new Vector2D(
                        0,
                        screenSize.y()
                ));
            }
            case RIGHT -> {
                return cameraPosition.add(new Vector2D(
                        screenSize.x(),
                        0
                ));
            }
            case LEFT -> {
                return cameraPosition.minus(new Vector2D(
                        screenSize.x(),
                        0
                ));
            }
            case null, default -> throw new RuntimeException("Invalid Direction");
        }
    }
}
