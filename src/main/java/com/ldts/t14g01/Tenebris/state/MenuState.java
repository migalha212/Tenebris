package com.ldts.t14g01.Tenebris.state;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.sound.GlobalSoundManager;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.view.View;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
        GlobalSoundManager.getInstance().playMusic(SoundManager.Music.menuBackground);
    }

    @Override
    protected View<Menu> getView() {
        return this.getModel().getView();
    }

    @Override
    protected Controller<Menu> getController() {
        return this.getModel().getController();
    }

    @Override
    public GUI.Type getGUIType() {
        return GUI.Type.MENU;
    }
}
