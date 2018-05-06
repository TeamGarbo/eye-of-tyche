package teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items;

/**
 * Created by hercu on 06-May-18.
 */

public class Consumable extends Item{

    int health, mana;

    public Consumable(int cost, String name, int health, int mana) {
        super(cost, name);
        this.health = health;
        this.mana = mana;
    }

}
