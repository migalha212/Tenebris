package com.ldts.t14g01.Tenebris.controller;

import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.StateChanger;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void tick(StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException, InterruptedException;
}
