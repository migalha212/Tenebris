package com.ldts.t14g01.Tenebris.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NewGameMenuTest {
    private NewGameMenu newGameMenu;

    @BeforeEach
    void setUp() {
        this.newGameMenu = new NewGameMenu();
    }

    @Test
    void MenuTest1() {
        Assertions.assertEquals(4, newGameMenu.getOptions().size());
    }

    @Test
    void GetTests() {
        Assertions.assertNotEquals(null, newGameMenu.getView());
        Assertions.assertNotEquals(null, newGameMenu.getController());
    }
}
