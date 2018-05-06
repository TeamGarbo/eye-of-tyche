package teamgarbo.github.io.eyeoftyche.Engine.WorldObjects;

/**
 * Created by hercu on 05-May-18.
 */

public class Mob {

    int health, mana, money, dex, str, xpDrop;

    public Mob(int health, int mana, int money, int dex, int str, int xpDrop) {
        this.health = health;
        this.mana = mana;
        this.money = health * mana;
        this.dex = dex;
        this.str = str;
        this.xpDrop = health * str + mana* dex;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getMoney() {
        return money;
    }

    public int getDex() {
        return dex;
    }

    public int getStr() {
        return str;
    }

    public int getXpDrop() {
        return xpDrop;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setXpDrop(int xpDrop) {
        this.xpDrop = xpDrop;
    }

}
