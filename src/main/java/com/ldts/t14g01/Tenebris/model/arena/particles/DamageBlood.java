package com.ldts.t14g01.Tenebris.model.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.particles.DamageBloodView;

public class DamageBlood extends Particle {
    public DamageBlood(Vector2D position) {
        super(position);
        this.view = new DamageBloodView(this);
    }

    @Override
    public boolean isOver() {
        return this.getCurrentFrame() > GUI.DAMAGE_BLOOD_FRAME_COUNT;
    }
}
