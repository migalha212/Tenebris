package com.ldts.t14g01.Tenebris.controller;

import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.StateChanger;

import java.io.IOException;
import java.util.Set;

public class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void tick(Action action, StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {}

    public void tickWithList(Set<Action> actions, StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {}
}
