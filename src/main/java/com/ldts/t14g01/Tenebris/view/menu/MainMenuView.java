package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuView extends View<Menu> {

    public MainMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        // ToDo : Draw Background

        // Get center x and center y position
        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        // Align Options Left
        int leftX = 4;

        // Draw Title
        List<String> title = new ArrayList<>();
        title.add(" _______              _          _");
        title.add("|__   __|            | |        (_)");
        title.add("   | | ___ _ __   ___| |__  _ __ _ ___");
        title.add("   | |/ _ \\ '_ \\ / _ \\ '_ \\| '__| / __|");
        title.add("   | |  __/ | | |  __/ |_) | |  | \\__ \\");
        title.add("   |_|\\___|_| |_|\\___|_.__/|_|  |_|___/");
        for(int i = 0; i < title.size(); i++){
            gui.drawText(title.get(i),new Vector2D(centerX - 20,2 + i), GUI.Colors.WHITE, GUI.Colors.BLACK);
        }

        // Draw options (left-aligned)
        for (int i = 0; i < this.getModel().getOptions().size(); i++) {
            GUI.Colors foreGroundColor = GUI.Colors.WHITE;

            // Add markers for the selected option
            if (i == this.getModel().getSelectedOption()) foreGroundColor = GUI.Colors.YELLOW;

            // Draw option
            gui.drawText(this.getModel().getOptions().get(i).replace('_', ' '), new Vector2D(leftX, centerY + i), foreGroundColor, GUI.Colors.BLACK);
        }
    }
}
