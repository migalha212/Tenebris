package com.ldts.t14g01.Tenebris.model.arena.weapons;

import com.ldts.t14g01.Tenebris.controller.arena.weapon.WeaponController;
import com.ldts.t14g01.Tenebris.view.arena.weapon.WeaponView;

public abstract class Weapon {
    protected WeaponController controller;
    protected WeaponView view;
    private final int WEAPON_RELOAD_TIME;
    private final int WEAPON_FIRE_COOLDOWN;
    private final int BULLET_CAPACITY;
    private int timer;
    private int ammo;
    private boolean isReloading;


    public Weapon(int weaponReloadTime, int weaponFireCooldown, int bulletCapacity) {
        this.controller = null;
        this.view = null;
        this.WEAPON_RELOAD_TIME = weaponReloadTime;
        this.WEAPON_FIRE_COOLDOWN = weaponFireCooldown;
        this.BULLET_CAPACITY = bulletCapacity;
        this.timer = 0;
        this.ammo = BULLET_CAPACITY;
        this.isReloading = false;
    }

    public WeaponController getController() {
        return this.controller;
    }

    public WeaponView getView() {
        return this.view;
    }

    public int getAmmoCount() {
        return this.ammo;
    }

    public void tickWeaponTimer() {
        if (this.timer < this.WEAPON_FIRE_COOLDOWN) this.timer++;
        if (this.isReloading && this.timer < this.WEAPON_RELOAD_TIME) this.timer++;
    }

    public void shot() {
        if (!this.canShoot()) throw new RuntimeException("Attempt to shoot weapon when it can't shoot.");
        this.ammo--;
        this.timer = 0;
    }

    public boolean canShoot() {
        if (this.isLoaded() && !this.isReloading) return this.timer >= this.WEAPON_FIRE_COOLDOWN;
        return false;
    }

    public boolean isLoaded() {
        return this.ammo > 0;
    }

    public boolean isReloading() {
        return this.isReloading;
    }

    public void startReload() {
        this.isReloading = true;
    }

    public void reload() {
        if (timer < this.WEAPON_RELOAD_TIME) return;

        this.ammo = this.BULLET_CAPACITY;
        this.isReloading = false;
        this.timer = 0;
    }
}
