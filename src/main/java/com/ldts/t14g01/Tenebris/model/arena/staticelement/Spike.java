package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class Spike extends GameElement implements DamagesEntities {
    private static final int damage = 10;

    public Spike(Vector2D position, int size) {
        super(position, size);
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
