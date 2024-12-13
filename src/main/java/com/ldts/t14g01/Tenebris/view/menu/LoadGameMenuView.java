package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.List;

public class LoadGameMenuView extends View<Menu> {
    public LoadGameMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {

        //used for drawing options and getting other info
        List<SaveData> saves = SaveData.getSaves();

        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        // X axis Offset
        int offsetX = 4;

        //draw title
        String title = "Load Game";
        gui.drawText(
                title,
                new Vector2D(4, 6),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );

        //show number of available saves
        int available = saves.size();
        gui.drawText(
                "Available save files:" + Integer.toString(available),
                new Vector2D(4, 9),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );

        //show which save is selected, and it's respective info
        String difficulty = saves.get(this.getModel().getSelectedOption()).getDifficulty().name();
        int level = saves.get(this.getModel().getSelectedOption()).getLevel();
        String text = "Selected Save: ";
        gui.drawText(
                text,
                new Vector2D(4, 10),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );
        gui.drawText(
                Integer.toString(this.getModel().getSelectedOption() + 1),
                new Vector2D(4 + text.length(), 10),
                GUI.Colors.YELLOW,
                GUI.Colors.BLACK
        );
        //draw the info for the save
        String saveInfo =
                "Dylan has ventured to level " + Integer.toString(level) + " on " + difficulty + " difficulty";
        gui.drawText(
                saveInfo,
                new Vector2D(centerX - saveInfo.length() / 2, 13),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );

        //draw confirmation
        String confirmation = "Load selected save?";
        gui.drawText(
                confirmation,
                new Vector2D(centerX - confirmation.length() / 2, 16),
                GUI.Colors.YELLOW,
                GUI.Colors.BLACK
        );
    }
}
