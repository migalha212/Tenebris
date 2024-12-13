package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.staticelement.SandbagView;

public class SandBag extends GameElement {
    public SandBag(Vector2D position) {
        super(position);
        this.view = new SandbagView(this);
    }

    @Override
    public void interact(GameElement other) {
        // Empty because this element doesn't react to other elements
    }
}
