package com.ldts.t14g01.Tenebris.model;

import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.model.arena.GameElement;
import com.ldts.t14g01.Tenebris.model.arena.entity.Dylan;
import com.ldts.t14g01.Tenebris.model.arena.staticelement.Spike;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

public class ArenaTest {
    Arena arena;

    @BeforeEach
    void setUp() throws IOException {
        arena = new Arena();
    }


    @Test
    void checkCollisionsTest() {

        GameElement gameElement1 = Mockito.mock(GameElement.class);
        GameElement gameElement2 = Mockito.mock(GameElement.class);
        GameElement gameElement3 = Mockito.mock(GameElement.class);

        Mockito.when(gameElement1.getPosition()).thenReturn(new Vector2D(1, 1));
        Mockito.when(gameElement2.getPosition()).thenReturn(new Vector2D(1, 1));
        Mockito.when(gameElement3.getPosition()).thenReturn(new Vector2D(20, 20));

        Mockito.when(gameElement1.getSize()).thenReturn(1);
        Mockito.when(gameElement2.getSize()).thenReturn(1);
        Mockito.when(gameElement3.getSize()).thenReturn(1);

        arena.addElement(gameElement1);
        arena.addElement(gameElement2);
        arena.addElement(gameElement3);
        arena.checkCollisions();

        Mockito.verify(gameElement1, Mockito.times(1)).interact(gameElement2);
        Mockito.verify(gameElement2, Mockito.times(1)).interact(gameElement1);
        Mockito.verify(gameElement3, Mockito.times(0)).interact(gameElement2);

    }
}
