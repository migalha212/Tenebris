package com.ldts.t14g01.Tenebris.model.arena.Commands;

import com.ldts.t14g01.Tenebris.model.arena.particles.Particle;

public record DeleteParticle(Particle particle) implements Command {
}
