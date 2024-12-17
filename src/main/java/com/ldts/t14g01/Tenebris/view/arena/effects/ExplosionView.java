package com.ldts.t14g01.Tenebris.view.arena.effects;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.effects.Explosion;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class ExplosionView extends ElementView<Explosion> {
    public ExplosionView(Explosion model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawExplosion(this.model.getPosition(), this.model.getCurrentFrame());
    }
}
