package teamgarbo.github.io.eyeoftyche.Engine;

import android.util.Log;

import teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties.Player;
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
        return seed.charAt(offset)%2 == 0;
    }

    static public Room generateRoom(String seed)
    {
        Log.e(TAG, seed+"generateRoom");
        boolean outside = getBoolean(seed, 1);
        int chests = getInteger(seed,1, 3);
        int mobs = getInteger(seed,2, 2);
        int npcs = getInteger(seed,3, 0);
        int rooms = getInteger(seed,4, 3);

        return new Room("Room:"+seed, chests, mobs, npcs, rooms+1, outside, seed);
    }

    static public Item generateItem(String seed)
    {
        Log.e(TAG, seed+"generateItem");
        int itemNumber = getInteger(seed, 0, 3);
        switch (itemNumber) {
            case Globals.ITEM_ARMOUR:
                return new Armour(getInteger(seed, 1, 10), "Armour", getInteger(seed, 2, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 3, Engine.getInstance().getWorld().getRoomCount()));
            case Globals.ITEM_CONSUMABLE:
                return new Consumable(getInteger(seed, 1, 10), "Consumable", getInteger(seed, 2, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 3, Engine.getInstance().getWorld().getRoomCount()));
            case Globals.ITEM_WEAPON:
                return new Weapon(getInteger(seed, 1, 10), "Weapon", getInteger(seed, 2, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 3, Engine.getInstance().getWorld().getRoomCount()));
        }
        return null;
    }

    static public void regenerateItem(String seed, Item item)
    {
        if(item instanceof Armour)
        {
            ((Armour) item).setArmour(new Armour(getInteger(seed, 1, 10), "Armour", getInteger(seed, 2, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 3, Engine.getInstance().getWorld().getRoomCount())));
        }
        if(item instanceof Consumable)
        {
            ((Consumable) item).setConsumable(new Consumable(getInteger(seed, 1, 10), "Consumable", getInteger(seed, 2, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 3, Engine.getInstance().getWorld().getRoomCount())));
        }
        if(item instanceof Weapon)
        {
            ((Weapon) item).setWeapon(new Weapon(getInteger(seed, 1, 10), "Weapon", getInteger(seed, 2, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 3, Engine.getInstance().getWorld().getRoomCount())));
        }
    }

    static public Mob generateMob(String seed)
    {
        Log.e(TAG, seed+"generateMob");
        int health = getInteger(seed,1, Engine.getInstance().getWorld().getRoomCount());
        int mana = getInteger(seed,2, Engine.getInstance().getWorld().getRoomCount());
        int money = getInteger(seed,3, Engine.getInstance().getWorld().getRoomCount() * 2);
        int dex = getInteger(seed,4, Engine.getInstance().getWorld().getRoomCount());
        int str = getInteger(seed,5, Engine.getInstance().getWorld().getRoomCount());
        int xpDrop = getInteger(seed,6, Engine.getInstance().getWorld().getRoomCount());

        return new Mob(health, mana, money, dex, str, xpDrop);
    }

    static public Player generatePlayer(String seed)
    {
        Log.e(TAG, seed+"generatePlayer");
        int health = getInteger(seed,1, 10);
        int mana = getInteger(seed,2, 10);
        int money = getInteger(seed,3, 20);
        int dex = getInteger(seed,4, 10);
        int str = getInteger(seed,5, 10);

        return new Player(health, mana, money, dex, str);
    }

    static public Spell generateSpell(String seed)
    {
        Log.e(TAG, seed+"generateSpell");
        int health = getInteger(seed,1, Engine.getInstance().getWorld().getRoomCount());
        int mana = getInteger(seed,2, Engine.getInstance().getWorld().getRoomCount());

        return new Spell(health, mana, "Spell", seed);
    }

    static public void regenerateItem(String seed, Spell spell)
    {
        spell.setSpell(new Spell(getInteger(seed,1, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed,2, Engine.getInstance().getWorld().getRoomCount()), "Spell", seed));
    }
}
