package teamgarbo.github.io.eyeoftyche.Engine;

import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Mob;

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

    ArrayList<Mob> mobList;

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

    public void setMobs(int mobs){
        this.mobs = mobs;
    }

    public void setMobList(ArrayList<Mob> mobList){
        this.mobList = mobList;
    }

    public ArrayList<Mob> getMobList(){
        return mobList;
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
