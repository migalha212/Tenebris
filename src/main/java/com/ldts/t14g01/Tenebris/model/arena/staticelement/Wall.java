package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.Commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.BlocksVision;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.staticelement.WallView;

import java.util.ArrayList;
import java.util.List;

public class Wall extends GameElement implements AbsorbsProjectiles, BlocksVision {

    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-9, -9), new Vector2D(17, 17));

    public Wall(Vector2D position) {
        super(position, hitBoX);
        this.view = new WallView(this);
    }

    // Empty because this element doesn't react to other elements
    @Override
    public List<Command> interact(GameElement other) {
        return new ArrayList<>();
    }
}
