package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class Wall extends GameElement implements AbsorbsProjectiles {
    public Wall(Vector2D position) {
        super(position);
    }

    @Override
    public void interact(GameElement other) {
        // Empty because this element doesn't react to other elements
    }
}
