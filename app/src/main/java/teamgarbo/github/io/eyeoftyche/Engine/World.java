package teamgarbo.github.io.eyeoftyche.Engine;

import java.io.IOException;
import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Mob;

/**
 * Created by hercu on 05-May-18.
 */

public class World {

    Room currentRoom;
    int roomCount = 10;
    int trades = 5;
    String seed;

    World(String seed)
    {
        this.seed = seed;
        currentRoom = new Room("Your house.", 10, 0,1,1,false, seed);
        currentRoom.setMobList(getMobs());
    }

    ArrayList<Room> getRooms()
    {
        ArrayList<Room> rooms = new ArrayList<>();
        for(int i = 2; i <= currentRoom.rooms+2; ++i)
            rooms.add(ContentGenerator.generateRoom(ContentGenerator.reseed(seed,i)));
        return rooms;
    }

    void progressRoom(Room room)
    {
        currentRoom = room;
        seed = room.getSeed();
        currentRoom.setMobList(getMobs());
        roomCount ++;
        trades = 5;
    }

    ArrayList<Item> getChest()
    {
        if(currentRoom.chests > 0) {
            ArrayList<Item> items = new ArrayList<>();
            items.add(ContentGenerator.generateItem(ContentGenerator.reseed(seed, currentRoom.chests)));
            currentRoom.chests--;
            return items;
        }
        return null;
    }

    ArrayList<Item> getTrade()
    {
        if(trades > 0) {
            ArrayList<Item> items = new ArrayList<>();
            for(int i = 1; i <= trades; ++i)
                items.add(ContentGenerator.generateItem(ContentGenerator.reseed(seed, trades / i % 1000)));
            trades--;
            return items;
        }
        return null;
    }

    ArrayList<Mob> getMobs()
    {
        ArrayList<Mob> mobs = new ArrayList<>();
        for(int i = 1; i <= currentRoom.mobs; ++i)
            mobs.add(ContentGenerator.generateMob(ContentGenerator.reseed(seed,i)));
        return mobs;
    }

    public int getRoomCount() {
        return roomCount * roomCount;
    }
}
