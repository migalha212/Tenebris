package com.ldts.t14g01.Tenebris.model.arena.entity;

import com.ldts.t14g01.Tenebris.controller.arena.DylanController;
import com.ldts.t14g01.Tenebris.model.arena.Commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.Commands.CreateParticle;
import com.ldts.t14g01.Tenebris.model.arena.Commands.KillDylan;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesPlayer;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.model.arena.particles.ParticleType;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.DylanView;

import java.util.List;

public class Dylan extends Entity implements TakesDamage {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-5, -6), new Vector2D(8, 13));
    private final DylanController controller;
    private final int WEAPON_1_COOLDOWN;
    private final int WEAPON_2_COOLDOWN;
    private int weapon1Timer;
    private int weapon2Timer;
    private int selectedWeapon;

    public Dylan(Vector2D position, int hp, int velocity) {
        super(position, hitBoX, hp, velocity);
        this.view = new DylanView(this);
        this.controller = new DylanController(this);
        this.WEAPON_1_COOLDOWN = 30;
        this.WEAPON_2_COOLDOWN = 50;
        this.weapon1Timer = 0;
        this.weapon2Timer = 0;
        this.selectedWeapon = 1;
    }

    public boolean canShoot(int weapon) {
        if (weapon == 1) return this.weapon1Timer >= this.WEAPON_1_COOLDOWN;
        if (weapon == 2) return this.weapon2Timer >= this.WEAPON_2_COOLDOWN;
        throw new RuntimeException("Asking if can shoot a weapon that no exist.");
    }

    public void tickWeaponTimers() {
        this.weapon1Timer++;
        this.weapon2Timer++;
    }

    public void resetTimer(int weapon) {
        if (weapon == 1) this.weapon1Timer = 0;
        else if (weapon == 2) this.weapon2Timer = 0;
        else throw new RuntimeException("Trying to reset a timer for a weapon that no exist.");
    }

    public int getSelectedWeapon() {
        return selectedWeapon;
    }

    public void setSelectedWeapon(int selectedWeapon) {
        this.selectedWeapon = selectedWeapon;
    }

    public DylanController getController() {
        return this.controller;
    }

    @Override
    public List<Command> interact(GameElement other) {
        // Call Entity interaction handler
        List<Command> commands = super.interact(other);

        // ToDo: Implement Interactions with DamagesPlayer Objects
        if (other instanceof DamagesPlayer) {
            this.takeDamage(((DamagesPlayer) other).getPlayerDamage());
            commands.add(new CreateParticle(this.position, ParticleType.DAMAGE_BLOOD));
        }

        if (!this.isAlive()) commands.add(new KillDylan());

        return commands;
    }
}
