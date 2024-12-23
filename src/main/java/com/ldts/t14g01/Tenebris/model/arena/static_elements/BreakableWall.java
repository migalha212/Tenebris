package com.ldts.t14g01.Tenebris.model.arena.static_elements;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.model.arena._commands.CreateParticle;
import com.ldts.t14g01.Tenebris.model.arena._commands.DeleteBreakableWall;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.BlocksMovement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.BlocksVision;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.model.arena.particles.ParticleType;
import com.ldts.t14g01.Tenebris.utils.HitBox;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.staticelement.BreakableWallView;

import java.util.ArrayList;
import java.util.List;

public class BreakableWall extends GameElement implements BlocksVision, AbsorbsProjectiles, BlocksMovement {
    private static final HitBox hitBoX = new HitBox(new Vector2D(-9, -9), new Vector2D(17, 17));
    private int hp;

    public BreakableWall(Vector2D position, int hp) {
        super(position, hitBoX);
        this.hp = hp;
        this.view = new BreakableWallView(this);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public List<Command> interact(GameElement other) {
        List<Command> commands = new ArrayList<>();

        if (other instanceof DamagesEntities) {
            int damage = ((DamagesEntities) other).getEntityDamage();
            if (damage != 0) {
                this.takeDamage(damage);
                commands.add(new CreateParticle(this.position, ParticleType.BREAKABLE_WALL_DAMAGE));
            }
        }

        if (!this.isAlive()) commands.add(new DeleteBreakableWall(this));

        return commands;
    }

    public int getHp() {
        return hp;
    }
}
