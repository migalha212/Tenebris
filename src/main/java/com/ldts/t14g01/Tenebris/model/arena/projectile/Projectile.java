package com.ldts.t14g01.Tenebris.model.arena.projectile;

import com.ldts.t14g01.Tenebris.controller.arena.projectiles.ProjectileController;
import com.ldts.t14g01.Tenebris.model.arena.Commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.Commands.DeleteProjectile;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public abstract class Projectile extends GameElement implements DamagesEntities {
    private final ProjectileController controller;
    private final Vector2D.Direction direction;
    private final int velocity;
    private final int damage;

    public Projectile(Vector2D position, HitBoX hitBox, Vector2D.Direction direction, int velocity, int damage) {
        super(position, hitBox);
        this.controller = new ProjectileController(this);
        this.direction = direction;
        this.velocity = velocity;
        this.damage = damage;
    }

    public ProjectileController getController() {
        return this.controller;
    }

    public Vector2D.Direction getDirection() {
        return this.direction;
    }

    public void update() {
        switch (this.direction) {
            case RIGHT -> this.position = this.position.add(new Vector2D(this.velocity, 0));
            case LEFT -> this.position = this.position.add(new Vector2D(-this.velocity, 0));
            case DOWN -> this.position = this.position.add(new Vector2D(0, this.velocity));
            case UP -> this.position = this.position.add(new Vector2D(0, -this.velocity));
        }
    }

    @Override
    public int getEntityDamage() {
        return damage;
    }

    @Override
    public List<Command> interact(GameElement other) {
        List<Command> commands = new ArrayList<>();

        if (other instanceof AbsorbsProjectiles) commands.add(new DeleteProjectile(this));
        if (other instanceof TakesDamage) commands.add(new DeleteProjectile(this));

        return commands;
    }
}
