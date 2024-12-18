package com.ldts.t14g01.Tenebris.view.arena.entity.monster;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.TenebrisPeon;
import com.ldts.t14g01.Tenebris.view.arena.entity.EntityView;

public class TenebrisPeonView extends EntityView<TenebrisPeon> {
    public TenebrisPeonView(TenebrisPeon model) {
        super(model);
    }

    @Override
    public void draw() {
        this.updateState();
        this.tickState();
        GUI.getGUI().drawMonster(
                this.model.getPosition(),
                GUI.Monster.TENEBRIS_PEON,
                this.state
        );
    }
}
