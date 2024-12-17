package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.model.arena.Commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena.Commands.DeleteEffect;
import com.ldts.t14g01.Tenebris.model.arena.effects.Effect;

public class EffectController {
    private final Effect model;

    public EffectController(Effect model) {
        this.model = model;
    }

    public void update(CommandHandler commandHandler) {
        this.model.update();

        if (this.model.isOver()) commandHandler.handleCommand(new DeleteEffect(this.model));
    }
}
