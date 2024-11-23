package com.ldts.t14g01.Tenebris.model.arena.arenaelement;

public class BreakableWall extends Wall{
    int hp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive(){
        return hp > 0;
    }
}
