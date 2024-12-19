package com.ldts.t14g01.Tenebris.view.arena;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class ArenaView extends View<Arena> {

    public ArenaView(Arena model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        Vector2D cameraOffset = this.getModel().getCamera().getPosition();
        Arena arena = this.getModel();
        GUI.getGUI().drawArenaBackGround();

        // Draw StaticElements
        arena.getElements().forEach(element -> element.getView().draw(cameraOffset));

        // Draw Monsters
        arena.getMonsters().forEach(monster -> monster.getView().draw(cameraOffset));

        // Draw Dylan
        arena.getDylan().getView().draw(cameraOffset);

        // Draw Particles
        arena.getParticles().forEach(particles -> particles.getView().draw(cameraOffset));

        // Draw Effects
        arena.getEffects().forEach(effect -> effect.getView().draw(cameraOffset));

        // Draw Projectiles
        arena.getProjectiles().forEach(projectile -> projectile.getView().draw(cameraOffset));

        // Draw UI
        GUI.getGUI().drawArenaUI(
                this.getModel().getDylan().getMaxHP(),
                this.getModel().getDylan().getHp()
        );
        arena.getDylan().getEquipedWeapon().getView().draw();
    }
}
