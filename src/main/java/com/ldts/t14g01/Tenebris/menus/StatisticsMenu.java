package com.ldts.t14g01.Tenebris.menus;

import com.ldts.t14g01.Tenebris.State;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;

import java.io.IOException;

public class StatisticsMenu implements Menu {
    private static final String name = "Statistics";

    @Override
    public void run(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
    }

    @Override
    public String getName() {
        return StatisticsMenu.name;
    }
}