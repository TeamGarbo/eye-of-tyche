package teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items;

/**
 * Created by hercu on 06-May-18.
 */

public class Equipable extends Item{

    public Equipable(int cost, String name) {
        super(cost, name);
    }

    public void setEquipable(int cost, String name)
    {
        setItem(cost, name);
    }
}
