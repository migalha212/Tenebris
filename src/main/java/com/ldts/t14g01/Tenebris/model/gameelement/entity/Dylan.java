package com.ldts.t14g01.Tenebris.model.gameelement.entity;

public class Dylan extends Entity {
    private int points;
    private int deathCount;
    private int selectedWeapon;

    public Dylan(int x, int y, int maxHp, double maxEnergy) {
        super(x, y, maxHp, maxEnergy);
        this.points = 0;
        this.selectedWeapon = 0;
        this.deathCount = 0;
    }

    public int getPoints() {
        return points;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public int getSelectedWeapon() {
        return selectedWeapon;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public void setSelectedWeapon(int selectedWeapon) {
        this.selectedWeapon = selectedWeapon;
    }
}
