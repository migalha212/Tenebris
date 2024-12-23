package com.ldts.t14g01.Tenebris.model.arena.projectiles;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.model.arena._commands.CreateParticle;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.model.arena.particles.ParticleType;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.utils.HitBox;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.projectiles.SpellView;

import java.util.List;

public class Spell extends Projectile {
    private static final HitBox HIT_BOX = new HitBox(new Vector2D(-2, -2), new Vector2D(4, 4));
    private static final int VELOCITY = 3;

    public Spell(Vector2D position, Vector2D.Direction direction, int damage) {
        super(position, HIT_BOX, direction, VELOCITY, damage);
        this.view = new SpellView(this);
    }

    @Override
    public List<Command> interact(GameElement other) {
        List<Command> commands = super.interact(other);

        if (other instanceof AbsorbsProjectiles) {
            commands.add(new CreateParticle(this.position, ParticleType.SPELL_EXPLOSION));
            SoundManager.getInstance().playSFX(SoundManager.SFX.SPELL_COLLISION);
        }
        return commands;
    }
}
