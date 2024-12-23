package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LevelsMenuTest {
    private LevelsMenu levelsMenu;
    private SaveDataProvider saveDataProvider;

    @Test
    void MenuTest1() {
        this.saveDataProvider = Mockito.mock(SaveDataProvider.class);
        Mockito.when(this.saveDataProvider.getSaveData()).thenReturn(null);

        this.levelsMenu = new LevelsMenu(this.saveDataProvider);
        Assertions.assertNull(levelsMenu.getOptions());
    }

    @Property
    void MenuTest2(@ForAll @IntRange(min = 1, max = 1000) int numberOfLevels) {
        SaveData saveData = Mockito.mock(SaveData.class);
        Mockito.when(saveData.getLevel()).thenReturn(numberOfLevels);

        this.saveDataProvider = Mockito.mock(SaveDataProvider.class);
        Mockito.when(this.saveDataProvider.getSaveData()).thenReturn(saveData);

        this.levelsMenu = new LevelsMenu(this.saveDataProvider);
        Assertions.assertEquals(numberOfLevels, this.levelsMenu.getOptions().size());
    }

    @Test
    void GetTests() {
        this.saveDataProvider = Mockito.mock(SaveDataProvider.class);
        Mockito.when(this.saveDataProvider.getSaveData()).thenReturn(null);

        this.levelsMenu = new LevelsMenu(this.saveDataProvider);
        Assertions.assertEquals(this.levelsMenu, this.levelsMenu.getView().getModel());
        Assertions.assertEquals(this.levelsMenu, this.levelsMenu.getController().getModel());
    }
}
