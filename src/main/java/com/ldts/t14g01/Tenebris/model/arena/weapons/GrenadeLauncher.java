package com.ldts.t14g01.Tenebris.model.arena.weapons;

import com.ldts.t14g01.Tenebris.controller.arena.weapon.GrenadeLauncherController;
import com.ldts.t14g01.Tenebris.view.arena.weapon.GrenadeLauncherView;

public class GrenadeLauncher extends Weapon {
    public GrenadeLauncher() {
        super(
                90,
                0,
                1
        );
        this.controller = new GrenadeLauncherController(this);
        this.view = new GrenadeLauncherView(this);
    }
}
