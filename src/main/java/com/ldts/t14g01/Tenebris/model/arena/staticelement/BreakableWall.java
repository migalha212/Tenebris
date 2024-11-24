package com.ldts.t14g01.Tenebris.model.arena.staticelement;

import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.utils.Position;

public class BreakableWall extends GameElement implements TakesDamage {
    private int hp;

    public BreakableWall(Position position, int size, int hp) {
        super(position, size);
        this.hp = hp;
    }

    @Override
    public void interact(GameElement other) {
        if (other instanceof DamagesEntities) this.takeDamage(((DamagesEntities) other).getEntityDamage());
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
