package teamgarbo.github.io.eyeoftyche.Engine;

import java.util.ArrayList;

/**
 * Created by Shan on 05/05/2018.
 */

public class Engine {

    //Variables
    ArrayList<String> pastCodes;
    Globals globals;

    private static Engine instance;

    public static Engine getInstance() {
        if(instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    public Engine(){
        pastCodes = new ArrayList<String>();
        globals = new Globals();
    }

    public Globals getGlobals(){return globals;}

    public ArrayList<String> getPastCodes(){
        return pastCodes;
    }
}
