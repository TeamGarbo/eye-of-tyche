package teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items;

/**
 * Created by hercu on 05-May-18.
 */

public class Item {

    int cost;
    String name;

    public Item(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }


    public void setItem(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }
}
