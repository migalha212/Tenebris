package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.utils.Pair;
import com.ldts.t14g01.Tenebris.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private List<GameElement> elements;

    public Arena() {}

    public void addElement(GameElement element) {
        this.elements.add(element);
    }

    public void checkCollisions() {
        List<Pair<GameElement>> collisions = new ArrayList<>();
        for (GameElement e1 : elements)
            for (GameElement e2 : elements)
                if (e1 != e2)
                    if (Position.inRange(e1.position, e2.position, e1.size + e2.size))
                        collisions.add(new Pair<>(e1, e2));

        for (Pair<GameElement> p: collisions) {
            p.first.interact(p.second);
            p.second.interact(p.first);
        }
    }
}
