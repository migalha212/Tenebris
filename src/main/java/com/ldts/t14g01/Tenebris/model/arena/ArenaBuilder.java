package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.*;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.BreakableWall;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.SandBag;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Spike;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Wall;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArenaBuilder {

    private ArenaBuilder() {
    }

    public static Arena build(SaveData saveData) throws IOException {
        return ArenaBuilder.loadFromFile("src/main/resources/levels/" + saveData.getLevel());
    }

    private static Arena loadFromFile(String filePath) throws IOException {
        // Create Arena
        Arena arena = new Arena();

        // Parse lines
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) ArenaBuilder.parseLine(arena, line.trim());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Trying to load a level that doesn't exist");
        }

        return arena;
    }

    private static void parseLine(Arena arena, String line) throws IOException {
        if (line.isEmpty() || line.startsWith("#")) return;
        Vector2D position = ArenaBuilder.parsePosition(line);

        switch (line.split("\\(")[0]) {
            case "Dylan" -> arena.addElement(new Dylan(position, 10, 2));
            case "Wall" -> arena.addElement(new Wall(position));
            case "SandBag" -> arena.addElement(new SandBag(position));
            case "Spike" -> arena.addElement(new Spike(position));
            case "Breakable" -> arena.addElement(new BreakableWall(position, 20));
            case "T-W" -> arena.addElement(new TenebrisWarden(position));
            case "T-SS" -> arena.addElement(new TenebrisSpikedScout(position));
            case "T-Heavy" -> arena.addElement(new TenebrisHeavy(position));
            case "T-Harbinger" -> arena.addElement(new TenebrisHarbinger(position));
            case "T-P" -> arena.addElement(new TenebrisPeon(position));
        }
    }

    private static Vector2D parsePosition(String line) {
        String coords = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
        String[] parts = coords.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        return new Vector2D(x, y);
    }
}

