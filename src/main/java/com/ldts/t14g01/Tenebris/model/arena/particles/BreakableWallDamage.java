package com.ldts.t14g01.Tenebris.model.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.particles.BreakableWallDamageView;

public class BreakableWallDamage extends Particle {
    public BreakableWallDamage(Vector2D position) {
        super(position);
        this.view = new BreakableWallDamageView(this);
    }

    @Override
    public boolean isOver() {
        return this.getCurrentFrame() > GUI.BREAKABLE_WALL_DAMAGE_FRAME_COUNT;
    }
}
