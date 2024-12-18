package com.ldts.t14g01.Tenebris.view.arena.entity;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entities.Dylan;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class DylanView extends EntityView<Dylan> {
    public DylanView(Dylan model) {
        super(model);
    }

    @Override
    public void draw(Vector2D cameraOffset) {
        this.updateState(); // Select correct animation type
        this.tickState(); // Cycle animation frames
        GUI.getGUI().drawDylan(this.model.getPosition().minus(cameraOffset), this.state);
    }
}
