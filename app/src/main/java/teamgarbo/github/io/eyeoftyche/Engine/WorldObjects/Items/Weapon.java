package teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items;

/**
 * Created by hercu on 06-May-18.
 */

public class Weapon extends Equipable {

    int dex, str;

    public Weapon(int cost, String name, int dex, int str) {
        super(dex + str, name);
        this.dex = dex;
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public int getStr() {
        return str;
    }

    public void setWeapon(int cost, String name, int dex, int str)
    {
        setEquipable(cost, name);
        this.dex = dex;
        this.str = str;
    }

    public void setWeapon(Weapon weapon)
    {
        setEquipable(weapon.getCost(), weapon.getName());
        this.dex = weapon.getDex();
        this.str = weapon.getStr();
    }
}
