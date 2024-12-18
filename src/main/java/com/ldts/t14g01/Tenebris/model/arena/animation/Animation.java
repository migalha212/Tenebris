package com.ldts.t14g01.Tenebris.model.arena.animation;

public abstract class Animation<T> {
    T model;

    public Animation(T model) {
        this.model = model;
    }

    public abstract boolean isOver();

    public abstract void execute();
}
