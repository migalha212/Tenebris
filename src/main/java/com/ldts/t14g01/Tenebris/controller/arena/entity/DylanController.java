package com.ldts.t14g01.Tenebris.controller.arena.entity;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.StateChanger;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

public class DylanController extends Controller<Dylan> {
    public DylanController(Dylan model) {
        super(model);
    }

    public void update() {
        this.getModel().accelerate();
        this.getModel().move();
    }

    @Override
    public void tick(Action action, StateChanger stateChanger, SaveDataProvider saveDataProvider) {
        Dylan model = this.getModel();
        switch (action) {
            case MOVE_UP -> model.acceleration = Vector2D.fromDirection(Vector2D.Direction.UP, 0.1);
            case MOVE_DOWN -> model.acceleration = Vector2D.fromDirection(Vector2D.Direction.DOWN, 0.1);
            case MOVE_RIGHT -> model.acceleration = Vector2D.fromDirection(Vector2D.Direction.RIGHT, 0.1);
            case MOVE_LEFT -> model.acceleration = Vector2D.fromDirection(Vector2D.Direction.LEFT, 0.1);
            case null, default -> model.acceleration = new Vector2D(0, 0);
        }
    }
}
