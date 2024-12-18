package com.ldts.t14g01.Tenebris.view.arena;

public abstract class ElementView<T> {
    protected final T model;

    public ElementView(T model) {
        this.model = model;
    }

    public abstract void draw();
}
