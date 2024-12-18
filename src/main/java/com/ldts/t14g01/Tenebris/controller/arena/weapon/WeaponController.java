package com.ldts.t14g01.Tenebris.controller.arena.weapon;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.model.arena._commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena._commands.CreateProjectile;
import com.ldts.t14g01.Tenebris.model.arena.entities.Entity;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.Bullet;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.ExplosiveBullet;
import com.ldts.t14g01.Tenebris.model.weapon.GrenadeLauncher;
import com.ldts.t14g01.Tenebris.model.weapon.Pistol;
import com.ldts.t14g01.Tenebris.model.weapon.Weapon;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.StateChanger;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.IOException;

public abstract class WeaponController {
    private final Weapon model;

    public WeaponController(Weapon model) {
        this.model = model;
    }

    public void update() {
        this.model.tickWeaponTimer();
        if (!this.model.hasBullets()) {
            this.model.startReload();
        }
        if (this.model.isReloading()) {
            this.model.reload();
        }
    }

    public void reload(){
        if(!model.isReloading()){
            this.model.startReload();
        }
    }

    public Weapon getModel() {
        return model;
    }

    public abstract void shoot(CommandHandler commandHandler, Vector2D bulletPosition, Vector2D.Direction direction);
}
