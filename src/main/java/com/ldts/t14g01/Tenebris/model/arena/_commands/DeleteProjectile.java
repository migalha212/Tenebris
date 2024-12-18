package com.ldts.t14g01.Tenebris.model.arena._commands;

import com.ldts.t14g01.Tenebris.model.arena.projectiles.Projectile;

public record DeleteProjectile(Projectile projectile) implements Command {
}
