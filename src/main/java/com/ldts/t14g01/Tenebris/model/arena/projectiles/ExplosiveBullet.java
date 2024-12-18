package com.ldts.t14g01.Tenebris.model.arena.projectiles;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.model.arena._commands.CreateEffect;
import com.ldts.t14g01.Tenebris.model.arena._commands.ShakeCamera;
import com.ldts.t14g01.Tenebris.model.arena.effects.Explosion;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.projectiles.ExplosiveBulletView;

import java.util.List;

public class ExplosiveBullet extends Projectile {
    private static final HitBoX HIT_BOX = new HitBoX(new Vector2D(-4, -4), new Vector2D(6, 6));
    private static final int VELOCITY = 4;
    private final int explosionDamage;

    public ExplosiveBullet(Vector2D position, Vector2D.Direction direction, int explosionDamage) {
        super(position, HIT_BOX, direction, VELOCITY, 0);
        this.view = new ExplosiveBulletView(this);
        this.explosionDamage = explosionDamage;
    }

    @Override
    public List<Command> interact(GameElement other) {
        List<Command> commands = super.interact(other);

        if (other instanceof AbsorbsProjectiles) {
            commands.add(new CreateEffect(new Explosion(this.position, this.explosionDamage)));
            commands.add(new ShakeCamera());
            SoundManager.getInstance().playSFX(SoundManager.SFX.EXPLOSION);
        }

        return commands;
    }
}
