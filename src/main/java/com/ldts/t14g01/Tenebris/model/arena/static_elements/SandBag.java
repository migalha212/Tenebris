package com.ldts.t14g01.Tenebris.model.arena.static_elements;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.staticelement.SandbagView;

import java.util.ArrayList;
import java.util.List;

public class SandBag extends GameElement {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-9, -7), new Vector2D(15, 13));

    public SandBag(Vector2D position) {
        super(position, hitBoX);
        this.view = new SandbagView(this);
    }

    // Empty because this element doesn't react to other elements
    @Override
    public List<Command> interact(GameElement other) {
        return new ArrayList<>();
    }
}
