package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.staticelement.SpikeView;

public class Spike extends GameElement implements DamagesEntities {
    private static final int damage = 10;

    public Spike(Vector2D position) {
        super(position);
        this.view = new SpikeView(this);
    }

    @Override
    public void interact(GameElement other) {
        // Empty because this element doesn't react to other elements
    }

    @Override
    public int getEntityDamage() {
        return Spike.damage;
    }
}
