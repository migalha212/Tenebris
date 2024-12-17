package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.model.arena.projectile.Projectile;

public class ProjectileController {
    private final Projectile model;

    public ProjectileController(Projectile model) {
        this.model = model;
    }

    public void update() {
        this.model.update();
    }
}
