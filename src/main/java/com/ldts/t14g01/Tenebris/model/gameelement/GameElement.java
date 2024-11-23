package com.ldts.t14g01.Tenebris.model.gameelement;

import com.ldts.t14g01.Tenebris.utils.Position;

public class GameElement {
    private Position position;

    public GameElement(int x, int y) {
        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
