package com.ldts.t14g01.Tenebris.model.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.particles.SpellExplosionView;

public class SpellExplosion extends Particle {
    public SpellExplosion(Vector2D position) {
        super(position);
        this.view = new SpellExplosionView(this);
    }

    @Override
    public boolean isOver() {
        return this.getCurrentFrame() > GUI.SPELL_EXPLOSION_FRAME_COUNT;
    }
}
