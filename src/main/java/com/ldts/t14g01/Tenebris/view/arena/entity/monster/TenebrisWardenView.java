package com.ldts.t14g01.Tenebris.view.arena.entity.monster;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.TenebrisWarden;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.EntityView;

public class TenebrisWardenView extends EntityView<TenebrisWarden> {
    public TenebrisWardenView(TenebrisWarden model) {
        super(model);
    }

    @Override
    public void draw(Vector2D cameraOffset) {
        this.updateState();
        this.tickState();
        GUI.getGUI().drawMonster(
                this.model.getPosition().minus(cameraOffset),
                GUI.Monster.TENEBRIS_WARDEN,
                this.state
        );
    }
}
