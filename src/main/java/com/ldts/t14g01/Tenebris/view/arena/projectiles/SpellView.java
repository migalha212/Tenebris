package com.ldts.t14g01.Tenebris.view.arena.projectiles;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.projectile.Spell;
import com.ldts.t14g01.Tenebris.view.arena.ElementView;

public class SpellView extends ElementView<Spell> {
    public SpellView(Spell model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawSpell(this.model.getPosition());
    }
}
