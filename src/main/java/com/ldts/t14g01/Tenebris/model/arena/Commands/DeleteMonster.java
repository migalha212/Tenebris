package com.ldts.t14g01.Tenebris.model.arena.Commands;

import com.ldts.t14g01.Tenebris.model.arena.entity.monster.Monster;

public record DeleteMonster(Monster monster) implements Command {
}
