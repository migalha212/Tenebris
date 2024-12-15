package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.entity.monster.*;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.BreakableWall;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.SandBag;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Spike;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Wall;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArenaBuilder {
    private final Arena arena;

    public ArenaBuilder() throws IOException {
        this.arena = new Arena();
    }

    public ArenaBuilder addDylan(Vector2D position, int size, int speed) throws IOException {
        arena.addElement(new Dylan(position, size, speed));
        return this;
    }

    public ArenaBuilder addWall(Vector2D position) {
        arena.addElement(new Wall(position));
        return this;
    }

    public ArenaBuilder addBreakableWall(Vector2D position) {
        arena.addElement(new BreakableWall(position, 20));
        return this;
    }

    public ArenaBuilder addSandBag(Vector2D position) {
        arena.addElement(new SandBag(position));
        return this;
    }

    public ArenaBuilder addSpike(Vector2D position) {
        arena.addElement(new Spike(position));
        return this;
    }

    public ArenaBuilder addMonster(Monster monster) {
        arena.addElement(monster);
        return this;
    }

    public ArenaBuilder loadFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseLine(line.trim());
            }
        }
        return this;
    }

    private void parseLine(String line) throws IOException {
        if (line.isEmpty() || line.startsWith("#")) return;
        Vector2D pos = parsePosition(line);

        switch (line.split("\\(")[0]) {
            case "Dylan" -> addDylan(pos, 10, 2);
            case "Wall" -> addWall(pos);
            case "SandBag" -> addSandBag(pos);
            case "Spike" -> addSpike(pos);
            case "Breakable" -> addBreakableWall(pos);
            case "T-W" -> addMonster(new TenebrisWarden(pos));
            case "T-SS" -> addMonster(new TenebrisSpikedScout(pos));
            case "T-Heavy" -> addMonster(new TenebrisHeavy(pos));
            case "T-Harbinger" -> addMonster(new TenebrisHarbinger(pos));
            case "T-P" -> addMonster(new TenebrisPeon(pos));
        }
    }

    private Vector2D parsePosition(String line) {
        String coords = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
        String[] parts = coords.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        return new Vector2D(x, y);
    }

    public Arena build() {
        return this.arena;
    }
}

