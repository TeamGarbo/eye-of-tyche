package teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties;

import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Spell;

/**
 * Created by hercu on 05-May-18.
 */

public class Player {

    boolean reforgeCharge = true;
    int reforgeCooldown = 5;

    int health, maxHealth;
    int mana, maxMana;
    int money;
    int dex;

    int str;
    int totalXp;

    Spell currentSpell;

    public Player(int health, int mana, int money, int dex, int str) {
        this.health = this.maxHealth = health;
        this.mana = this.maxMana = mana;
        this.money = money;
        this.dex = dex;
        this.str = str;
        this.totalXp = 0;
        Spell hit = new Spell(1,0,"Punch", "0");
        spellBook.addSpell(hit);
        currentSpell = hit;
    }

    public Spell getCurrentSpell() {
        return currentSpell;
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

    public void addSpell(Spell spell)
    {
        spellBook.addSpell(spell);
        currentSpell = spell;
    }

    public boolean isReforgeCharge() {
        return reforgeCharge;
    }

    public int getReforgeCooldown() {
        return reforgeCooldown;
    }

    public void setReforgeCharge(boolean reforgeCharge) {
        this.reforgeCharge = reforgeCharge;
    }

    public void setReforgeCooldown(int reforgeCooldown) {
        this.reforgeCooldown = reforgeCooldown;
    }

    public void setCurrentSpell(Spell currentSpell) {
        this.currentSpell = currentSpell;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }
}
