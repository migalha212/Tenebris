package com.ldts.t14g01.Tenebris.view.arena.staticelement;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.static_elements.SandBag;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class SandbagView extends ElementView<SandBag> {
    public SandbagView(SandBag model) {
        super(model);
    }

    @Override
    public void draw() {
        GUI.getGUI().drawStaticElement(this.model.getPosition(), GUI.StaticElement.SANDBAG);
    }
}
