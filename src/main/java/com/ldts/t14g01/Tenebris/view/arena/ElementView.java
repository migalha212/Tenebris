package com.ldts.t14g01.Tenebris.view.arena;

import com.ldts.t14g01.Tenebris.gui.GUI;

public abstract class ElementView<T> {
    protected final T model;

    public ElementView(T model) {
        this.model = model;
    }

    public abstract void draw(GUI gui);
}
