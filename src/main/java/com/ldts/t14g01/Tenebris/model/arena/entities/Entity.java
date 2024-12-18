package com.ldts.t14g01.Tenebris.model.arena.entities;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.model.arena._commands.CreateParticle;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.AbsorbsProjectiles;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.BlocksMovement;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.Moves;
import com.ldts.t14g01.Tenebris.model.arena.particles.ParticleType;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.utils.Bounce;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class Entity extends GameElement implements Moves, AbsorbsProjectiles, BlocksMovement {
    private final int velocity;
    private int hp;
    private Bounce bounce;

    protected Set<State> moving;
    protected State looking;

    public enum State {
        IDLE,
        LEFT,
        RIGHT,
        FRONT,
        BACK
    }

    public Entity(Vector2D position, HitBoX hitBoX, int hp, int velocity) {
        super(position, hitBoX);
        this.velocity = velocity;
        this.hp = hp;
        this.bounce = null;
        this.moving = new TreeSet<>();
    }

    public int getVelocity() {
        return this.velocity;
    }

    public int getHp() {
        return this.hp;
    }

    public Bounce getBounce() {
        return bounce;
    }

    public void setBounce(Bounce bounce) {
        this.bounce = bounce;
    }

    public State getLooking() {
        return looking;
    }

    public void setLooking(State looking) {
        this.looking = looking;
    }

    public State getMoving() {
        // When moving in multiple directions
        // The left and right movement have a higher visual priority
        if (this.moving.contains(State.LEFT) && !this.moving.contains(State.RIGHT)) return State.LEFT;
        if (this.moving.contains(State.RIGHT) && !this.moving.contains(State.LEFT)) return State.RIGHT;
        if (this.moving.contains(State.FRONT) && !this.moving.contains(State.BACK)) return State.FRONT;
        if (this.moving.contains(State.BACK) && !this.moving.contains(State.FRONT)) return State.BACK;
        return State.IDLE;
    }

    public void setMoving(Set<State> moving) {
        this.moving = moving;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
        SoundManager.getInstance().playSFX(SoundManager.SFX.ENTITY_DAMAGE);
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    @Override
    public void move() {
        this.moving.forEach(moveState -> {
            switch (moveState) {
                case FRONT -> this.position = this.position.add(new Vector2D(0, this.getVelocity()));
                case BACK -> this.position = this.position.add(new Vector2D(0, -this.getVelocity()));
                case LEFT -> this.position = this.position.add(new Vector2D(-this.getVelocity(), 0));
                case RIGHT -> this.position = this.position.add(new Vector2D(this.getVelocity(), 0));
                case null, default -> {
                }
            }
        });
    }

    @Override
    public void bounce(Vector2D.Direction direction) {
        this.bounce = new Bounce(direction);
    }

    @Override
    public void collide(GameElement other) {
        // Calculate direction to move in to resolve conflict
        Vector2D.Direction direction = other.getPosition().minus(this.getPosition()).multiply(-1).getMajorDirection();

        // Move in that direction until collision is no longer happening
        while (HitBoX.collide(this.position, this.hitBox, other.getPosition(), other.getHitBox())) {
            switch (direction) {
                case UP -> this.position = this.position.add(new Vector2D(0, -1));
                case DOWN -> this.position = this.position.add(new Vector2D(0, 1));
                case RIGHT -> this.position = this.position.add(new Vector2D(1, 0));
                case LEFT -> this.position = this.position.add(new Vector2D(-1, 0));

                case UP_LEFT -> this.position = this.position.add(new Vector2D(-1, -1));
                case UP_RIGHT -> this.position = this.position.add(new Vector2D(1, -1));
                case DOWN_LEFT -> this.position = this.position.add(new Vector2D(-1, 1));
                case DOWN_RIGHT -> this.position = this.position.add(new Vector2D(1, 1));
            }
        }
    }

    @Override
    public List<Command> interact(GameElement other) {
        List<Command> commands = new ArrayList<>();

        if (other instanceof DamagesEntities) {
            int damage = ((DamagesEntities) other).getEntityDamage();
            if (damage != 0) {
                this.takeDamage(damage);
                this.bounce(this.position.minus(other.getPosition()).getMajorDirection());
                commands.add(new CreateParticle(this.position, ParticleType.DAMAGE_BLOOD));
            }
        }

        if (other instanceof Moves) this.bounce(this.position.minus(other.getPosition()).getMajorDirection());

        if (other instanceof BlocksMovement) this.collide(other);

        if (!this.isAlive()) commands.add(new CreateParticle(this.position, ParticleType.DEATH_BLOOD));

        return commands;
    }
}