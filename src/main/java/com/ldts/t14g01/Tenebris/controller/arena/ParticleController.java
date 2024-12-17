package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.model.arena.particles.Particle;

public class ParticleController {
    private final Particle model;

    public ParticleController(Particle model) {
        this.model = model;
    }

    public void update() {
        this.model.update();
    }
}
