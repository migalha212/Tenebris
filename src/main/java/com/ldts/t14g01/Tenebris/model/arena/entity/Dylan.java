package com.ldts.t14g01.Tenebris.model.arena.entity;

import com.ldts.t14g01.Tenebris.controller.arena.DylanController;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.Commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.Commands.CreateParticle;
import com.ldts.t14g01.Tenebris.model.arena.Commands.KillDylan;
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

    public Dylan(Vector2D position, int hp, int velocity) {
        super(position, hitBoX, hp, velocity);
        this.view = new DylanView(this);
        this.controller = new DylanController(this);
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
