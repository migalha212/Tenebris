package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.controller.arena.CameraController;
import com.ldts.t14g01.Tenebris.model.arena._commands.Command;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Camera extends GameElement {
    private CameraController controller;

    public Camera(Vector2D position) {
        super(position, new HitBoX(new Vector2D(0, 0), new Vector2D(0, 0)));
        this.controller = new CameraController(this);
    }

    public CameraController getController() {
        return this.controller;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    // Empty because this element doesn't interact
    @Override
    public List<Command> interact(GameElement other) {
        return new ArrayList<>();
    }
}
