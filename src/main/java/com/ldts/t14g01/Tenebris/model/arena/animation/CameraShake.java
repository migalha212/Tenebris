package com.ldts.t14g01.Tenebris.model.arena.animation;

import com.ldts.t14g01.Tenebris.model.arena.Camera;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CameraShake extends Animation<Camera> {
    private static final int NUMBER_OF_MOVEMENTS = 10;
    private static final double DECAY_FACTOR = 0.5;
    private static final int MAX_AMPLITUDE = 10;

    private final List<Vector2D> movements;
    private final Vector2D initialPosition;
    private int frameCount;


    public CameraShake(Camera model) {
        super(model);
        this.initialPosition = model.getPosition();
        this.movements = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_MOVEMENTS; i++) {
            double decay = Math.pow(DECAY_FACTOR, i);
            int x = (int) (random.nextGaussian() * MAX_AMPLITUDE * decay);
            int y = (int) (random.nextGaussian() * MAX_AMPLITUDE * decay);
            this.movements.add(new Vector2D(x, y));
        }

        // Reset back to where it started
        this.movements.add(new Vector2D(0, 0));
    }

    @Override
    public boolean isOver() {
        return frameCount >= movements.size();
    }

    @Override
    public void execute() {
        if (frameCount >= movements.size()) return;

        Vector2D movement = this.movements.get(this.frameCount);
        this.model.setPosition(this.initialPosition.add(movement));

        this.frameCount++;
    }
}
