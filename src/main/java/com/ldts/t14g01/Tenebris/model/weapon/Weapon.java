package com.ldts.t14g01.Tenebris.model.weapon;

import com.ldts.t14g01.Tenebris.controller.arena.weapon.WeaponController;

public abstract class Weapon {
    private final int WEAPON_RELOAD_TIME;
    private final int WEAPON_FIRE_COOLDOWN;
    private final int BULLET_CAPACITY;
    private int weaponTimer;
    private int bullets;
    private boolean isReloading;


    public Weapon(int weaponReloadTime, int weaponFireCooldown, int bulletCapacity) {
        WEAPON_RELOAD_TIME = weaponReloadTime;
        WEAPON_FIRE_COOLDOWN = weaponFireCooldown;
        BULLET_CAPACITY = bulletCapacity;
        weaponTimer = 0;
        bullets = BULLET_CAPACITY;
        isReloading = false;
    }

    public void tickWeaponTimer() {
        this.weaponTimer++;
    }

    public void resetTimer() {
        this.weaponTimer = 0;
    }

    public boolean hasBullets() {
        return this.bullets > 0;
    }

    public void startReload() {
        this.isReloading = true;
    }

    public void reload() {
        if (weaponTimer < this.getWEAPON_RELOAD_TIME()) return;

        this.bullets = this.BULLET_CAPACITY;
        this.isReloading = false;
        this.resetTimer();
    }

    public boolean canShoot() {
        if (this.hasBullets() && !this.isReloading) return this.weaponTimer >= this.WEAPON_FIRE_COOLDOWN;
        return false;
    }

    public void shot() {
        this.bullets--;
    }

    public boolean isReloading() {
        return this.isReloading;
    }

    public int getWEAPON_RELOAD_TIME() {
        return WEAPON_RELOAD_TIME;
    }

    public int getWEAPON_FIRE_COOLDOWN() {
        return WEAPON_FIRE_COOLDOWN;
    }

    public int getBULLET_CAPACITY() {
        return BULLET_CAPACITY;
    }

    public abstract WeaponController getWeaponController();

}
