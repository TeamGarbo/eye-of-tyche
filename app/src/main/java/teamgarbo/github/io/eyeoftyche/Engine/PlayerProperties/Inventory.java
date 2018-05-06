package teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties;

import java.util.HashMap;

import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;

/**
 * Created by hercu on 05-May-18.
 */

public class Inventory {
    HashMap<Item, Integer> inventory = new HashMap<>();

    public void addItem(Item item)
    {
        inventory.put(item, inventory.get(item) + 1);
    }
}
