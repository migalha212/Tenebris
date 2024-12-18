package com.ldts.t14g01.Tenebris.controller.arena.weapon;

import com.ldts.t14g01.Tenebris.model.arena._commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena._commands.CreateProjectile;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.Bullet;
import com.ldts.t14g01.Tenebris.model.weapon.Pistol;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class PistolController extends WeaponController{
    public PistolController(Pistol model){
        super(model);
    }

    @Override
    public void shoot(CommandHandler commandHandler, Vector2D bulletPosition, Vector2D.Direction direction){
        if(super.getModel().canShoot()) {
            super.getModel().resetTimer();
            super.getModel().shot();

            commandHandler.handleCommand(new CreateProjectile(new Bullet(bulletPosition, direction, 10)));
        }
    }
}
