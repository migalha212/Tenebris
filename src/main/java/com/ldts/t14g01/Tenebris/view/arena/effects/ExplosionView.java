package com.ldts.t14g01.Tenebris.view.arena.effects;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.effects.Explosion;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class ExplosionView extends ElementView<Explosion> {
    public ExplosionView(Explosion model) {
        super(model);
    }

    @Override
    public void draw(Vector2D cameraOffset) {
        GUI.getGUI().drawParticleEffect(
                this.model.getPosition().minus(cameraOffset),
                GUI.ParticleEffect.EXPLOSION,
                this.model.getCurrentFrame()
        );
    }
}
