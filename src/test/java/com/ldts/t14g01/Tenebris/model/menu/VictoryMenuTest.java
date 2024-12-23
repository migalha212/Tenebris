package com.ldts.t14g01.Tenebris.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VictoryMenuTest {
    private VictoryMenu victoryMenu;

    @BeforeEach
    void setUp() {
        this.victoryMenu = new VictoryMenu();
    }

    @Test
    void GetTests() {
        Assertions.assertEquals(this.victoryMenu, this.victoryMenu.getView().getModel());
        Assertions.assertEquals(this.victoryMenu, this.victoryMenu.getController().getModel());
    }
}
