package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.effects.Effect;
import com.ldts.t14g01.Tenebris.model.arena.effects.Explosion;
import com.ldts.t14g01.Tenebris.model.arena.entities.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.TenebrisHeavy;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.TenebrisPeon;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.TenebrisWarden;
import com.ldts.t14g01.Tenebris.model.arena.particles.DamageBlood;
import com.ldts.t14g01.Tenebris.model.arena.particles.SpellExplosion;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.Bullet;
import com.ldts.t14g01.Tenebris.model.arena.projectiles.Projectile;
import com.ldts.t14g01.Tenebris.model.arena.static_elements.Spike;
import com.ldts.t14g01.Tenebris.model.arena.static_elements.Wall;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ArenaTest {
    private Arena arena;
    private Vector2D position;

    @BeforeEach
    void setUp() throws IOException {
        this.arena = new Arena();
        this.position = new Vector2D(10, 20);
    }

    @Test
    void getCameraTest() {
        Camera camera = new Camera(this.position);
        this.arena.addElement(camera);

        Assertions.assertSame(camera, this.arena.getCamera());
    }

    @Test
    void getDylanTest() {
        Dylan dylan = new Dylan(this.position, 20, 30);
        this.arena.setDylan(dylan);
        Assertions.assertSame(dylan, this.arena.getDylan());
    }

    @Test
    void getMonstersTest() {
        TenebrisPeon peon = new TenebrisPeon(position, 10, 20, 30, 4);
        TenebrisHeavy heavy = new TenebrisHeavy(position, 10, 20, 30, 4);
        TenebrisWarden warden = new TenebrisWarden(position, 10, 20, 30, 4);

        this.arena.addElement(peon);
        this.arena.addElement(heavy);
        this.arena.addElement(warden);

        Assertions.assertEquals(3, this.arena.getMonsters().size());

        Assertions.assertSame(peon, this.arena.getMonsters().get(0));
        Assertions.assertSame(heavy, this.arena.getMonsters().get(1));
        Assertions.assertSame(warden, this.arena.getMonsters().get(2));
    }

    @Test
    void getElementTest() {
        Wall wall = new Wall(position);
        Spike spike = new Spike(position, 10);

        this.arena.addElement(wall);
        this.arena.addElement(spike);

        Assertions.assertEquals(2, this.arena.getElements().size());

        Assertions.assertSame(wall, this.arena.getElements().get(0));
        Assertions.assertSame(spike, this.arena.getElements().get(1));
    }

    @Test
    void getParticlesTest() {
        DamageBlood blood = new DamageBlood(position);
        SpellExplosion explosion = new SpellExplosion(position);

        this.arena.addElement(blood);
        this.arena.addElement(explosion);

        Assertions.assertEquals(2, this.arena.getParticles().size());

        Assertions.assertSame(blood, this.arena.getParticles().get(0));
        Assertions.assertSame(explosion, this.arena.getParticles().get(1));
    }

    @Test
    void getProjectilesTest() {
        List<Projectile> projectiles = this.arena.getProjectiles();
        Assertions.assertNotNull(projectiles);

        projectiles.add(new Bullet(position, Vector2D.Direction.UP));

        Assertions.assertEquals(1, projectiles.size());
    }

    @Test
    void getEffectsTest() {
        List<Effect> effects = this.arena.getEffects();
        Assertions.assertNotNull(effects);

        effects.add(new Explosion(position,10));

        Assertions.assertEquals(1, effects.size());
    }

    @Test
    void addNullElementTest() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> this.arena.addElement(null));
        Assertions.assertEquals("Trying to add null Element to Arena", exception.getMessage());
    }
}
