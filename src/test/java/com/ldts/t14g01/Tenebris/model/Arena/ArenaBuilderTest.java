package com.ldts.t14g01.Tenebris.model.arena;


import com.ldts.t14g01.Tenebris.model.arena.entities.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entities.monster.*;
import com.ldts.t14g01.Tenebris.model.arena.static_elements.*;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import java.io.IOException;
import java.util.List;

public class ArenaBuilderTest {
    private Arena arena;
    private SaveData saveData;

    @BeforeEach
    void setUp() {
        this.saveData = Mockito.mock(SaveData.class);
    }

    @Test
    void verifyEasyCreation() throws IOException {
        try (MockedStatic<SaveDataManager> saveManagerMocked = Mockito.mockStatic(SaveDataManager.class)) {
            SaveDataManager saveDataManager = Mockito.mock(SaveDataManager.class);
            saveManagerMocked.when(SaveDataManager::getInstance).thenReturn(saveDataManager);

            Mockito.when(saveData.getDifficulty()).thenReturn(Difficulty.Easy);
            Mockito.when(saveData.getLevel()).thenReturn(-1);
            this.arena = ArenaBuilder.build(saveData);

            Mockito.verify(saveDataManager, Mockito.times(1)).markAsLastOpen(saveData);
        }
        // get Camera position must be different from (0,0) as to have the default camera not pass
        Camera camera = arena.getCamera();
        Assertions.assertNotNull(camera);
        Assertions.assertEquals(new Vector2D(1, 2), camera.getPosition());

        // get Dylan
        Dylan dylan = arena.getDylan();
        Assertions.assertNotNull(dylan);
        Assertions.assertEquals(new Vector2D(10, 28), dylan.getPosition());
        Assertions.assertEquals(150, dylan.getMaxHP());

        // get Monsters
        List<Monster> monsters = arena.getMonsters();
        Assertions.assertEquals(5, monsters.size());

        for (Monster monster : monsters) {
            switch (monster) {
                case TenebrisPeon tenebrisPeon -> {
                    Assertions.assertEquals(new Vector2D(90, 90), tenebrisPeon.getPosition());
                    Assertions.assertEquals(15,tenebrisPeon.getHp());
                    Assertions.assertEquals(2,tenebrisPeon.getVelocity());
                    Assertions.assertEquals(10,tenebrisPeon.getPlayerDamage());
                    Assertions.assertEquals(50,tenebrisPeon.getVisionRange());
                }
                case TenebrisHeavy tenebrisHeavy -> {
                    Assertions.assertEquals(new Vector2D(40, 20), tenebrisHeavy.getPosition());
                    Assertions.assertEquals(25,tenebrisHeavy.getHp());
                    Assertions.assertEquals(1,tenebrisHeavy.getVelocity());
                    Assertions.assertEquals(25,tenebrisHeavy.getPlayerDamage());
                    Assertions.assertEquals(35,tenebrisHeavy.getVisionRange());
                }
                case TenebrisSpikedScout tenebrisSpikedScout -> {
                    Assertions.assertEquals(new Vector2D(60, 60), tenebrisSpikedScout.getPosition());
                    Assertions.assertEquals(15,tenebrisSpikedScout.getHp());
                    Assertions.assertEquals(3,tenebrisSpikedScout.getVelocity());
                    Assertions.assertEquals(10,tenebrisSpikedScout.getPlayerDamage());
                    Assertions.assertEquals(30,tenebrisSpikedScout.getVisionRange());
                }
                case TenebrisHarbinger tenebrisHarbinger -> {
                    Assertions.assertEquals(new Vector2D(80, 80), tenebrisHarbinger.getPosition());
                    Assertions.assertEquals(30,tenebrisHarbinger.getHp());
                    Assertions.assertEquals(20,tenebrisHarbinger.getPlayerDamage());
                    Assertions.assertEquals(125,tenebrisHarbinger.getVisionRange());
                    Assertions.assertEquals(75,tenebrisHarbinger.getShootingRange());
                }
                case TenebrisWarden tenebrisWarden -> {
                    Assertions.assertEquals(new Vector2D(100, 100), tenebrisWarden.getPosition());
                    Assertions.assertEquals(50,tenebrisWarden.getHp());
                    Assertions.assertEquals(1,tenebrisWarden.getVelocity());
                    Assertions.assertEquals(25,tenebrisWarden.getPlayerDamage());
                    Assertions.assertEquals(40,tenebrisWarden.getVisionRange());
                }
                default -> throw new IllegalStateException("Unexpected value: " + monster);
            }
        }

        // get Static Elements
        List<GameElement> elements = arena.getElements();
        Assertions.assertEquals(5, elements.size());

        for (GameElement element : elements) {
            switch (element) {
                case Wall wall -> Assertions.assertEquals(new Vector2D(10, 10), wall.getPosition());
                case SandBag sandBag -> Assertions.assertEquals(new Vector2D(14, 14), sandBag.getPosition());
                case Spike spike -> {
                    Assertions.assertEquals(new Vector2D(20, 20), spike.getPosition());
                    Assertions.assertEquals(10,spike.getEntityDamage());
                }
                case BreakableWall breakableWall -> {
                    Assertions.assertEquals(new Vector2D(5, 5), breakableWall.getPosition());
                    Assertions.assertEquals(15,breakableWall.getHp());
                }
                case VisionBlocker visionBlocker -> Assertions.assertEquals(new Vector2D(30,30), visionBlocker.getPosition());
                default -> throw new IllegalStateException("Unexpected value: " + element);
            }
        }
    }

    @Test
    void verifyNormalCreation() throws IOException {

        try (MockedStatic<SaveDataManager> saveManagerMocked = Mockito.mockStatic(SaveDataManager.class)) {
            SaveDataManager saveDataManager = Mockito.mock(SaveDataManager.class);
            saveManagerMocked.when(SaveDataManager::getInstance).thenReturn(saveDataManager);

            Mockito.when(saveData.getDifficulty()).thenReturn(Difficulty.Normal);
            Mockito.when(saveData.getLevel()).thenReturn(-1);
            this.arena = ArenaBuilder.build(saveData);

            Mockito.verify(saveDataManager, Mockito.times(1)).markAsLastOpen(saveData);
        }

        // get Dylan
        Dylan dylan = arena.getDylan();
        Assertions.assertNotNull(dylan);
        Assertions.assertEquals(new Vector2D(10, 28), dylan.getPosition());
        Assertions.assertEquals(100, dylan.getMaxHP());

        // get Monsters
        List<Monster> monsters = arena.getMonsters();
        Assertions.assertEquals(5, monsters.size());

        for (Monster monster : monsters) {
            switch (monster) {
                case TenebrisPeon tenebrisPeon -> {
                    Assertions.assertEquals(new Vector2D(90, 90), tenebrisPeon.getPosition());
                    Assertions.assertEquals(20,tenebrisPeon.getHp());
                    Assertions.assertEquals(2,tenebrisPeon.getVelocity());
                    Assertions.assertEquals(15,tenebrisPeon.getPlayerDamage());
                    Assertions.assertEquals(80,tenebrisPeon.getVisionRange());
                }
                case TenebrisHeavy tenebrisHeavy -> {
                    Assertions.assertEquals(new Vector2D(40, 20), tenebrisHeavy.getPosition());
                    Assertions.assertEquals(40,tenebrisHeavy.getHp());
                    Assertions.assertEquals(1,tenebrisHeavy.getVelocity());
                    Assertions.assertEquals(35,tenebrisHeavy.getPlayerDamage());
                    Assertions.assertEquals(55,tenebrisHeavy.getVisionRange());
                }
                case TenebrisSpikedScout tenebrisSpikedScout -> {
                    Assertions.assertEquals(new Vector2D(60, 60), tenebrisSpikedScout.getPosition());
                    Assertions.assertEquals(25,tenebrisSpikedScout.getHp());
                    Assertions.assertEquals(4,tenebrisSpikedScout.getVelocity());
                    Assertions.assertEquals(15,tenebrisSpikedScout.getPlayerDamage());
                    Assertions.assertEquals(40,tenebrisSpikedScout.getVisionRange());
                }
                case TenebrisHarbinger tenebrisHarbinger -> {
                    Assertions.assertEquals(new Vector2D(80, 80), tenebrisHarbinger.getPosition());
                    Assertions.assertEquals(35,tenebrisHarbinger.getHp());
                    Assertions.assertEquals(30,tenebrisHarbinger.getPlayerDamage());
                    Assertions.assertEquals(200,tenebrisHarbinger.getVisionRange());
                    Assertions.assertEquals(100,tenebrisHarbinger.getShootingRange());
                }
                case TenebrisWarden tenebrisWarden -> {
                    Assertions.assertEquals(new Vector2D(100, 100), tenebrisWarden.getPosition());
                    Assertions.assertEquals(75,tenebrisWarden.getHp());
                    Assertions.assertEquals(1,tenebrisWarden.getVelocity());
                    Assertions.assertEquals(35,tenebrisWarden.getPlayerDamage());
                    Assertions.assertEquals(50,tenebrisWarden.getVisionRange());
                }
                default -> throw new IllegalStateException("Unexpected value: " + monster);
            }
        }

        // get Static Elements
        List<GameElement> elements = arena.getElements();
        Assertions.assertEquals(5, elements.size());

        for (GameElement element : elements) {
            switch (element) {
                case Wall wall -> Assertions.assertEquals(new Vector2D(10, 10), wall.getPosition());
                case SandBag sandBag -> Assertions.assertEquals(new Vector2D(14, 14), sandBag.getPosition());
                case Spike spike -> {
                    Assertions.assertEquals(new Vector2D(20, 20), spike.getPosition());
                    Assertions.assertEquals(15,spike.getEntityDamage());
                }
                case BreakableWall breakableWall -> {
                    Assertions.assertEquals(new Vector2D(5, 5), breakableWall.getPosition());
                    Assertions.assertEquals(30,breakableWall.getHp());
                }
                case VisionBlocker visionBlocker -> Assertions.assertEquals(new Vector2D(30,30), visionBlocker.getPosition());
                default -> throw new IllegalStateException("Unexpected value: " + element);
            }
        }
    }

    @Test
    void verifyHeartlessCreation() throws IOException {
        try (MockedStatic<SaveDataManager> saveManagerMocked = Mockito.mockStatic(SaveDataManager.class)) {
            SaveDataManager saveDataManager = Mockito.mock(SaveDataManager.class);
            saveManagerMocked.when(SaveDataManager::getInstance).thenReturn(saveDataManager);

            Mockito.when(saveData.getDifficulty()).thenReturn(Difficulty.Champion);
            Mockito.when(saveData.getLevel()).thenReturn(-1);
            this.arena = ArenaBuilder.build(saveData);

            Mockito.verify(saveDataManager, Mockito.times(1)).markAsLastOpen(saveData);
        }

        // get Dylan
        Dylan dylan = arena.getDylan();
        Assertions.assertNotNull(dylan);
        Assertions.assertEquals(new Vector2D(10, 28), dylan.getPosition());
        Assertions.assertEquals(100, dylan.getMaxHP());

        // get Monsters
        List<Monster> monsters = arena.getMonsters();
        Assertions.assertEquals(5, monsters.size());

        for (Monster monster : monsters) {
            switch (monster) {
                case TenebrisPeon tenebrisPeon -> {
                    Assertions.assertEquals(new Vector2D(90, 90), tenebrisPeon.getPosition());
                    Assertions.assertEquals(25,tenebrisPeon.getHp());
                    Assertions.assertEquals(3,tenebrisPeon.getVelocity());
                    Assertions.assertEquals(25,tenebrisPeon.getPlayerDamage());
                    Assertions.assertEquals(100,tenebrisPeon.getVisionRange());
                }
                case TenebrisHeavy tenebrisHeavy -> {
                    Assertions.assertEquals(new Vector2D(40, 20), tenebrisHeavy.getPosition());
                    Assertions.assertEquals(50,tenebrisHeavy.getHp());
                    Assertions.assertEquals(2,tenebrisHeavy.getVelocity());
                    Assertions.assertEquals(40,tenebrisHeavy.getPlayerDamage());
                    Assertions.assertEquals(80,tenebrisHeavy.getVisionRange());
                }
                case TenebrisSpikedScout tenebrisSpikedScout -> {
                    Assertions.assertEquals(new Vector2D(60, 60), tenebrisSpikedScout.getPosition());
                    Assertions.assertEquals(30,tenebrisSpikedScout.getHp());
                    Assertions.assertEquals(5,tenebrisSpikedScout.getVelocity());
                    Assertions.assertEquals(25,tenebrisSpikedScout.getPlayerDamage());
                    Assertions.assertEquals(50,tenebrisSpikedScout.getVisionRange());
                }
                case TenebrisHarbinger tenebrisHarbinger -> {
                    Assertions.assertEquals(new Vector2D(80, 80), tenebrisHarbinger.getPosition());
                    Assertions.assertEquals(40,tenebrisHarbinger.getHp());
                    Assertions.assertEquals(35,tenebrisHarbinger.getPlayerDamage());
                    Assertions.assertEquals(250,tenebrisHarbinger.getVisionRange());
                    Assertions.assertEquals(150,tenebrisHarbinger.getShootingRange());
                }
                case TenebrisWarden tenebrisWarden -> {
                    Assertions.assertEquals(new Vector2D(100, 100), tenebrisWarden.getPosition());
                    Assertions.assertEquals(100,tenebrisWarden.getHp());
                    Assertions.assertEquals(2,tenebrisWarden.getVelocity());
                    Assertions.assertEquals(45,tenebrisWarden.getPlayerDamage());
                    Assertions.assertEquals(75,tenebrisWarden.getVisionRange());
                }
                default -> throw new IllegalStateException("Unexpected value: " + monster);
            }
        }

        // get Static Elements
        List<GameElement> elements = arena.getElements();
        Assertions.assertEquals(5, elements.size());

        for (GameElement element : elements) {
            switch (element) {
                case Wall wall -> Assertions.assertEquals(new Vector2D(10, 10), wall.getPosition());
                case SandBag sandBag -> Assertions.assertEquals(new Vector2D(14, 14), sandBag.getPosition());
                case Spike spike -> {
                    Assertions.assertEquals(new Vector2D(20, 20), spike.getPosition());
                    Assertions.assertEquals(20,spike.getEntityDamage());
                }
                case BreakableWall breakableWall -> {
                    Assertions.assertEquals(new Vector2D(5, 5), breakableWall.getPosition());
                    Assertions.assertEquals(35,breakableWall.getHp());
                }
                case VisionBlocker visionBlocker -> Assertions.assertEquals(new Vector2D(30,30), visionBlocker.getPosition());
                default -> throw new IllegalStateException("Unexpected value: " + element);
            }
        }
    }

    @Test
    void fileNotFoundTest(){
        Mockito.when(saveData.getDifficulty()).thenReturn(Difficulty.Normal);
        Mockito.when(saveData.getLevel()).thenReturn(-3);
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> arena = ArenaBuilder.build(saveData) );
        Assertions.assertEquals("Trying to load a level that doesn't exist",exception.getMessage());
    }
}
