package com.ldts.t14g01.Tenebris.view.arena.staticelement;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Spike;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class SpikeView extends ElementView<Spike> {

    public SpikeView(Spike model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawSpikes(this.model.getPosition());
    }
}
