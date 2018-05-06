package teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties;

/**
 * Created by hercu on 05-May-18.
 */

public class Player {

    int health, mana, money, dex, str, totalXp;

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


}
