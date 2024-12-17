package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.*;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.BreakableWall;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.SandBag;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Spike;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Wall;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArenaBuilder {

    private ArenaBuilder() {
    }

    public static Arena build(SaveData saveData) throws IOException {
        SaveDataManager.getInstance().markAsLastOpen(saveData);
        Difficulty difficulty = saveData.getDifficulty();
        return ArenaBuilder.loadFromFile("src/main/resources/levels/" + saveData.getLevel(), difficulty);
    }

    private static Arena loadFromFile(String filePath, Difficulty difficulty) throws IOException {
        // Create Arena
        Arena arena = new Arena();

        // Parse lines
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) ArenaBuilder.parseLine(arena, line.trim(), difficulty);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Trying to load a level that doesn't exist");
        }

        return arena;
    }

    private static void parseLine(Arena arena, String line, Difficulty difficulty) {
        if (line.isEmpty() || line.startsWith("#")) return;
        Vector2D position = ArenaBuilder.parsePosition(line);

        switch (line.split("\\(")[0]) {
            case "Dylan" -> arena.addElement(createDylan(position, difficulty));
            case "Wall" -> arena.addElement(new Wall(position));
            case "SandBag" -> arena.addElement(new SandBag(position));
            case "Spike" -> arena.addElement(createSpike(position, difficulty));
            case "Breakable" -> arena.addElement(createBreakableWall(position, difficulty));
            case "T-W" -> arena.addElement(createTenebrisWarden(position, difficulty));
            case "T-SS" -> arena.addElement(createTenebrisSpikedScout(position, difficulty));
            case "T-Heavy" -> arena.addElement(createTenebrisHeavy(position, difficulty));
            case "T-Harbinger" -> arena.addElement(createTenebrisHarbinger(position, difficulty));
            case "T-P" -> arena.addElement(createTenebrisPeon(position, difficulty));
        }
    }

    private static Vector2D parsePosition(String line) {
        String coords = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
        String[] parts = coords.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        return new Vector2D(x, y);
    }

    private static Dylan createDylan(Vector2D pos, Difficulty difficulty) {
        int hp = 150;
        if (difficulty != Difficulty.Easy) hp = 100;

        return new Dylan(pos, hp, 3);
    }

    private static Spike createSpike(Vector2D pos, Difficulty difficulty) {
        int dmg = 20;
        switch (difficulty) {
            case Easy -> dmg = 10;
            case Normal -> dmg = 15;
        }

        return new Spike(pos, dmg);
    }

    private static TenebrisPeon createTenebrisPeon(Vector2D pos, Difficulty difficulty) {
        int hp = 25;
        int velocity = 3;
        int damage = 25;
        int visionRange = 100;

        switch (difficulty) {
            case Easy -> {
                hp = 15;
                velocity = 2;
                damage = 10;
                visionRange = 50;
            }
            case Normal -> {
                hp = 20;
                velocity = 2;
                damage = 15;
                visionRange = 80;
            }
        }

        return new TenebrisPeon(pos, hp, velocity, damage, visionRange);
    }

    private static TenebrisHeavy createTenebrisHeavy(Vector2D pos, Difficulty difficulty) {
        int hp = 50;
        int velocity = 2;
        int damage = 40;
        int visionRange = 80;

        switch (difficulty) {
            case Easy -> {
                hp = 25;
                velocity = 1;
                damage = 25;
                visionRange = 35;
            }
            case Normal -> {
                hp = 40;
                velocity = 1;
                damage = 35;
                visionRange = 55;
            }
        }

        return new TenebrisHeavy(pos, hp, velocity, damage, visionRange);
    }

    private static TenebrisHarbinger createTenebrisHarbinger(Vector2D pos, Difficulty difficulty) {
        int hp = 40;
        int damage = 35;
        int visionRange = 250;
        int damageRange = 150;

        switch (difficulty) {
            case Easy -> {
                hp = 30;
                damage = 20;
                visionRange = 125;
                damageRange = 75;
            }
            case Normal -> {
                hp = 35;
                damage = 30;
                visionRange = 200;
                damageRange = 100;
            }
        }

        return new TenebrisHarbinger(pos, hp, 2, damage, visionRange, damageRange);
    }

    private static TenebrisWarden createTenebrisWarden(Vector2D pos, Difficulty difficulty) {
        int hp = 100;
        int velocity = 2;
        int damage = 45;
        int visionRange = 75;

        switch (difficulty) {
            case Easy -> {
                hp = 50;
                damage = 25;
                velocity = 1;
                visionRange = 40;
            }
            case Normal -> {
                hp = 75;
                velocity = 1;
                damage = 35;
                visionRange = 50;
            }
        }

        return new TenebrisWarden(pos, hp, velocity, damage, visionRange);
    }

    private static TenebrisSpikedScout createTenebrisSpikedScout(Vector2D pos, Difficulty difficulty) {
        int hp = 30;
        int velocity = 5;
        int damage = 25;
        int visionRange = 50;

        switch (difficulty) {
            case Easy -> {
                hp = 15;
                damage = 10;
                velocity = 3;
                visionRange = 30;
            }
            case Normal -> {
                hp = 25;
                velocity = 4;
                damage = 15;
                visionRange = 40;
            }
        }

        return new TenebrisSpikedScout(pos, hp, velocity, damage, visionRange);
    }

    private static BreakableWall createBreakableWall(Vector2D pos, Difficulty difficulty) {
        int hp = 35;
        switch (difficulty) {
            case Easy -> hp = 15;
            case Normal -> hp = 30;
        }

        return new BreakableWall(pos, hp);
    }
}

