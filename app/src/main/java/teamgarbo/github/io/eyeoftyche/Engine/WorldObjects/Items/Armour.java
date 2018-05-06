package teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items;

/**
 * Created by hercu on 06-May-18.
 */

public class Armour extends Equipable {

    int mana, health;

    public Armour(int cost, String name, int mana, int health) {
        super(cost, name);
        this.mana = mana;
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public int getHealth() {
        return health;
    }
}
