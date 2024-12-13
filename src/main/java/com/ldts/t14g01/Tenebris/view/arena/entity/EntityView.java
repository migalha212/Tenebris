package com.ldts.t14g01.Tenebris.view.arena.entity;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.Entity;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public abstract class EntityView<T extends Entity> extends ElementView<T> {
    protected static final int ANIMATION_SPEED = 10;
    protected GUI.AnimationState state;
    protected int frameCounter;

    public EntityView(T model) {
        super(model);
        this.frameCounter = 0;
        this.state = GUI.AnimationState.IDLE_1;
    }

    protected void tickState() {
        this.frameCounter++;
        if (frameCounter < ANIMATION_SPEED) return;

        frameCounter = 0;
        switch (this.state) {
            case IDLE_1 -> this.state = GUI.AnimationState.IDLE_2;
            case FRONT_2 -> this.state = GUI.AnimationState.FRONT_1;
            case FRONT_1 -> this.state = GUI.AnimationState.FRONT_2;
            case BACK_2 -> this.state = GUI.AnimationState.BACK_1;
            case BACK_1 -> this.state = GUI.AnimationState.BACK_2;
            case LEFT_2 -> this.state = GUI.AnimationState.LEFT_1;
            case LEFT_1 -> this.state = GUI.AnimationState.LEFT_2;
            case RIGHT_2 -> this.state = GUI.AnimationState.RIGHT_1;
            case RIGHT_1 -> this.state = GUI.AnimationState.RIGHT_2;
            case null, default -> this.state = GUI.AnimationState.IDLE_1;
        }
    }

    protected void either(GUI.AnimationState state1, GUI.AnimationState state2) {
        if (this.state != state1 && this.state != state2) this.state = state1;
    }

    protected void updateState() {
        // Only not idle if moving
        if (this.model.getMoving() == Dylan.State.IDLE) {
            this.either(GUI.AnimationState.IDLE_1, GUI.AnimationState.IDLE_2);
            return;
        }

        // If the player is looking somewhere then face that direction
        // Otherwise face the direction the player is moving
        switch (this.model.getLooking()) {
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
                    case null, default -> this.either(GUI.AnimationState.IDLE_1, GUI.AnimationState.IDLE_2);
                }
            }
        }
    }
}
