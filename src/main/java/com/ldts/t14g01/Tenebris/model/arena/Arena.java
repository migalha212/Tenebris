package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.Monster;
import com.ldts.t14g01.Tenebris.utils.Pair;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final List<GameElement> elements;
    private final List<Monster> monsters;

    private Dylan dylan;

    public Arena() {
        this.elements = new ArrayList<>();
        this.monsters = new ArrayList<>();

        addElement(new Dylan(
                new Vector2D(4, 4),
                2,
                10,
                4
        ));
    }

    public void addElement(GameElement element) {
        if (element instanceof Dylan && this.dylan == null) this.dylan = (Dylan) element;
        else if (element instanceof Monster) this.monsters.add((Monster) element);
        else this.elements.add(element);
    }

    public void checkCollisions() {
        List<Pair<GameElement>> collisions = new ArrayList<>();
        for (GameElement e1 : elements)
            for (GameElement e2 : elements)
                if (e1 != e2)
                    if (e1.getPosition().inRange(e2.getPosition(), e1.getSize() + e2.getSize()))
                        collisions.add(new Pair<>(e1, e2));

        for (Pair<GameElement> p : collisions) {
            p.first.interact(p.second);
            p.second.interact(p.first);
        }
    }

    public Dylan getDylan() {
        return this.dylan;
    }
}
