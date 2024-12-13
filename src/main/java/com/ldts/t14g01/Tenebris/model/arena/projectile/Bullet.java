package com.ldts.t14g01.Tenebris.model.arena.projectile;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.Moves;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class Bullet extends GameElement implements Moves, DamagesEntities {
    private final Vector2D velocity;
    private final int damage;
    private boolean hit;

    public Bullet(Vector2D position, int damage, Vector2D velocity) {
        super(position);
        this.velocity = velocity;
        this.damage = damage;
        this.hit = false;
    }

    @Override
    public void interact(GameElement other) {
        if (other instanceof AbsorbsProjectiles) this.hit = true;
        if (other instanceof TakesDamage) this.hit = true;
    }

    @Override
    public int getEntityDamage() {
        return damage;
    }

    @Override
    public void move() {
        this.position = this.position.add(this.velocity);
    }

    @Override
    public void bounce(Vector2D.Direction direction) {
        // This element doesn't bouce
    }
}
