package com.ldts.t14g01.Tenebris.view.arena.staticelement;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Wall;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class WallView extends ElementView<Wall> {
    public WallView(Wall model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawWall(this.model.getPosition());
    }
}
