package com.ldts.t14g01.Tenebris.model.arena.weapons;

import com.ldts.t14g01.Tenebris.controller.arena.weapon.PistolController;
import com.ldts.t14g01.Tenebris.view.arena.weapon.PistolView;

public class Pistol extends Weapon {
    public Pistol() {
        super(
                60,
                5,
                10
        );
        this.controller = new PistolController(this);
        this.view = new PistolView(this);
    }
}
