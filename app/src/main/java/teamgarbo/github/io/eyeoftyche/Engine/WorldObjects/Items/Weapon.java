package teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items;

/**
 * Created by hercu on 06-May-18.
 */

public class Weapon extends Equipable {

    int dex, str;

    public Weapon(int cost, String name, int dex, int str) {
        super(cost, name);
        this.dex = dex;
        this.str = str;
    }
}
