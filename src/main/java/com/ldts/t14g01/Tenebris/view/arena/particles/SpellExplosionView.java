package com.ldts.t14g01.Tenebris.view.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.particles.SpellExplosion;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class SpellExplosionView extends ElementView<SpellExplosion> {
    public SpellExplosionView(SpellExplosion model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        System.out.println("Drawing frame: " + this.model.getCurrentFrame());
        gui.drawSellExplosion(this.model.getPosition(), this.model.getCurrentFrame());
    }
}
