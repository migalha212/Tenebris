package com.ldts.t14g01.Tenebris.model.arena._commands;

import com.ldts.t14g01.Tenebris.model.arena.entities.monster.Monster;

public record DeleteMonster(Monster monster) implements Command {
}
