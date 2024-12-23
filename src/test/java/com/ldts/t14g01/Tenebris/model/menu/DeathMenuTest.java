package com.ldts.t14g01.Tenebris.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeathMenuTest {
    private DeathMenu deathMenu;

    @BeforeEach
    void setUp() {
        this.deathMenu = new DeathMenu();
    }

    @Test
    void MenuTest1() {
        Assertions.assertEquals(2, this.deathMenu.getOptions().size());
    }

    @Test
    void GetTests() {
        Assertions.assertEquals(this.deathMenu, this.deathMenu.getView().getModel());
        Assertions.assertEquals(this.deathMenu, this.deathMenu.getController().getModel());
    }
}
