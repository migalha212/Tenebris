package com.ldts.t14g01.Tenebris.view.arena.entity.monster;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.TenebrisWarden;
import com.ldts.t14g01.Tenebris.view.arena.entity.EntityView;

public class TenebrisWardenView extends EntityView<TenebrisWarden> {
    public TenebrisWardenView(TenebrisWarden model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        this.updateState();
        this.tickState();
        gui.drawMonster(this.model.getPosition(), GUI.Monster.TENEBRIS_WARDEN, this.state);
    }
}
