package com.ldts.t14g01.Tenebris.view.arena.projectiles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.Bullet;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class BulletView extends ElementView<Bullet> {
    public BulletView(Bullet model) {
        super(model);
    }

    @Override
    public void draw() {
        GUI.getGUI().drawProjectile(
                this.model.getPosition(),
                GUI.Projectile.BULLET,
                this.model.getDirection()
        );
    }
}
