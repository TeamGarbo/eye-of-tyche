package teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties;

import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;

/**
 * Created by hercu on 05-May-18.
 */

public class Player {

    int health;
    int mana;
    int money;
    int dex;

    int str;
    int totalXp;

    public Player(int health, int mana, int money, int dex, int str) {
        this.health = health;
        this.mana = mana;
        this.money = money;
        this.dex = dex;
        this.str = str;
        this.totalXp = 0;
    }

    Inventory inventory = new Inventory();

    SpellBook spellBook = new SpellBook();

    public void addItem(Item item)
    {
        inventory.addItem(item);
    }

    public ArrayList<Item> getInventory()
    {
       return inventory.getItemsList();
    }

    public void removeItem(Item item)
    {
        inventory.removeItem(item);
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

    public int getTotalXp() {
        return totalXp;
    }

    public void addXP(int xp){
        this.totalXp+=xp;
    }

    public SpellBook getSpellBook() {
        return spellBook;
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

    public void setTotalXp(int totalXp) {
        this.totalXp = totalXp;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setSpellBook(SpellBook spellBook) {
        this.spellBook = spellBook;
    }
}
