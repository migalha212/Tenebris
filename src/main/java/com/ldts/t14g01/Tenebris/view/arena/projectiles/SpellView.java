package com.ldts.t14g01.Tenebris.view.arena.projectiles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.projectile.Spell;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class SpellView extends ElementView<Spell> {
    public SpellView(Spell model) {
        super(model);
    }

    @Override
    public void draw() {
        GUI.getGUI().drawProjectile(
                this.model.getPosition(),
                GUI.Projectile.SPELL,
                Vector2D.Direction.RIGHT
        );
    }
}
