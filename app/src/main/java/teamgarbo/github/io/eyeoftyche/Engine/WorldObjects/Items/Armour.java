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

    public void setArmour(int cost, String name, int health, int mana)
    {
        setEquipable(cost, name);
        this.health = health;
        this.mana = mana;
    }
    public void setArmour(Armour armour)
    {
        setEquipable(armour.getCost(), armour.getName());
        this.health = armour.getHealth();
        this.mana = armour.getMana();
    }
}
