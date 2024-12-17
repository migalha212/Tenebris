package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.model.arena.ArenaBuilder;
import com.ldts.t14g01.Tenebris.model.arena.Commands.*;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.effects.Effect;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.Monster;
import com.ldts.t14g01.Tenebris.model.arena.particles.DamageBlood;
import com.ldts.t14g01.Tenebris.model.arena.particles.DeathBlood;
import com.ldts.t14g01.Tenebris.model.arena.particles.Particle;
import com.ldts.t14g01.Tenebris.model.arena.particles.SpellExplosion;
import com.ldts.t14g01.Tenebris.model.arena.projectile.Projectile;
import com.ldts.t14g01.Tenebris.model.menu.DeathMenu;
import com.ldts.t14g01.Tenebris.model.menu.GameOverMenu;
import com.ldts.t14g01.Tenebris.model.menu.PauseMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.ArenaState;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.utils.HitBoX;
import com.ldts.t14g01.Tenebris.utils.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ArenaController extends Controller<Arena> implements CommandHandler {
    private final List<Command> commands;

    public ArenaController(Arena model) {
        super(model);
        this.commands = new ArrayList<>();
    }

    public void checkCollisions() {
        List<Pair<GameElement>> collisions = new ArrayList<>();
        Dylan dylan = this.getModel().getDylan();
        List<GameElement> elements = this.getModel().getElements();
        List<Monster> monsters = this.getModel().getMonsters();
        List<Projectile> projectiles = this.getModel().getProjectiles();
        List<Effect> effects = this.getModel().getEffects();

        // Check Collisions between Entities and Static Elements
        for (GameElement element : elements) {
            for (Monster monster : monsters)
                if (HitBoX.collide(element.getPosition(), element.getHitBox(), monster.getPosition(), monster.getHitBox()))
                    collisions.add(new Pair<>(element, monster));
            if (HitBoX.collide(element.getPosition(), element.getHitBox(), dylan.getPosition(), dylan.getHitBox()))
                collisions.add(new Pair<>(dylan, element));
        }

        // Check Collision between Projectiles and Entities
        for (Projectile projectile : projectiles) {
            for (Monster monster : monsters)
                if (HitBoX.collide(projectile.getPosition(), projectile.getHitBox(), monster.getPosition(), monster.getHitBox()))
                    collisions.add(new Pair<>(projectile, monster));
            if (HitBoX.collide(projectile.getPosition(), projectile.getHitBox(), dylan.getPosition(), dylan.getHitBox()))
                collisions.add(new Pair<>(projectile, dylan));
        }

        // Check Collisions between Effects and Entities
        for (Effect effect : effects) {
            for (Monster monster : monsters)
                if (HitBoX.collide(effect.getPosition(), effect.getHitBox(), monster.getPosition(), monster.getHitBox()))
                    collisions.add(new Pair<>(effect, monster));
            if (HitBoX.collide(effect.getPosition(), effect.getHitBox(), dylan.getPosition(), dylan.getHitBox()))
                collisions.add(new Pair<>(effect, dylan));
        }

        // Check Collisions between Monsters
        for (int i = 0; i < monsters.size(); i++)
            for (int j = i + 1; j < monsters.size(); j++) {
                Monster monster1 = monsters.get(i);
                Monster monster2 = monsters.get(j);
                if (HitBoX.collide(monster1.getPosition(), monster1.getHitBox(), monster2.getPosition(), monster2.getHitBox()))
                    collisions.add(new Pair<>(monster1, monster2));
            }

        // Check Collisions between Dylan and Monsters
        for (Monster monster : monsters)
            if (HitBoX.collide(monster.getPosition(), monster.getHitBox(), dylan.getPosition(), dylan.getHitBox()))
                collisions.add(new Pair<>(dylan, monster));

        for (Pair<GameElement> p : collisions) {
            p.first.interact(p.second).forEach(this::handleCommand);
            p.second.interact(p.first).forEach(this::handleCommand);
        }
    }

    private void triggerCommands() {
        Arena arena = this.getModel();
        List<Monster> monsters = this.getModel().getMonsters();
        List<Projectile> projectiles = this.getModel().getProjectiles();
        List<Particle> particles = this.getModel().getParticles();
        List<Effect> effects = this.getModel().getEffects();

        this.commands.forEach(command -> {
            switch (command) {
                // Particles
                case CreateParticle c -> {
                    switch (((CreateParticle) command).type()) {
                        case DAMAGE_BLOOD -> arena.addElement(new DamageBlood(((CreateParticle) command).position()));
                        case DEATH_BLOOD -> arena.addElement(new DeathBlood(((CreateParticle) command).position()));
                        case SPELL_EXPLOSION ->
                                arena.addElement(new SpellExplosion(((CreateParticle) command).position()));
                        case null, default ->
                                throw new RuntimeException("Command tried to create an invalid type of particle");
                    }
                }

                case DeleteParticle c -> particles.remove(((DeleteParticle) command).particle());

                // Projectiles
                case CreateProjectile c -> projectiles.add(((CreateProjectile) command).projectile());
                case DeleteProjectile c -> projectiles.remove(((DeleteProjectile) command).projectile());

                // Effects
                case CreateEffect c -> effects.add(((CreateEffect) command).effect());
                case DeleteEffect c -> effects.remove(((DeleteEffect) command).effect());

                // Kills
                case DeleteMonster c -> monsters.remove(((DeleteMonster) command).monster());
                case KillDylan c -> this.getModel().setDylan(null);
                case null, default -> {
                }
            }
        });
    }

    @Override
    public void tick(Action action, StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {
        switch (action) {
            case ESC -> stateChanger.setState(new MenuState(new PauseMenu(this.getModel())));
            case QUIT -> stateChanger.setState(null);
            case SELECT_1, SELECT_2 -> this.getModel().getDylan().getController().setSelectedWeapon(action);
            case null, default -> {
            }
        }
    }

    @Override
    public void tickWithList(Set<Action> actions, StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {
        // If no monsters alive then player wins
        if (this.getModel().getMonsters().isEmpty()) {
            saveDataProvider.getSaveData().increaseLevel();
            stateChanger.setState(new ArenaState(ArenaBuilder.build(saveDataProvider.getSaveData())));
            return;
        }

        // Update Dylan
        Dylan dylan = this.getModel().getDylan();
        DylanController dylanController = this.getModel().getDylan().getController();
        dylanController.setLooking(null);
        Set<Action> dylan_moves = new TreeSet<>();
        actions.forEach((action -> {
            switch (action) {
                case MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT -> dylan_moves.add(action);
                case LOOK_UP, LOOK_DOWN, LOOK_LEFT, LOOK_RIGHT -> dylanController.setLooking(action);
                case EXEC -> dylanController.shoot(this);
                case null, default -> {
                }
            }
        }));
        dylanController.setMoving(dylan_moves);
        dylanController.update();

        // Update Monsters with Dylan position
        List<Monster> monsters = this.getModel().getMonsters();
        monsters.forEach(monster -> monster.getController().update(dylan.getPosition(), this.getModel(), this));

        // Update Projectiles
        this.getModel().getProjectiles().forEach(projectile -> projectile.getController().update());

        // Update Effects
        this.getModel().getEffects().forEach(effect -> effect.getController().update(this));

        // Update Particles
        List<Particle> particles = this.getModel().getParticles();
        particles.forEach(particle -> particle.getController().update(this));

        // Check Collisions
        this.checkCollisions();

        // Trigger Tick Commands
        // This is Stored and Only Handled here
        // because executing commands in the middle of the tick would break the tick logic
        this.triggerCommands();
        this.commands.clear();

        // Dylan is Dead
        if (this.getModel().getDylan() == null) {
            if (saveDataProvider.getSaveData().getDifficulty() == Difficulty.Heartless)
                stateChanger.setState(new MenuState(new GameOverMenu()));
            else stateChanger.setState(new MenuState(new DeathMenu()));
        }
    }

    @Override
    public void handleCommand(Command command) {
        this.commands.add(command);
    }
}
