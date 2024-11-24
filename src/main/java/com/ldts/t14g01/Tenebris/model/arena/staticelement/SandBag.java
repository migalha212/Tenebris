package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.utils.Position;

public class SandBag extends GameElement {

    public SandBag(Position position, int size) {
        super(position, size);
    }

    @Override
    public void interact(GameElement other) {
        // Empty because this element doesn't react to other elements
    }
}
