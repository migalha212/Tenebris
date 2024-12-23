package com.ldts.t14g01.Tenebris.model.arena.static_elements;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.BlocksMovement;
import com.ldts.t14g01.Tenebris.utils.HitBox;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.staticelement.SandbagView;

import java.util.ArrayList;
import java.util.List;

public class SandBag extends GameElement implements BlocksMovement {
    private static final HitBox hitBoX = new HitBox(new Vector2D(-9, -7), new Vector2D(15, 13));

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
