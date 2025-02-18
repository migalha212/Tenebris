package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.model.arena.Camera;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena._commands.*;
import com.ldts.t14g01.Tenebris.model.arena.animation.CameraShake;
import com.ldts.t14g01.Tenebris.model.arena.effects.Effect;
import com.ldts.t14g01.Tenebris.model.arena.entities.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.Monster;
import com.ldts.t14g01.Tenebris.model.arena.particles.*;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.Projectile;
import com.ldts.t14g01.Tenebris.model.menu.*;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.utils.HitBox;
import com.ldts.t14g01.Tenebris.utils.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ArenaController extends Controller<Arena> implements CommandHandler {
    private final List<Command> commands;
    private int endCounter;

    public ArenaController(Arena model) {
        super(model);
        this.commands = new ArrayList<>();
        this.endCounter = 60;
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
                if (HitBox.collide(element.getPosition(), element.getHitBox(), monster.getPosition(), monster.getHitBox()))
                    collisions.add(new Pair<>(element, monster));
            if (dylan != null)
                if (HitBox.collide(element.getPosition(), element.getHitBox(), dylan.getPosition(), dylan.getHitBox()))
                    collisions.add(new Pair<>(dylan, element));
        }

        // Check Collisions between Projectiles and Static Elements
        for (Projectile projectile : projectiles)
            for (GameElement element : elements)
                if (HitBox.collide(projectile.getPosition(), projectile.getHitBox(), element.getPosition(), element.getHitBox()))
                    collisions.add(new Pair<>(projectile, element));

        // Check Collision between Projectiles and Entities
        for (Projectile projectile : projectiles) {
            for (Monster monster : monsters)
                if (HitBox.collide(projectile.getPosition(), projectile.getHitBox(), monster.getPosition(), monster.getHitBox()))
                    collisions.add(new Pair<>(projectile, monster));
            if (dylan != null)
                if (HitBox.collide(projectile.getPosition(), projectile.getHitBox(), dylan.getPosition(), dylan.getHitBox()))
                    collisions.add(new Pair<>(projectile, dylan));
        }

        // Check Collisions between Effects and Static Elements
        for (Effect effect : effects)
            for (GameElement element : elements)
                if (HitBox.collide(effect.getPosition(), effect.getHitBox(), element.getPosition(), element.getHitBox()))
                    collisions.add(new Pair<>(effect, element));

        // Check Collisions between Effects and Entities
        for (Effect effect : effects) {
            for (Monster monster : monsters)
                if (HitBox.collide(effect.getPosition(), effect.getHitBox(), monster.getPosition(), monster.getHitBox()))
                    collisions.add(new Pair<>(effect, monster));
            if (dylan != null)
                if (HitBox.collide(effect.getPosition(), effect.getHitBox(), dylan.getPosition(), dylan.getHitBox()))
                    collisions.add(new Pair<>(effect, dylan));
        }

        // Check Collisions between Monsters
        for (int i = 0; i < monsters.size(); i++)
            for (int j = i + 1; j < monsters.size(); j++) {
                Monster monster1 = monsters.get(i);
                Monster monster2 = monsters.get(j);
                if (HitBox.collide(monster1.getPosition(), monster1.getHitBox(), monster2.getPosition(), monster2.getHitBox()))
                    collisions.add(new Pair<>(monster1, monster2));
            }

        // Check Collisions between Dylan and Monsters
        if (dylan != null)
            for (Monster monster : monsters)
                if (HitBox.collide(monster.getPosition(), monster.getHitBox(), dylan.getPosition(), dylan.getHitBox()))
                    collisions.add(new Pair<>(dylan, monster));

        for (Pair<GameElement> p : collisions) {
            p.first.interact(p.second).forEach(this::handleCommand);
            p.second.interact(p.first).forEach(this::handleCommand);
        }
    }

    private void triggerCommands() {
        Arena arena = this.getModel();
        Camera camera = this.getModel().getCamera();
        List<GameElement> elements = this.getModel().getElements();
        List<Monster> monsters = this.getModel().getMonsters();
        List<Projectile> projectiles = this.getModel().getProjectiles();
        List<Particle> particles = this.getModel().getParticles();
        List<Effect> effects = this.getModel().getEffects();

        this.commands.forEach(command -> {
            switch (command) {
                // Wall
                case DeleteBreakableWall c -> elements.remove(((DeleteBreakableWall) command).breakableWall());

                // Particles
                case CreateParticle c -> {
                    switch (((CreateParticle) command).type()) {
                        case DAMAGE_BLOOD -> arena.addElement(new DamageBlood(((CreateParticle) command).position()));
                        case DEATH_BLOOD -> arena.addElement(new DeathBlood(((CreateParticle) command).position()));
                        case SPELL_EXPLOSION ->
                                arena.addElement(new SpellExplosion(((CreateParticle) command).position()));
                        case BREAKABLE_WALL_DAMAGE ->
                                arena.addElement(new BreakableWallDamage(((CreateParticle) command).position()));
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

                // Camera Shake
                case ShakeCamera c -> camera.setAnimation(new CameraShake(camera));
                case null, default -> {
                }
            }
        });
    }

    @Override
    public void tick(StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException, InterruptedException {
        Action action = GUI.getGUI().getAction();
        switch (action) {
            case ESC -> stateChanger.setState(new MenuState(new PauseMenu(this.getModel())));
            case QUIT -> stateChanger.setState(null);
            case SELECT_1, SELECT_2, RELOAD -> this.getModel().getDylan().getController().updateWeapon(action);
            case null, default -> {
            }
        }

        Set<Action> activeActions = GUI.getGUI().getActiveActions();

        // If Dylan not Dead
        Dylan dylan = this.getModel().getDylan();
        if (dylan != null) {
            // Update Dylan
            DylanController dylanController = dylan.getController();
            dylanController.setLooking(null);
            Set<Action> dylan_moves = new TreeSet<>();
            activeActions.forEach((activeAction -> {
                switch (activeAction) {
                    case MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT -> dylan_moves.add(activeAction);
                    case LOOK_UP, LOOK_DOWN, LOOK_LEFT, LOOK_RIGHT -> dylanController.setLooking(activeAction);
                    case EXEC -> dylanController.shoot(this);
                    case null, default -> {
                    }
                }
            }));
            dylanController.setMoving(dylan_moves);
            dylanController.update();

            // Update Camera
            this.getModel().getCamera().getController().update(dylan.getPosition());

            // Update Monsters with Dylan position
            List<Monster> monsters = this.getModel().getMonsters();
            monsters.forEach(monster -> monster.getController().update(dylan.getPosition(), this.getModel(), this));
        }

        // Update Effects
        List<Effect> effects = this.getModel().getEffects();
        effects.forEach(effect -> effect.getController().update(this));

        // Update Projectiles
        List<Projectile> projectiles = this.getModel().getProjectiles();
        projectiles.forEach(projectile -> projectile.getController().update());

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

        // If no monsters alive then player wins
        if (this.getModel().getMonsters().isEmpty() && saveDataProvider.getSaveData().getLevel() != SaveData.MAX_LEVEL) {
            this.endCounter--;
            if (this.endCounter <= 0) {
                SoundManager.getInstance().playSFX(SoundManager.SFX.LEVEL_COMPLETED);
                saveDataProvider.getSaveData().increaseLevel();
                stateChanger.setState(new MenuState(new LevelCompletedMenu()));
                return;
            }
        }

        // If last level show Victory screen
        else if (this.getModel().getMonsters().isEmpty()) {
            this.endCounter--;
            if (this.endCounter <= 0) {
                SoundManager.getInstance().playSFX(SoundManager.SFX.GAME_VICTORY);
                stateChanger.setState(new MenuState(new VictoryMenu()));
                return;
            }
        }

        // Dylan is Dead
        if (dylan == null) {
            this.endCounter--;
            if (this.endCounter <= 0) {
                if (saveDataProvider.getSaveData().getDifficulty() == Difficulty.Heartless) {
                    stateChanger.setState(new MenuState(new GameOverMenu()));
                    SaveDataManager.getInstance().deleteSave(saveDataProvider.getSaveData());
                    saveDataProvider.setSaveData(null);
                } else stateChanger.setState(new MenuState(new DeathMenu()));
            }
        }
    }

    @Override
    public void handleCommand(Command command) {
        this.commands.add(command);
    }
}
