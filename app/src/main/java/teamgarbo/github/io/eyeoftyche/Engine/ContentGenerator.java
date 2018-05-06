package teamgarbo.github.io.eyeoftyche.Engine;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties.Player;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Armour;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Consumable;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Weapon;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Mob;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Spell;
import teamgarbo.github.io.eyeoftyche.R;

/**
 * Created by hercu on 05-May-18.
 */

public class ContentGenerator {

    static String TAG = "-SEED";

    static public String reseed(String seed, int i)
    {
        return String.valueOf((Math.abs(Integer.parseInt(seed)) * i) % Integer.MAX_VALUE - i + 1000 / i * i);
    }

    public static int getInteger(String seed, int offset, int max)
    {
        Log.e(TAG, seed+"getInteger");
        return seed.charAt(offset) % max;
    }

    static private boolean getBoolean(String seed, int offset)
    {
        Log.e(TAG, seed+"getBoolean");
        return seed.charAt(offset)%2 == 0;
    }

    static public Room generateRoom(String seed)  {
        Log.e(TAG, seed+"generateRoom");
        boolean outside = getBoolean(seed, 1);
        int chests = getInteger(seed,1, 3);
        int mobs = getInteger(seed,2, 2);
        int npcs = getInteger(seed,3, 1);
        int rooms = getInteger(seed,4, 3);

        return new Room(generateRoomName(seed), chests, mobs, npcs, rooms+1, outside, seed);
    }

    static public Item generateItem(String seed)
    {
        Log.e(TAG, seed+"generateItem");
        int itemNumber = getInteger(seed, 0, 3);
        switch (itemNumber) {
            case Globals.ITEM_ARMOUR:
                return new Armour(getInteger(seed, 1, 10), getArmourName(seed), getInteger(seed, 6, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 1, Engine.getInstance().getWorld().getRoomCount()));
            case Globals.ITEM_CONSUMABLE:
                return new Consumable(getInteger(seed, 1, 10), getConsumableName(seed), getInteger(seed, 2, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 3, Engine.getInstance().getWorld().getRoomCount()));
            case Globals.ITEM_WEAPON:
                return new Weapon(getInteger(seed, 1, 10), getWeaponName(seed), getInteger(seed, 4, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 5, Engine.getInstance().getWorld().getRoomCount()));
        }
        return null;
    }

    static public void regenerateItem(String seed, Item item)
    {
        if(item instanceof Armour)
        {
            ((Armour) item).setArmour(new Armour(getInteger(seed, 1, 10), item.getName(), getInteger(seed, 6, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 1, Engine.getInstance().getWorld().getRoomCount())));
        }
        if(item instanceof Consumable)
        {
            ((Consumable) item).setConsumable(new Consumable(getInteger(seed, 1, 10), item.getName(), getInteger(seed, 2, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 3, Engine.getInstance().getWorld().getRoomCount())));
        }
        if(item instanceof Weapon)
        {
            ((Weapon) item).setWeapon(new Weapon(getInteger(seed, 1, 10), item.getName(), getInteger(seed, 4, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed, 5, Engine.getInstance().getWorld().getRoomCount())));
        }
    }

    static public Mob generateMob(String seed)
    {
        Log.e(TAG, seed+"generateMob");
        int health = 1+ getInteger(seed,1, Engine.getInstance().getWorld().getRoomCount());
        int mana = 1+ getInteger(seed,2, Engine.getInstance().getWorld().getRoomCount());
        int money = 1+ getInteger(seed,3, Engine.getInstance().getWorld().getRoomCount() * 2);
        int dex = 1+ getInteger(seed,4, Engine.getInstance().getWorld().getRoomCount());
        int str = 1+ getInteger(seed,5, Engine.getInstance().getWorld().getRoomCount());
        int xpDrop = 1+ getInteger(seed,6, Engine.getInstance().getWorld().getRoomCount());

        return new Mob(health, mana, money, dex, str, xpDrop);
    }

    static public Player generatePlayer(String seed)
    {
        Log.e(TAG, seed+"generatePlayer");
        int health = 1+ getInteger(seed,1, 20);
        int mana = 1+ getInteger(seed,2, 20);
        int money = 1+getInteger(seed,3, 20);
        int dex = 1+ getInteger(seed,4, 5);
        int str = 1+ getInteger(seed,5, 5);

        return new Player(health, mana, money, dex, str);
    }

    public static String generateRoomName(String seed)  {
        int number = getInteger(seed,2,Globals.rooms.length-1);
        return getAdjective(seed) + " " + Globals.rooms[number];
    }

    public static String getAdjective(String seed){
        int number = getInteger(seed,6,Globals.adjectives.length-1);
        return Globals.adjectives[number];
    }

    public static String getArmourName(String seed){
        int number = getInteger(seed,3,Globals.armour.length-1);
        return getAdjective(seed) + " " + Globals.armour[number];
    }

    public static String getWeaponName(String seed){
        int number = getInteger(seed,1,Globals.weapons.length-1);
        return getAdjective(seed) + " " + Globals.weapons[number];
    }

    public static String getConsumableName(String seed){
        int number = getInteger(seed,5,Globals.con.length-1);
        return getAdjective(seed) + " " + Globals.con[number];
    }

    public static String getSpellName(String seed){
        int number = getInteger(seed,4,Globals.attacks.length-1);
        return getElementType(seed) + " " + Globals.attacks[number];
    }

    public static String getElementType(String seed){
        int number = getInteger(seed,2,Globals.elements.length-1);
        return getAdjective(seed) + " " + Globals.elements[number];
    }



    static public Spell generateSpell(String seed)
    {
        Log.e(TAG, seed+"generateSpell");
        int health = getInteger(seed,1, Engine.getInstance().getWorld().getRoomCount());
        int mana = getInteger(seed,2, Engine.getInstance().getWorld().getRoomCount());

        return new Spell(health, mana, getSpellName(seed), seed);
    }

    static public void regenerateItem(String seed, Spell spell)
    {
        spell.setSpell(new Spell(getInteger(seed,1, Engine.getInstance().getWorld().getRoomCount()), getInteger(seed,2, Engine.getInstance().getWorld().getRoomCount()), spell.getName(), seed));
    }
}
