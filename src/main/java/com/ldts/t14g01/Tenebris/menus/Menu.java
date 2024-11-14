package com.ldts.t14g01.Tenebris.menus;

import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.state.State;

import java.io.IOException;

public interface Menu {
    void tick(State state, Action action);

    void draw(GUI gui) throws IOException;
}
