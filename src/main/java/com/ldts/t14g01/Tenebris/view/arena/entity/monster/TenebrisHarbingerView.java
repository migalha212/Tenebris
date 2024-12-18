package com.ldts.t14g01.Tenebris.view.arena.entity.monster;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.TenebrisHarbinger;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.arena.entity.EntityView;

public class TenebrisHarbingerView extends EntityView<TenebrisHarbinger> {
    public TenebrisHarbingerView(TenebrisHarbinger model) {
        super(model);
    }

    @Override
    public void draw(Vector2D cameraOffset) {
        this.updateState();
        this.tickState();
        GUI.getGUI().drawMonster(
                this.model.getPosition().minus(cameraOffset),
                GUI.Monster.TENEBRIS_HARBINGER,
                this.state
        );
    }
}
