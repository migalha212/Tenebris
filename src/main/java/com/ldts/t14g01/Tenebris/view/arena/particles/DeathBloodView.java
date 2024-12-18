package com.ldts.t14g01.Tenebris.view.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.particles.DeathBlood;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class DeathBloodView extends ElementView<DeathBlood> {
    public DeathBloodView(DeathBlood model) {
        super(model);
    }

    @Override
    public void draw() {
        GUI.getGUI().drawParticleEffect(
                this.model.getPosition(),
                GUI.ParticleEffect.DEATH_BLOOD,
                this.model.getCurrentFrame()
        );
    }
}
