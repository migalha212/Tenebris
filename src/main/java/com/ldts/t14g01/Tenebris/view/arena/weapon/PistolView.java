package com.ldts.t14g01.Tenebris.view.arena.weapon;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.weapons.Pistol;

public class PistolView extends WeaponView<Pistol> {
    public PistolView(Pistol model) {
        super(model);
    }

    @Override
    public void draw() {
        GUI.getGUI().drawWeapon(GUI.Weapon.PISTOL, this.model.getAmmoCount());
    }
}
