package com.ldts.t14g01.Tenebris.view.arena.entity.monster;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.TenebrisSpikedScout;
import com.ldts.t14g01.Tenebris.view.arena.entity.EntityView;

public class TenebrisSpikedScoutView extends EntityView<TenebrisSpikedScout> {
    public TenebrisSpikedScoutView(TenebrisSpikedScout model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        this.updateState();
        this.tickState();
        gui.drawMonster(this.model.getPosition(), GUI.Monster.TENEBRIS_SPIKED_SCOUT, this.state);
    }
}
