package teamgarbo.github.io.eyeoftyche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void start(View view){
        Intent myIntent = new Intent(MainMenuActivity.this, GameActivity.class);
        startActivity(myIntent);
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
