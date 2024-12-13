package com.ldts.t14g01.Tenebris.view.arena.entity;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class DylanView extends ElementView<Dylan> {
    private static final int ANIMATION_SPEED = 10;
    private int frameCounter;
    private GUI.Dylan state;

    public DylanView(Dylan model) {
        super(model);
        this.frameCounter = 0;
        this.state = GUI.Dylan.IDLE_1;
    }

    private void tickState() {
        this.frameCounter++;
        if (frameCounter < ANIMATION_SPEED) return;

        frameCounter = 0;
        switch (this.state) {
            case IDLE_1 -> this.state = GUI.Dylan.IDLE_2;
            case FRONT_2 -> this.state = GUI.Dylan.FRONT_1;
            case FRONT_1 -> this.state = GUI.Dylan.FRONT_2;
            case BACK_2 -> this.state = GUI.Dylan.BACK_1;
            case BACK_1 -> this.state = GUI.Dylan.BACK_2;
            case LEFT_2 -> this.state = GUI.Dylan.LEFT_1;
            case LEFT_1 -> this.state = GUI.Dylan.LEFT_2;
            case RIGHT_2 -> this.state = GUI.Dylan.RIGHT_1;
            case RIGHT_1 -> this.state = GUI.Dylan.RIGHT_2;
            case null, default -> this.state = GUI.Dylan.IDLE_1;
        }
    }

    private void either(GUI.Dylan state1, GUI.Dylan state2) {
        if (this.state != state1 && this.state != state2) this.state = state1;
    }

    private void updateState() {
        // Only not idle if moving
        if (this.model.getMoving() == Dylan.State.IDLE) {
            this.either(GUI.Dylan.IDLE_1, GUI.Dylan.IDLE_2);
            return;
        }

        // If the player is looking somewhere then face that direction
        // Otherwise face the direction the player is moving
        switch (this.model.getLooking()) {
            case LEFT -> this.either(GUI.Dylan.LEFT_1, GUI.Dylan.LEFT_2);
            case RIGHT -> this.either(GUI.Dylan.RIGHT_1, GUI.Dylan.RIGHT_2);
            case FRONT -> this.either(GUI.Dylan.FRONT_1, GUI.Dylan.FRONT_2);
            case BACK -> this.either(GUI.Dylan.BACK_1, GUI.Dylan.BACK_2);
            case null, default -> {
                switch (this.model.getMoving()) {
                    case LEFT -> this.either(GUI.Dylan.LEFT_1, GUI.Dylan.LEFT_2);
                    case RIGHT -> this.either(GUI.Dylan.RIGHT_1, GUI.Dylan.RIGHT_2);
                    case FRONT -> this.either(GUI.Dylan.FRONT_1, GUI.Dylan.FRONT_2);
                    case BACK -> this.either(GUI.Dylan.BACK_1, GUI.Dylan.BACK_2);
                    case null, default -> this.either(GUI.Dylan.IDLE_1, GUI.Dylan.IDLE_2);
                }
            }
        }
    }

    @Override
    public void draw(GUI gui) {
        this.updateState(); // Select correct animation type
        this.tickState(); // Cycle animation frames
        gui.drawDylan(this.model.getPosition(), this.state);
    }
}
