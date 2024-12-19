package com.ldts.t14g01.Tenebris.view;

import com.ldts.t14g01.Tenebris.gui.GUI;

import java.io.IOException;

public abstract class View<T> {
    private final T model;

    public View(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void draw() throws IOException {
        this.drawElements();
        GUI.getGUI().refresh();
    }

    protected abstract void drawElements() throws IOException;
}
