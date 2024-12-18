package com.ldts.t14g01.Tenebris.view.arena.projectiles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.ExplosiveBullet;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class ExplosiveBulletView extends ElementView<ExplosiveBullet> {
    public ExplosiveBulletView(ExplosiveBullet model) {
        super(model);
    }

    @Override
    public void draw() {
        GUI.getGUI().drawProjectile(
                this.model.getPosition(),
                GUI.Projectile.EXPLOSIVE,
                Vector2D.Direction.RIGHT
        );
    }
}
