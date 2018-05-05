package teamgarbo.github.io.eyeoftyche;

/**
 * Created by Shan on 05/05/2018.
 */

public class Engine {

    private static Engine instance;

    public static Engine getInstance() {
        if(instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    public Engine(){

    }


}
