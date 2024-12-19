package com.ldts.t14g01.Tenebris.model.arena.entities;

import com.ldts.t14g01.Tenebris.controller.arena.DylanController;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.model.arena._commands.CreateParticle;
import com.ldts.t14g01.Tenebris.model.arena._commands.KillDylan;
import com.ldts.t14g01.Tenebris.model.arena.animation.Bounce;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesPlayer;
import com.ldts.t14g01.Tenebris.model.arena.particles.ParticleType;
import com.ldts.t14g01.Tenebris.model.arena.weapons.GrenadeLauncher;
import com.ldts.t14g01.Tenebris.model.arena.weapons.Pistol;
import com.ldts.t14g01.Tenebris.model.arena.weapons.Weapon;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.DylanView;

import java.util.ArrayList;
import java.util.List;

public class Dylan extends Entity {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-5, -6), new Vector2D(8, 13));
    private final DylanController controller;
    private final List<Weapon> weapons;
    private int selectedWeapon;
    private final int maxHP;

    public Dylan(Vector2D position, int hp, int velocity) {
        super(position, hitBoX, hp, velocity);
        this.view = new DylanView(this);
        this.controller = new DylanController(this);
        this.weapons = new ArrayList<>();
        this.selectedWeapon = 0;
        this.maxHP = hp;

        // Add Weapons
        weapons.add(new Pistol());
        weapons.add(new GrenadeLauncher());
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public Weapon getEquipedWeapon() {
        return this.weapons.get(this.selectedWeapon);
    }

    public void setSelectedWeapon(int selectedWeapon) {
        if (selectedWeapon < 0 || selectedWeapon >= this.weapons.size())
            throw new RuntimeException("Trying to select a weapon that doesn't exist");
        this.selectedWeapon = selectedWeapon;
    }

    public DylanController getController() {
        return this.controller;
    }

    @Override
    public List<Command> interact(GameElement other) {
        // Call Entity interaction handler
        List<Command> commands = super.interact(other);

        // Handle things that Damage only the Player
        if (other instanceof DamagesPlayer) {
            int damage = ((DamagesPlayer) other).getPlayerDamage();
            if (damage != 0) {
                // Take Damage
                this.takeDamage(damage);

                // Create Blood Particles
                commands.add(new CreateParticle(this.position, ParticleType.DAMAGE_BLOOD));

                // Bounce in the opposite way
                this.animation = new Bounce(
                        this,
                        this.getPosition().minus(other.getPosition()).getMajorDirection()
                );
            }
        }

        // If died play sound and create death blood
        if (!this.isAlive()) {
            SoundManager.getInstance().playSFX(SoundManager.SFX.DYLAN_DEATH);
            commands.add(new KillDylan());
        }

        return commands;
    }
}
