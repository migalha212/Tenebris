package com.ldts.t14g01.Tenebris.view.arena.entity;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;

public class DylanView extends EntityView<Dylan> {
    public DylanView(Dylan model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        this.updateState(); // Select correct animation type
        this.tickState(); // Cycle animation frames
        gui.drawDylan(this.model.getPosition(), this.state);
    }
}
