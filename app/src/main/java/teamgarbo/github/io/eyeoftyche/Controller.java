package teamgarbo.github.io.eyeoftyche;

/**
 * Created by Shan on 05/05/2018.
 */

public class Controller {

    private static Controller instance;

    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Controller(){

    }
}
