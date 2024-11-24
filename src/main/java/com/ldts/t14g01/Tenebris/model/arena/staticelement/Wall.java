package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.utils.Position;

public class Wall extends GameElement implements AbsorbsProjectiles {
    public Wall(Position position, int size) {
        super(position, size);
    }

    @Override
    public void interact(GameElement other) {
        // Empty because this element doesn't react to other elements
    }
}
