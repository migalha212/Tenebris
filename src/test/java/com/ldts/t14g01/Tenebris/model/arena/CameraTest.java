package com.ldts.t14g01.Tenebris.model.arena;

import com.ldts.t14g01.Tenebris.controller.arena.CameraController;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class CameraTest {
    @Test
    void Test1() {
        Vector2D position = new Vector2D(0,0);
        Camera camera = new Camera(position);

        Assertions.assertEquals(position, camera.getPosition());

        position = position.add(new Vector2D(1,1));
        camera.setPosition(position);
        Assertions.assertEquals(position, camera.getPosition());

        GameElement other = Mockito.mock(GameElement.class);
        Assertions.assertEquals(new ArrayList<>(), camera.interact(other));

        Assertions.assertInstanceOf(CameraController.class, camera.getController());
    }
}
