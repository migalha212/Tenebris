package com.ldts.t14g01.Tenebris.view.arena.weapon;

import com.ldts.t14g01.Tenebris.model.arena.weapons.Weapon;

public abstract class WeaponView<T extends Weapon> {
    protected Weapon model;

    public WeaponView(Weapon model) {
        this.model = model;
    }

    public abstract void draw();
}
