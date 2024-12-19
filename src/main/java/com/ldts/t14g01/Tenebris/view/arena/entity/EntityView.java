package com.ldts.t14g01.Tenebris.view.arena.entity;

import com.ldts.t14g01.Tenebris.Tenebris;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entities.Entity;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public abstract class EntityView<T extends Entity> extends ElementView<T> {
    private static final int ANIMATION_SPEED_MS = 333;
    private static final int ANIMATION_FRAME_COUNT = Tenebris.FPS * ANIMATION_SPEED_MS / 1000;

    protected GUI.AnimationState state;
    protected int frameCounter;

    public EntityView(T model) {
        super(model);
        this.state = GUI.AnimationState.IDLE_1;
        this.frameCounter = 0;
    }

    protected void tickState() {
        // Update Animation Frame Counter
        this.frameCounter++;
        if (frameCounter < ANIMATION_FRAME_COUNT) return;

        // Switch Animation State
        frameCounter = 0;
        switch (this.state) {
            case IDLE_1 -> this.state = GUI.AnimationState.IDLE_2;
            case IDLE_2 -> this.state = GUI.AnimationState.IDLE_1;
            case FRONT_2 -> this.state = GUI.AnimationState.FRONT_1;
            case FRONT_1 -> this.state = GUI.AnimationState.FRONT_2;
            case BACK_2 -> this.state = GUI.AnimationState.BACK_1;
            case BACK_1 -> this.state = GUI.AnimationState.BACK_2;
            case LEFT_2 -> this.state = GUI.AnimationState.LEFT_1;
            case LEFT_1 -> this.state = GUI.AnimationState.LEFT_2;
            case RIGHT_2 -> this.state = GUI.AnimationState.RIGHT_1;
            case RIGHT_1 -> this.state = GUI.AnimationState.RIGHT_2;
            case null, default -> throw new RuntimeException("Invalid Animation State");
        }
    }

    protected void either(GUI.AnimationState state1, GUI.AnimationState state2) {
        // Make sure the current State is either state1 or state2
        if (this.state != state1 && this.state != state2) this.state = state1;
    }

    protected void updateState() {
        // If not moving
        if (this.model.getMoving() == Entity.State.IDLE) {
            if (this.model.getLooking() != null) this.frameCounter = ANIMATION_FRAME_COUNT - 2;
            switch (this.model.getLooking()) {
                case LEFT -> this.state = GUI.AnimationState.LEFT_1;
                case RIGHT -> this.state = GUI.AnimationState.RIGHT_1;
                case FRONT -> this.state = GUI.AnimationState.FRONT_1;
                case BACK -> this.state = GUI.AnimationState.BACK_1;
                case null, default -> this.either(GUI.AnimationState.IDLE_1, GUI.AnimationState.IDLE_2);
            }
        } else {
            // If the entity is looking somewhere then face that direction
            // Otherwise face the direction the entity is moving
            switch (this.model.getLooking()) {
                case IDLE -> this.either(GUI.AnimationState.IDLE_1, GUI.AnimationState.IDLE_2);
                case LEFT -> this.either(GUI.AnimationState.LEFT_1, GUI.AnimationState.LEFT_2);
                case RIGHT -> this.either(GUI.AnimationState.RIGHT_1, GUI.AnimationState.RIGHT_2);
                case FRONT -> this.either(GUI.AnimationState.FRONT_1, GUI.AnimationState.FRONT_2);
                case BACK -> this.either(GUI.AnimationState.BACK_1, GUI.AnimationState.BACK_2);
                case null, default -> {
                    switch (this.model.getMoving()) {
                        case LEFT -> this.either(GUI.AnimationState.LEFT_1, GUI.AnimationState.LEFT_2);
                        case RIGHT -> this.either(GUI.AnimationState.RIGHT_1, GUI.AnimationState.RIGHT_2);
                        case FRONT -> this.either(GUI.AnimationState.FRONT_1, GUI.AnimationState.FRONT_2);
                        case BACK -> this.either(GUI.AnimationState.BACK_1, GUI.AnimationState.BACK_2);
                        case IDLE -> this.either(GUI.AnimationState.IDLE_1, GUI.AnimationState.IDLE_2);
                        case null, default -> throw new RuntimeException("Invalid Entity Moving State");
                    }
                }
            }
        }
    }
}
