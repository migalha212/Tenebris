package com.ldts.t14g01.Tenebris.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameOverMenuTest {
    private GameOverMenu gameOverMenu;

    @BeforeEach
    void setUp() {
        this.gameOverMenu = new GameOverMenu();
    }

    @Test
    void GetTests() {
        Assertions.assertEquals(this.gameOverMenu, this.gameOverMenu.getView().getModel());
        Assertions.assertEquals(this.gameOverMenu, this.gameOverMenu.getController().getModel());
    }
}
