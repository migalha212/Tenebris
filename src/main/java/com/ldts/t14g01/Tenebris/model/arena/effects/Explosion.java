package com.ldts.t14g01.Tenebris.model.arena.effects;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.effects.ExplosionView;

public class Explosion extends Effect implements DamagesEntities {
    private int damage;

    public Explosion(Vector2D position, int damage) {
        super(position, new HitBoX(new Vector2D(-35, -35), new Vector2D(70, 70)));
        this.view = new ExplosionView(this);
        this.damage = damage * 2; // It will be cut in half before the first interaction
    }

    @Override
    public void update() {
        super.update();

        // Reduce Damage so it hurts entities closer more
        // They collide more times when they are bouncing away
        this.damage = (int) (Math.pow(this.damage, 0.8));
    }

    @Override
    public boolean isOver() {
        return this.getCurrentFrame() > GUI.EXPLOSION_FRAME_COUNT;
    }

    @Override
    public int getEntityDamage() {
        return this.damage;
    }
}
