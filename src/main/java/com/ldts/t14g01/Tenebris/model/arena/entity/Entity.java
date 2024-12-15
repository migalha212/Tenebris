package com.ldts.t14g01.Tenebris.model.arena.entity;

import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.Commands.Command;
import com.ldts.t14g01.Tenebris.model.arena.Commands.CreateParticle;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.DamagesEntities;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.Moves;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.TakesDamage;
import com.ldts.t14g01.Tenebris.model.arena.particles.ParticleType;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.utils.Bounce;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Entity extends GameElement implements TakesDamage, Moves {
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
    }

    protected int getVelocity() {
        return this.velocity;
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

    public Bounce getBounce() {
        return bounce;
    }

    public void setBounce(Bounce bounce) {
        this.bounce = bounce;
    }

    @Override
    public void move() {
        this.moving.forEach(moveState -> {
            switch (moveState) {
                case FRONT -> this.position = this.position.add(new Vector2D(0, this.getVelocity()));
                case BACK -> this.position = this.position.add(new Vector2D(0, -this.getVelocity()));
                case LEFT -> this.position = this.position.add(new Vector2D(-this.getVelocity(), 0));
                case RIGHT -> this.position = this.position.add(new Vector2D(this.getVelocity(), 0));
                case null, default -> {}
            }
        });
    }

    @Override
    public void bounce(Vector2D direction) {
        this.bounce = new Bounce(direction.getMajorDirection(10), 20);
    }

    @Override
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
        SoundManager.getInstance().playSFX(SoundManager.SFX.ENTITY_DAMAGE);
    }

    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }

    @Override
    public List<Command> interact(GameElement other) {
        List<Command> commands = new ArrayList<>();

        if (other instanceof DamagesEntities) {
            this.takeDamage(((DamagesEntities) other).getEntityDamage());
            this.bounce(this.position.minus(other.getPosition()));
            commands.add(new CreateParticle(this.position, ParticleType.DEATH_BLOOD));
        }

        if (other instanceof Moves) this.bounce(this.position.minus(other.getPosition()));

        return commands;
    }
}