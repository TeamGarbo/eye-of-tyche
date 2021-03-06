package teamgarbo.github.io.eyeoftyche;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.init();
    }

    public void init(){
        Engine.getInstance();
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

    public void startApp(View view)
    {
        vibrate(500);
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                initMainMenu();
            }
        });
    }

}
