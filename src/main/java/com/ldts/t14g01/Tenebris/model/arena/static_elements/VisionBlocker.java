package com.ldts.t14g01.Tenebris.model.arena.static_elements;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.BlocksVision;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.staticelement.VisionBlockerView;

import java.util.ArrayList;
import java.util.List;

public class VisionBlocker extends GameElement implements BlocksVision {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-9, -9), new Vector2D(17, 17));

    public VisionBlocker(Vector2D position) {
        super(position, hitBoX);
        this.view = new VisionBlockerView(this);
    }

    @Override
    public List<Command> interact(GameElement other) {
        return new ArrayList<>();
    }
}
