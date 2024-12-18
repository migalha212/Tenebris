package com.ldts.t14g01.Tenebris.model.arena.animation;

import com.ldts.t14g01.Tenebris.model.arena.entities.Entity;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Bounce extends Animation<Entity> {
    private static final int NUMBER_OF_MOVEMENTS = 10;
    private static final double DECAY_FACTOR = 0.8;

    private final List<Vector2D> movements;
    private int frameCount;

    public Bounce(Entity model, Vector2D.Direction direction) {
        super(model);
        this.movements = new ArrayList<>();
        this.frameCount = 0;

        int velocity = 7;

        for (int i = 0; i < NUMBER_OF_MOVEMENTS; i++) {
            double decay = Math.pow(DECAY_FACTOR, i);
            int x;
            int y;

            switch (direction) {
                case RIGHT, DOWN_RIGHT, UP_RIGHT -> x = (int) (velocity * decay);
                case LEFT, UP_LEFT, DOWN_LEFT -> x = (int) (-velocity * decay);
                default -> x = 0;
            }
            switch (direction) {
                case UP, UP_LEFT, UP_RIGHT -> y = (int) (-velocity * decay);
                case DOWN, DOWN_LEFT, DOWN_RIGHT -> y = (int) (velocity * decay);
                default -> y = 0;
            }

            this.movements.add(new Vector2D(x, y));
        }
    }

    @Override
    public boolean isOver() {
        return frameCount >= movements.size();
    }

    @Override
    public void execute() {
        if (frameCount >= movements.size()) return;

        Vector2D movement = this.movements.get(this.frameCount);
        this.model.setPosition(this.model.getPosition().add(movement));

        this.frameCount++;
    }
}
