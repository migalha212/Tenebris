package com.ldts.t14g01.Tenebris.model.arena.projectile;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.Moves;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.utils.Position;
import com.ldts.t14g01.Tenebris.utils.Vector;

public class Bullet extends GameElement implements Moves, DamagesEntities {
    private final Vector velocity;
    private final int damage;
    private boolean hit;

    public Bullet(Position position, int size, int damage, Vector velocity) {
        super(position, size);
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
    public void accelerate() {
        // This element doesn't accelerate
    }

    @Override
    public void bounce(Vector.Direction direction) {
        // This element doesn't bouce
    }
}
