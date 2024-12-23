package com.ldts.t14g01.Tenebris.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreditsMenuTest {
    private CreditsMenu creditsMenu;

    @BeforeEach
    void setUp() {
        this.creditsMenu = new CreditsMenu();
    }

    @Test
    void GetTests() {
        Assertions.assertEquals(this.creditsMenu, this.creditsMenu.getView().getModel());
        Assertions.assertEquals(this.creditsMenu, this.creditsMenu.getController().getModel());
    }
}
