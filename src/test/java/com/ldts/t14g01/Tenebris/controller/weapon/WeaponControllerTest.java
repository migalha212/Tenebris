package com.ldts.t14g01.Tenebris.controller.weapon;

import com.ldts.t14g01.Tenebris.controller.arena.weapon.WeaponController;
import com.ldts.t14g01.Tenebris.model.arena._commands.CommandHandler;
import com.ldts.t14g01.Tenebris.model.arena.weapons.Weapon;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class WeaponControllerTest {
    private Weapon model;
    private WeaponController controller;
    private SoundManager soundManager;

    @BeforeEach
    void setUp() {
        this.model = Mockito.mock(Weapon.class);
        this.soundManager = Mockito.mock(SoundManager.class);
        this.controller = new WeaponController(model) {
            @Override
            protected void playReloadSound() {
                SoundManager.getInstance().playSFX(SoundManager.SFX.PISTOL_RELOAD);
            }

            @Override
            public void shoot(CommandHandler commandHandler, Vector2D bulletPosition, Vector2D.Direction direction) {
            }
        };
    }

    @Test
    void reloadTest() {
        try (MockedStatic<SoundManager> soundManagerMockedStatic = Mockito.mockStatic(SoundManager.class)) {
            soundManagerMockedStatic.when(SoundManager::getInstance).thenReturn(this.soundManager);
            this.controller.reload();
            Mockito.verify(this.model, Mockito.times(1)).startReload();
            Mockito.verify(this.soundManager, Mockito.times(1)).playSFX(SoundManager.SFX.PISTOL_RELOAD);
        }
    }

    @Test
    void updateTest() {
        Mockito.when(this.model.isLoaded()).thenReturn(false);
        Mockito.when(this.model.isReloading()).thenReturn(false,true);
        this.controller.update();
        Mockito.verify(this.model, Mockito.times(1)).startReload();
        Mockito.verify(this.model, Mockito.times(1)).reload();
        Mockito.verify(this.model, Mockito.times(1)).tickWeaponTimer();
    }

    @Test
    void updateReloadTest() {
        Mockito.when(this.model.isLoaded()).thenReturn(false);
        Mockito.when(this.model.isReloading()).thenReturn(true);
        this.controller.update();
        Mockito.verify(this.model,Mockito.never()).startReload();
        Mockito.verify(this.model, Mockito.times(1)).reload();
        Mockito.verify(this.model,Mockito.times(1)).tickWeaponTimer();
    }

    @Test
    void updateNoReloadTest() {
        Mockito.when(this.model.isLoaded()).thenReturn(true);
        Mockito.when(this.model.isReloading()).thenReturn(false);
        this.controller.update();
        Mockito.verify(this.model,Mockito.times(1)).tickWeaponTimer();
        Mockito.verify(this.model,Mockito.never()).reload();
        Mockito.verify(this.model,Mockito.never()).startReload();
    }
}
