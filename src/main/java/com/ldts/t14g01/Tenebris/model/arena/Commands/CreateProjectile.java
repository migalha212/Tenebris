package com.ldts.t14g01.Tenebris.model.arena.Commands;

import com.ldts.t14g01.Tenebris.model.arena.projectile.Projectile;

public record CreateProjectile(Projectile projectile) implements Command {
}
