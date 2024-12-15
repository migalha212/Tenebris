package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.Commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.Commands.CreateParticle;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.BlocksVision;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.model.arena.particles.ParticleType;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class BreakableWall extends GameElement implements TakesDamage, BlocksVision {
    private static final HitBoX hitBoX = new HitBoX(new Vector2D(-9, -9), new Vector2D(17, 17));
    private int hp;

    public BreakableWall(Vector2D position, int hp) {
        super(position, hitBoX);
        this.hp = hp;
    }

    @Override
    public List<Command> interact(GameElement other) {
        List<Command> commands = new ArrayList<>();

        if (other instanceof DamagesEntities) {
            this.takeDamage(((DamagesEntities) other).getEntityDamage());
            commands.add(new CreateParticle(this.position, ParticleType.DEATH_BLOOD));
        }

        return commands;
    }

    @Override
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
}
