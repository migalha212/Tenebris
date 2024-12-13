package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.Monster;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final List<GameElement> elements;
    private final List<Monster> monsters;

    private Dylan dylan;

    public Arena() throws IOException {
        this.elements = new ArrayList<>();
        this.monsters = new ArrayList<>();

        // TODO Implement Arena Builder
        addElement(new Dylan(
                new Vector2D(100, 100),
                10,
                2
        ));
    }

    public void addElement(GameElement element) {
        if (element instanceof Dylan) this.dylan = (Dylan) element;
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
}
