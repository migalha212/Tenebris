package com.ldts.t14g01.Tenebris.view.arena.weapon;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.weapons.GrenadeLauncher;

public class GrenadeLauncherView extends WeaponView<GrenadeLauncher> {
    public GrenadeLauncherView(GrenadeLauncher model) {
        super(model);
    }

    @Override
    public void draw() {
        GUI.getGUI().drawWeapon(GUI.Weapon.GRENADE_LAUNCHER, this.model.getAmmoCount());
    }
}
