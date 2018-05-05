package teamgarbo.github.io.eyeoftyche;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        this.init();
    }

    public void init(){
        Engine.getInstance();
        vibrate(500);
        //Initialise global variables, get permissions, etc.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                initMainMenu();
            }
        });
    }

    public void initMainMenu(){
        Intent myIntent = new Intent(MainActivity.this, MainMenuActivity.class);
        startActivity(myIntent);
    }

    public void vibrate(int millis){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(millis);
    }


}
