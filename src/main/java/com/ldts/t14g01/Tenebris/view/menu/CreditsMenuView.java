package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.util.ArrayList;
import java.util.List;

public class CreditsMenuView extends View<Menu> {
    public CreditsMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) {
        // Get center position
        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        // X axis Offset
        int offsetX = 4;

        //Draw Title
        List<String> titleLines = new ArrayList<>();
        titleLines.add("Credits");
        titleLines.add("───────────");

        for (int i = 0; i < titleLines.size(); i++) {
            gui.drawText(titleLines.get(i), new Vector2D(offsetX, centerY - 8 + i), GUI.Colors.BRIGHT_YELLOW, GUI.Colors.BLACK);
        }

        // Draw UC Info
        List<String> ucInfoLines = new ArrayList<>();
        ucInfoLines.add("2024/2025");
        ucInfoLines.add("FEUP L.EIC014 - LDTS");

        for (int i = 0; i < titleLines.size(); i++) {
            gui.drawText(ucInfoLines.get(i), new Vector2D(gui.getWindowSize().x() - offsetX - ucInfoLines.get(i).length(), centerY - 8 + i), GUI.Colors.WHITE, GUI.Colors.BLACK);
        }

        // Draw Authors
        List<String> authorsLines = new ArrayList<>();
        authorsLines.add("T14G01:");
        authorsLines.add("Cláudio Meireles");
        authorsLines.add("Dinis Silva");
        authorsLines.add("Miguel Pereira");

        // Authors title
        gui.drawText(authorsLines.getFirst(), new Vector2D(offsetX + 3, centerY - 3), GUI.Colors.CYAN, GUI.Colors.BLACK);

        // Authors names
        for (int i = 1; i < authorsLines.size(); i++) {
            gui.drawText(authorsLines.get(i), new Vector2D(offsetX + 3, centerY - 2 + i), GUI.Colors.WHITE, GUI.Colors.BLACK);
        }

        // Draw Regent and Supervisor Names
        List<String> professorsLines = new ArrayList<>();
        professorsLines.add("Rui Maranhão");
        professorsLines.add("Juvenaldo Carvalho");

        for (int i = 0; i < professorsLines.size(); i++) {
            gui.drawText(professorsLines.get(i), new Vector2D(gui.getWindowSize().x() - offsetX - professorsLines.get(i).length(), centerY - 1 + i), GUI.Colors.WHITE, GUI.Colors.BLACK);
        }

        // Draw Back Button
        String backText = "Back";
        gui.drawText("Back", new Vector2D(centerX - backText.length() / 2, gui.getWindowSize().y() - 3), GUI.Colors.YELLOW, GUI.Colors.BLACK);
    }
}
