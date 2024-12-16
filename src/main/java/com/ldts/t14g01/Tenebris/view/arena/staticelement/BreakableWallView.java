package com.ldts.t14g01.Tenebris.view.arena.staticelement;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.BreakableWall;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class BreakableWallView extends ElementView<BreakableWall> {
    public BreakableWallView(BreakableWall model){
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawBreakableWall(this.model.getPosition());
    }
}
