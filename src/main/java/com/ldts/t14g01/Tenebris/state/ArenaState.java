package com.ldts.t14g01.Tenebris.state;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.arena.ArenaController;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.arena.ArenaView;

public class ArenaState extends State<Arena> {
    public ArenaState(Arena model) {
        super(model);
        SoundManager.getInstance().playMusic(SoundManager.Music.ARENA_BACKGROUND);
    }

    @Override
    protected View<Arena> getView() {
        return new ArenaView(this.getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(this.getModel());
    }
}
