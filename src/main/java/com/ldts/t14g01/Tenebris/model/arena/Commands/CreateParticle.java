package com.ldts.t14g01.Tenebris.model.arena.Commands;

import com.ldts.t14g01.Tenebris.model.arena.particles.ParticleType;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public record CreateParticle(Vector2D position, ParticleType type) implements Command {
}
