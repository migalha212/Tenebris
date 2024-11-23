package com.ldts.t14g01.Tenebris.state;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final View<T> view;
    private final Controller<T> controller;

    public State(T model) {
        this.model = model;
        this.view = getView();
        this.controller = getController();
    }

    public T getModel() {
        return model;
    }

    protected abstract View<T> getView();

    protected abstract Controller<T> getController();

    public void tick(GUI gui, StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException, InterruptedException {
        Action action = gui.getAction();
        controller.tick(action, stateChanger, saveDataProvider);
        view.draw(gui);
    }
}
