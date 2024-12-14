package com.ldts.t14g01.Tenebris.controller.arena.monster;

import com.ldts.t14g01.Tenebris.model.arena.entity.monster.Monster;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public abstract class MonsterController<T extends Monster> {
    T model;

    public MonsterController(T model) {
        this.model = model;
    }

    public abstract void update(Vector2D dylanPosition);
}
