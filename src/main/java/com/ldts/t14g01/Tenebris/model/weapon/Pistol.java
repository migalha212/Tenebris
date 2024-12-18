package com.ldts.t14g01.Tenebris.model.weapon;

import com.ldts.t14g01.Tenebris.controller.arena.weapon.PistolController;
import com.ldts.t14g01.Tenebris.controller.arena.weapon.WeaponController;

public class Pistol extends Weapon {
    private final PistolController pistolController;

    public Pistol() {
        super(
                60,
                10,
                5
        );
        pistolController = new PistolController(this);
    }


    @Override
    public WeaponController getWeaponController() {
        return this.pistolController;
    }
}
