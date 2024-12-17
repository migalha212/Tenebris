package com.ldts.t14g01.Tenebris.model.arena.projectile;

import com.ldts.t14g01.Tenebris.model.arena.Commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.Commands.CreateParticle;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.model.arena.particles.ParticleType;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.projectiles.SpellView;

import java.util.List;

public class Spell extends Projectile {
    private static final HitBoX HIT_BOX = new HitBoX(new Vector2D(-2, -2), new Vector2D(4, 4));
    private static final int VELOCITY = 3;

    public Spell(Vector2D position, Vector2D.Direction direction, int damage) {
        super(position, HIT_BOX, direction, VELOCITY, damage);
        this.view = new SpellView(this);
    }

    @Override
    public List<Command> interact(GameElement other) {
        List<Command> commands = super.interact(other);

        if (other instanceof AbsorbsProjectiles)
            commands.add(new CreateParticle(this.position, ParticleType.SPELL_EXPLOSION));
        else if (other instanceof TakesDamage)
            commands.add(new CreateParticle(this.position, ParticleType.SPELL_EXPLOSION));

        return commands;
    }
}
