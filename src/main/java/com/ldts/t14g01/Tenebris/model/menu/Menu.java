package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    List<String> options;
    int selectedOption;

    protected Menu () {
        this.options = new ArrayList<>();
        selectedOption = 0;
    }

    public void moveUp() {
        this.selectedOption += this.options.size() - 1;
        this.selectedOption %= this.options.size();
    }

    public void moveDown() {
        this.selectedOption++;
        this.selectedOption %= this.options.size();
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public List<String> getOptions() {
        return options;
    }

    public abstract View<Menu> getView();

    public abstract Controller<Menu> getController();
}
