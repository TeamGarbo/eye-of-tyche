package teamgarbo.github.io.eyeoftyche.Engine;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties.Player;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;
import teamgarbo.github.io.eyeoftyche.ItemAdapter;
import teamgarbo.github.io.eyeoftyche.RoomAdapter;

/**
 * Created by Shan on 05/05/2018.
 */

public class Engine {

    //Variables
    ArrayList<String> pastCodes;

    Player player;

    World world;

    String seed = Globals.DEFULT_SEED;
    String playerSeed = Globals.DEFULT_SEED;

    private static Engine instance;

    public static Engine getInstance() {
        if(instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    public Engine(){
        pastCodes = new ArrayList<String>();
    }

    public void initWorld(){
        world = new World(seed);
    }

    public void setSeed(String seed){
        this.seed = seed;
    }

    public void initPlayerStats(String seed){
        this.playerSeed = seed;
    }

    public ArrayList<String> getPastCodes(){
        return pastCodes;
    }

    public RoomAdapter getRoomList(Context context)
    {
        return new RoomAdapter(context, world.getRooms());
    }

    public ItemAdapter getChest(Context context)
    {
        ArrayList<Item> items = world.getChest();
        if(items!=null)
            return new ItemAdapter(context, items);
        else
            return null;
    }

    public Player getPlayer() {
        return player;
    }

    public void progressRoom(Room room)
    {
        world.progressRoom(room);
    }
}
