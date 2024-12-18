package com.ldts.t14g01.Tenebris.model.weapon;

import com.ldts.t14g01.Tenebris.controller.arena.weapon.GrenadeLauncherController;
import com.ldts.t14g01.Tenebris.controller.arena.weapon.WeaponController;

public class GrenadeLauncher extends Weapon {
    private final GrenadeLauncherController grenadeLauncherController;
    public GrenadeLauncher() {
        super(
                30,
                30,
                1
        );
        grenadeLauncherController = new GrenadeLauncherController(this);
    }


    @Override
    public WeaponController getWeaponController() {
        return this.grenadeLauncherController;
    }
}
