package com.ldts.t14g01.Tenebris.state;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.arena.ArenaController;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.view.View;

public class ArenaState extends State<Arena> {
    public ArenaState(Arena model) {
        super(model);
    }

    @Override
    protected View<Arena> getView() {
        return null;
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(this.getModel());
    }
}
