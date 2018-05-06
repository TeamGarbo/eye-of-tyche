package teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties;

import java.util.ArrayList;
import java.util.HashMap;

import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;

/**
 * Created by hercu on 05-May-18.
 */

public class Inventory {
    HashMap<Item, Integer> inventory = new HashMap<>();

    public void addItem(Item item)
    {
        inventory.put(item, inventory.get(item) != null ? inventory.get(item) + 1 : 1);
    }

    public ArrayList<Item> getItemsList()
    {
        return new ArrayList<Item>(inventory.keySet());
    }

    public void removeItem(Item item)
    {
        if(inventory.get(item) != null) {
            if (inventory.get(item) > 1)
                inventory.put(item, inventory.get(item) - 1);
            else
                inventory.remove(item);
        }
    }
}
