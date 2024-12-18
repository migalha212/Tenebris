package com.ldts.t14g01.Tenebris.view.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.particles.BreakableWallDamage;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class BreakableWallDamageView extends ElementView<BreakableWallDamage> {
    public BreakableWallDamageView(BreakableWallDamage model) {
        super(model);
    }

    @Override
    public void draw(Vector2D cameraOffset) {
        GUI.getGUI().drawParticleEffect(
                this.model.getPosition().minus(cameraOffset),
                GUI.ParticleEffect.BREAKABLE_WALL_DAMAGE,
                this.model.getCurrentFrame()
        );
    }
}
