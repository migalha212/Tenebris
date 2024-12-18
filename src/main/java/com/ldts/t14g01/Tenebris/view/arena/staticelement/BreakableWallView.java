package com.ldts.t14g01.Tenebris.view.arena.staticelement;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.static_elements.BreakableWall;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class BreakableWallView extends ElementView<BreakableWall> {
    public BreakableWallView(BreakableWall model) {
        super(model);
    }

    @Override
    public void draw(Vector2D cameraOffset) {
        GUI.getGUI().drawStaticElement(this.model.getPosition().minus(cameraOffset), GUI.StaticElement.BREAKABLE_WALL);
    }
}
