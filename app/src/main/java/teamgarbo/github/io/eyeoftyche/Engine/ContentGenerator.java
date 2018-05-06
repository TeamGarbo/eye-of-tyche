package teamgarbo.github.io.eyeoftyche.Engine;

import android.util.Log;

import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Armour;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Consumable;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Weapon;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Mob;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Spell;

/**
 * Created by hercu on 05-May-18.
 */

public class ContentGenerator {

    static String TAG = "-SEED";

    static public String reseed(String seed, int i)
    {
        return String.valueOf((Math.abs(Integer.parseInt(seed)) * i) % Integer.MAX_VALUE);
    }

    static private int getInteger(String seed, int offset, int max)
    {
        Log.e(TAG, seed+"getInteger");
        return seed.charAt(offset) % max;
    }

    static private boolean getBoolean(String seed, int offset)
    {
        Log.e(TAG, seed+"getBoolean");
        return seed.charAt(offset) >= 128;
    }

    static public Room generateRoom(String seed)
    {
        Log.e(TAG, seed+"generateRoom");
        boolean outside = getBoolean(seed, 1);
        int chests = getInteger(seed,1, 10);
        int mobs = getInteger(seed,2, 5);
        int npcs = getInteger(seed,3, 2);
        int rooms = getInteger(seed,4, 3);

        return new Room("Room:"+seed, chests, mobs, npcs, rooms+1, outside, seed);
    }

    static public Item generateItem(String seed)
    {
        Log.e(TAG, seed+"generateItem");
        int itemNumber = getInteger(seed, 9, 3);
        switch (itemNumber) {
            case Globals.ITEM_ARMOUR:
                return new Armour(getInteger(seed, 1, 10), "Armour", getInteger(seed, 2, 10), getInteger(seed, 3, 10));
            case Globals.ITEM_CONSUMABLE:
                return new Consumable(getInteger(seed, 1, 10), "Consumable", getInteger(seed, 2, 10), getInteger(seed, 3, 10));
            case Globals.ITEM_WEAPON:
                return new Weapon(getInteger(seed, 1, 10), "Weapon", getInteger(seed, 2, 10), getInteger(seed, 3, 10));
        }
        return null;
    }

    static public Mob generateMob(String seed)
    {
        Log.e(TAG, seed+"generateMob");
        int health = getInteger(seed,1, 10);
        int mana = getInteger(seed,2, 10);
        int money = getInteger(seed,3, 10);
        int dex = getInteger(seed,4, 10);
        int str = getInteger(seed,5, 10);
        int xpDrop = getInteger(seed,6, 10);

        return new Mob(health, mana, money, dex, str, xpDrop);
    }

    static public Spell generateSpell(String seed)
    {
        Log.e(TAG, seed+"generateSpell");
        int health = getInteger(seed,1, 10);
        int mana = getInteger(seed,2, 10);

        return new Spell(health, mana, "Spell");
    }

}
