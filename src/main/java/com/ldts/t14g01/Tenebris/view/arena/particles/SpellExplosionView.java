package com.ldts.t14g01.Tenebris.view.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.particles.SpellExplosion;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class SpellExplosionView extends ElementView<SpellExplosion> {
    public SpellExplosionView(SpellExplosion model) {
        super(model);
    }

    @Override
    public void draw() {
        GUI.getGUI().drawParticleEffect(
                this.model.getPosition(),
                GUI.ParticleEffect.SPELL_EXPLOSION,
                this.model.getCurrentFrame()
        );
    }
}
