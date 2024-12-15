package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.*;
import com.ldts.t14g01.Tenebris.model.arena.interfaces.ElementProvider;
import com.ldts.t14g01.Tenebris.model.arena.particles.Particle;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.SandBag;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Spike;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Wall;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena implements ElementProvider {
    private final List<GameElement> elements;
    private final List<Monster> monsters;
    private final List<Particle> particles;

    private Dylan dylan;

    public Arena() throws IOException {
        this.elements = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.particles = new ArrayList<>();

        // TODO Implement Arena Builder
        addElement(new Dylan(new Vector2D(100, 100), 10, 2));

        addElement(new Wall(new Vector2D(150, 150)));
        addElement(new SandBag(new Vector2D(190, 150)));
        addElement(new Spike(new Vector2D(170, 170)));

        addElement(new TenebrisWarden(new Vector2D(250, 150)));
        addElement(new TenebrisSpikedScout(new Vector2D(20, 20)));
        addElement(new TenebrisHeavy(new Vector2D(250, 30)));
        addElement(new TenebrisHarbinger(new Vector2D(250, 50)));
        addElement(new TenebrisPeon(new Vector2D(10, 10)));
    }

    public void addElement(GameElement element) {
        if (element instanceof Dylan) this.dylan = (Dylan) element;
        else if (element instanceof Particle) this.particles.add((Particle) element);
        else if (element instanceof Monster) this.monsters.add((Monster) element);
        else this.elements.add(element);
    }

    public void checkCollisions() {
        // TODO CHECK THIS COLLISIONS WITH MONSTERS AND DYLAN ARE NOT ACCOUNTED FOR
        /*
        List<Pair<GameElement>> collisions = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++)
            for (int j = i + 1; j < elements.size(); j++)
                if (elements.get(i) != elements.get(j))
                    if (elements.get(i).getPosition().inRange(
                            elements.get(j).getPosition(),
                            elements.get(i).getSize() + elements.get(j).getSize()))
                        collisions.add(new Pair<>(elements.get(i), elements.get(j)));

        for (Pair<GameElement> p : collisions) {
            p.first.interact(p.second);
            p.second.interact(p.first);
        }
        */
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
}
