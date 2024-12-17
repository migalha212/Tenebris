package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.Commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.staticelement.SpikeView;

import java.util.ArrayList;
import java.util.List;

public class Spike extends GameElement implements DamagesEntities {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-8, -7), new Vector2D(15, 13));
    private int damage;

    public Spike(Vector2D position, int damage) {
        super(position, hitBoX);
        this.damage = damage;
        this.view = new SpikeView(this);
    }

    // Empty because this element doesn't react to other elements
    @Override
    public List<Command> interact(GameElement other) {
        return new ArrayList<>();
    }

    @Override
    public int getEntityDamage() {
        return damage;
    }
}
