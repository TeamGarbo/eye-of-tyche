package teamgarbo.github.io.eyeoftyche.Engine;

import java.util.ArrayList;

/**
 * Created by hercu on 05-May-18.
 */

public class Room {

    String description;
    int chests;
    int mobs;
    int npcs;
    int rooms;
    boolean outside;


    String seed;

    public Room(String description, int chests, int mobs, int npcs, int rooms, boolean outside, String seed) {
        this.description = description;
        this.chests = chests;
        this.mobs = mobs;
        this.npcs = npcs;
        this.rooms = rooms;
        this.outside = outside;
        this.seed = seed;
    }

    public String getDescription() {
        return description;
    }

    public int getChests() {
        return chests;
    }

    public int getMobs() {
        return mobs;
    }

    public int getNpcs() {
        return npcs;
    }

    public int getRooms() {
        return rooms;
    }

    public boolean isOutside() {
        return outside;
    }

    public String getSeed() {
        return seed;
    }
}
