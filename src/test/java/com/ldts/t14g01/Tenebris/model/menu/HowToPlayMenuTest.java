package com.ldts.t14g01.Tenebris.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HowToPlayMenuTest {
    private HowToPlayMenu howToPlayMenu;

    @BeforeEach
    void setUp() {
        this.howToPlayMenu = new HowToPlayMenu();
    }

    @Test
    void MenuTest1() {
        Assertions.assertEquals(10, this.howToPlayMenu.getOptions().size());
    }

    @Test
    void GetTests() {
        Assertions.assertEquals(this.howToPlayMenu, this.howToPlayMenu.getView().getModel());
        Assertions.assertEquals(this.howToPlayMenu, this.howToPlayMenu.getController().getModel());
    }
}
