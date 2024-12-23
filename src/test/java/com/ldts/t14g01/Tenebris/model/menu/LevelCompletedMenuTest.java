package com.ldts.t14g01.Tenebris.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LevelCompletedMenuTest {
    private LevelCompletedMenu levelCompletedMenu;

    @BeforeEach
    void setUp() {
        this.levelCompletedMenu = new LevelCompletedMenu();
    }

    @Test
    void GetTests() {
        Assertions.assertEquals(this.levelCompletedMenu, this.levelCompletedMenu.getView().getModel());
        Assertions.assertEquals(this.levelCompletedMenu, this.levelCompletedMenu.getController().getModel());
    }
}
