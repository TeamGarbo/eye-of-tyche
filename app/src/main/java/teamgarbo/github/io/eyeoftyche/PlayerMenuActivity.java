package teamgarbo.github.io.eyeoftyche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayerMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_menu);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void showInventory(View view)
    {
        Intent myIntent = new Intent(PlayerMenuActivity.this, PlayerInventoryActivity.class);
        startActivity(myIntent);
    }

    public void showSpellBook(View view)
    {
        Intent myIntent = new Intent(PlayerMenuActivity.this, PlayerSpellBookActivity.class);
        startActivity(myIntent);
    }

    public void showStats(View view)
    {
        Intent myIntent = new Intent(PlayerMenuActivity.this, PlayerStatsActivity.class);
        startActivity(myIntent);
    }
}
