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

    public int getMana() {
        return mana;
    }

    public int getHealth() {
        return health;
    }

    public void setConsumable(int cost, String name, int health, int mana)
    {
        setItem(cost, name);
        this.health = health;
        this.mana = mana;
    }

    public void setConsumable(Consumable consumable)
    {
        setItem(consumable.getCost(), consumable.getName());
        this.health = consumable.getHealth();
        this.mana = consumable.getMana();
    }
}
