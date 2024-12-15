package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.Commands.*;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.*;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.ElementProvider;
import com.ldts.t14g01.Tenebris.model.arena.particles.DamageBlood;
import com.ldts.t14g01.Tenebris.model.arena.particles.DeathBlood;
import com.ldts.t14g01.Tenebris.model.arena.particles.Particle;
import com.ldts.t14g01.Tenebris.model.arena.projectile.Projectile;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.SandBag;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Spike;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Wall;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Pair;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena implements ElementProvider {
    private final List<GameElement> elements;
    private final List<Monster> monsters;
    private final List<Particle> particles;
    private final List<Projectile> projectiles;

    private Dylan dylan;

    public Arena() throws IOException {
        this.elements = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.particles = new ArrayList<>();
        this.projectiles = new ArrayList<>();
    }

    public void addElement(GameElement element) {
        switch (element) {
            case Dylan dylan1 -> {
                this.dylan = dylan1;
                System.out.println("Dylan Added");
            }
            case Particle particle -> this.particles.add(particle);
            case Monster monster -> this.monsters.add(monster);
            case null -> {}
            default -> this.elements.add(element);
        }
    }

    public Dylan getDylan() {
        return this.dylan;
    }

    public List<Monster> getMonsters() {
        return this.monsters;
    }

    public List<GameElement> getElements() {
        return this.elements;
    }

    public List<Particle> getParticles() {
        return this.particles;
    }

    public void removeParticle(Particle particle) {
        this.particles.remove(particle);
    }

    public void checkCollisions() {
        List<Pair<GameElement>> collisions = new ArrayList<>();

        for (GameElement element : this.elements) {
            // Check Collisions between Monsters and Static Elements
            for (Monster monster : this.monsters)
                if (HitBoX.collide(element.position, element.hitBox, monster.position, monster.hitBox)) collisions.add(
                        new Pair<>(element, monster));

            // Check Collision between Dylan and Static Elements
            if (HitBoX.collide(element.position, element.hitBox, this.dylan.position, this.dylan.hitBox))
                collisions.add(new Pair<>(this.dylan, element));
        }

        // Check Collisions between Monsters
        for (int i = 0; i < this.monsters.size(); i++)
            for (int j = i + 1; j < this.monsters.size(); j++) {
                Monster monster1 = this.monsters.get(i);
                Monster monster2 = this.monsters.get(j);
                if (HitBoX.collide(monster1.position, monster1.hitBox, monster2.position, monster2.hitBox))
                    collisions.add(new Pair<>(monster1, monster2));
            }

        // Check Collisions between Dylan and Monsters
        for (Monster monster : this.monsters)
            if (HitBoX.collide(monster.position, monster.hitBox, this.dylan.position, this.dylan.hitBox))
                collisions.add(new Pair<>(this.dylan, monster));

        for (Pair<GameElement> p : collisions) {
            this.handleCommands(p.first.interact(p.second));
            this.handleCommands(p.second.interact(p.first));
        }
    }

    private void handleCommands(List<Command> commands) {
        commands.forEach(command -> {
            // Handle Projectile Creation
            if (command instanceof CreateParticle) switch (((CreateParticle) command).type()) {
                case DAMAGE_BLOOD -> this.addElement(new DamageBlood(((CreateParticle) command).position()));
                case DEATH_BLOOD -> this.addElement(new DeathBlood(((CreateParticle) command).position()));
                case null, default -> throw new RuntimeException("Command tried to create an invalid type of particle");
            }

            // Handle Projectile Deletion
            if (command instanceof DeleteProjectile) this.projectiles.remove(((DeleteProjectile) command).projectile());

            // Handle Monster Deletion
            if (command instanceof DeleteMonster) this.monsters.remove(((DeleteMonster) command).monster());

            // Handle Dylan Death
            if (command instanceof KillDylan) this.dylan = null;
        });
    }
}
