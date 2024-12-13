package com.ldts.t14g01.Tenebris.view.arena;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class ArenaView extends View<Arena> {

    public ArenaView(Arena model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        Arena arena = this.getModel();
        gui.drawArenaBackGround();

        // Draw Dylan
        arena.getDylan().getView().draw(gui);
    }
}
