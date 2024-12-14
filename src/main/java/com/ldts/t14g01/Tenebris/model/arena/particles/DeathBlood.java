package com.ldts.t14g01.Tenebris.model.arena.particles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.particles.DeathBloodView;

public class DeathBlood extends Particle {
    public DeathBlood(Vector2D position) {
        super(position);
        this.view = new DeathBloodView(this);
    }

    public boolean isOver() {
        return this.getCurrentFrame() > GUI.DEATH_BLOOD_FRAME_COUNT;
    }
}
