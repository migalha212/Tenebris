package com.ldts.t14g01.Tenebris.view.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.particles.DamageBlood;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class DamageBloodView extends ElementView<DamageBlood> {
    public DamageBloodView(DamageBlood model) {
        super(model);
    }

    @Override
    public void draw() {
        GUI.getGUI().drawParticleEffect(
                this.model.getPosition(),
                GUI.ParticleEffect.DAMAGE_BLOOD,
                this.model.getCurrentFrame()
        );
    }
}
