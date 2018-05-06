package teamgarbo.github.io.eyeoftyche.Engine.WorldObjects;

/**
 * Created by hercu on 05-May-18.
 */

public class Spell {

    int health, mana;
    String name;
    String barcode;

    public Spell(int health, int mana, String name, String barcode) {
        this.health = health;
        this.mana = mana;
        this.name = name;
        this.barcode = barcode;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setSpell(Spell spell)
    {
        this.health = spell.getHealth();
        this.mana = spell.getMana();
        this.name = spell.getName();
        this.barcode = spell.getBarcode();
    }
}
