package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.model.arena.particles.DeathBlood;
import com.ldts.t14g01.Tenebris.model.arena.particles.Particle;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.model.menu.PauseMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ArenaController extends Controller<Arena> {
    public ArenaController(Arena model) {
        super(model);
    }

    @Override
    public void tick(Action action, StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {
        switch (action) {
            case ESC -> {
                saveDataProvider.getSaveData().save();
                stateChanger.setState(new MenuState(new MainMenu(saveDataProvider)));
            }
            case QUIT -> stateChanger.setState(null);
            case EXEC -> this.getModel().addElement(new DeathBlood(this.getModel().getDylan().getPosition()));
            case null, default -> {
            }
        }
    }

    @Override
    public void tickWithList(Set<Action> actions, StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {
        // Update Dylan
        DylanController dylanController = this.getModel().getDylan().getController();
        dylanController.setLooking(null);
        Set<Action> dylan_moves = new TreeSet<>();
        actions.forEach((action -> {
            switch (action) {
                case MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT -> dylan_moves.add(action);
                case LOOK_UP, LOOK_DOWN, LOOK_LEFT, LOOK_RIGHT -> dylanController.setLooking(action);
                case null, default -> {
                }
            }
        }));
        dylanController.setMoving(dylan_moves);
        dylanController.move();

        // Update Monsters with Dylan position
        this.getModel().getMonsters().forEach(
                monster -> monster.getController().update(this.getModel().getDylan().getPosition(), this.getModel()));

        // Update Particles
        this.getModel().getParticles().forEach(particles -> particles.getController().update());
        List<Particle> toDelete = new ArrayList<>();
        this.getModel().getParticles().forEach(particle -> {
            if (particle.isOver()) toDelete.add(particle);
        });
        toDelete.forEach(particle -> this.getModel().removeParticle(particle));

        // Check Collisions
        this.getModel().checkCollisions();

        // TODO Handle Player Death
        // Dylan is Dead
        if (this.getModel().getDylan() == null) stateChanger.setState(new MenuState(new MainMenu(saveDataProvider)));
    }
}
