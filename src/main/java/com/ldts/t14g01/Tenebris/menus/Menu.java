package com.ldts.t14g01.Tenebris.menus;

import com.ldts.t14g01.Tenebris.screen.ScreenGetter;
import com.ldts.t14g01.Tenebris.state.State;

import java.io.IOException;

public interface Menu {
    void run(State state, ScreenGetter screenGetter) throws IOException, InterruptedException;
}
