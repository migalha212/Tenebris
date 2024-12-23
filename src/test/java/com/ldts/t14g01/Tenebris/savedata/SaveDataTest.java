package com.ldts.t14g01.Tenebris.savedata;

import com.ldts.t14g01.Tenebris.utils.Difficulty;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.EnumSet;

import static java.lang.Math.min;

public class SaveDataTest {
    private SaveData saveData;

    @Provide
    Arbitrary<Difficulty> difficultyValues() {
        return Arbitraries.of(EnumSet.allOf(Difficulty.class));
    }

    @Property
    void GetTests(@ForAll("difficultyValues") Difficulty difficulty, @ForAll @Positive int level, @ForAll boolean lastOpened) {
        this.saveData = new SaveData(difficulty, level, lastOpened);
        Assertions.assertEquals(difficulty, this.saveData.getDifficulty());
        Assertions.assertEquals(level, this.saveData.getLevel());
        Assertions.assertEquals(lastOpened, this.saveData.isLastOpened());
    }

    @Property
    void SetTests(@ForAll("difficultyValues") Difficulty difficulty, @ForAll @IntRange(min = 1, max = SaveData.MAX_LEVEL) int level, @ForAll boolean lastOpened) {
        this.saveData = new SaveData(difficulty, level, lastOpened);

        this.saveData.setLastOpened();
        Assertions.assertTrue(this.saveData.isLastOpened());

        this.saveData.removeLastOpened();
        Assertions.assertFalse(this.saveData.isLastOpened());

        try (MockedStatic<SaveDataManager> mockedSaveDataManager = Mockito.mockStatic(SaveDataManager.class)) {
            SaveDataManager mockSaveDataManager = Mockito.mock(SaveDataManager.class);
            mockedSaveDataManager.when(SaveDataManager::getInstance).thenReturn(mockSaveDataManager);

            this.saveData.increaseLevel();
            Assertions.assertEquals(min(level + 1, 6), this.saveData.getLevel());
            Mockito.verify(mockSaveDataManager, Mockito.times(1)).triggerUpdate();

            this.saveData.increaseLevel();
            Assertions.assertEquals(min(level + 2, 6), this.saveData.getLevel());
            Mockito.verify(mockSaveDataManager, Mockito.times(2)).triggerUpdate();
        }
    }

    @Property
    void EqualsTests(@ForAll("difficultyValues") Difficulty difficulty, @ForAll @IntRange(min = 1, max = SaveData.MAX_LEVEL) int level, @ForAll boolean lastOpened) {
        this.saveData = new SaveData(difficulty, level, lastOpened);
        SaveData saveData2 = new SaveData(difficulty, level, lastOpened);
        Assertions.assertEquals(this.saveData, saveData2);
        Assertions.assertEquals(this.saveData.hashCode(), saveData2.hashCode());
    }

    @Property
    void NotEqualsTests(
            @ForAll("difficultyValues") Difficulty difficulty, @ForAll @IntRange(min = 1, max = SaveData.MAX_LEVEL) int level, @ForAll boolean lastOpened,
            @ForAll("difficultyValues") Difficulty difficulty2, @ForAll @IntRange(min = 1, max = SaveData.MAX_LEVEL) int level2
    ) {
        this.saveData = new SaveData(difficulty, level, lastOpened);
        SaveData saveData2 = new SaveData(difficulty2, level2, lastOpened);

        if (difficulty == difficulty2 && level == level2) return;
        Assertions.assertNotEquals(this.saveData, saveData2);
        Assertions.assertNotEquals(this.saveData.hashCode(), saveData2.hashCode());

        Assertions.assertFalse(this.saveData.equals(null));
        Assertions.assertFalse(this.saveData.equals(new Object()));
    }
}
