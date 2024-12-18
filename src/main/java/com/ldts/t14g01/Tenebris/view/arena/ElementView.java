package com.ldts.t14g01.Tenebris.view.arena;

import com.ldts.t14g01.Tenebris.utils.Vector2D;

public abstract class ElementView<T> {
    protected final T model;

    public ElementView(T model) {
        this.model = model;
    }

    public abstract void draw(Vector2D cameraOffset);
}
