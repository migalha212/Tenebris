package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.effects.Effect;
import com.ldts.t14g01.Tenebris.model.arena.entities.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.Monster;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.ElementProvider;
import com.ldts.t14g01.Tenebris.model.arena.particles.Particle;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.Projectile;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena implements ElementProvider {
    private final List<GameElement> elements;
    private final List<Monster> monsters;
    private final List<Particle> particles;
    private final List<Projectile> projectiles;
    private final List<Effect> effects;

    private Camera camera;
    private Dylan dylan;

    public Arena() throws IOException {
        this.camera = new Camera(new Vector2D(0, 0));
        this.elements = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.particles = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.effects = new ArrayList<>();
    }

    public void addElement(GameElement element) {
        switch (element) {
            case Camera camera1 -> this.camera = camera1;
            case Dylan dylan1 -> this.dylan = dylan1;
            case Particle particle -> this.particles.add(particle);
            case Monster monster -> this.monsters.add(monster);
            case Projectile projectile -> this.projectiles.add(projectile);
            case null -> throw new RuntimeException("Trying to add null Element to Arena");
            default -> this.elements.add(element);
        }
    }

    public Camera getCamera() {
        return this.camera;
    }

    public Dylan getDylan() {
        return this.dylan;
    }

    public void setDylan(Dylan dylan) {
        this.dylan = dylan;
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

    public List<Projectile> getProjectiles() {
        return this.projectiles;
    }

    public List<Effect> getEffects() {
        return this.effects;
    }
}
