package com.ldts.t14g01.Tenebris.view.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.particles.DeathBlood;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class DeathBloodView extends ElementView<DeathBlood> {
    public DeathBloodView(DeathBlood model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawDeathBlood(this.model.getPosition(), this.model.getCurrentFrame());
    }
}
