package teamgarbo.github.io.eyeoftyche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        Controller.getInstance();

        //Initialise global variables, get permissions, etc.

        initMainMenu();
    }

    public void initMainMenu(){
        Intent myIntent = new Intent(MainActivity.this, MainMenuActivity.class);
        startActivity(myIntent);
    }
}
