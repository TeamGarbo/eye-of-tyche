package teamgarbo.github.io.eyeoftyche;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.Globals;

public class MainMenuActivity extends AppCompatActivity {

    Engine engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        engine = Engine.getInstance();
    }

    public void start(View view){
        if(Globals.STARTED){
            Intent myIntent = new Intent(MainMenuActivity.this, GameActivity.class);
            startActivity(myIntent);
        }
        else{
            Intent myIntent = new Intent(MainMenuActivity.this, CreatePlayerActivity.class);
            startActivity(myIntent);
        }
    }

    public void settings(View view){
        Intent myIntent = new Intent(MainMenuActivity.this, SettingsActivity.class);
        startActivity(myIntent);
    }

    public void exit(View view){
        finish();
        System.exit(0);
    }

    public void help(View view){
        Intent myIntent = new Intent(MainMenuActivity.this, HelpActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

}
