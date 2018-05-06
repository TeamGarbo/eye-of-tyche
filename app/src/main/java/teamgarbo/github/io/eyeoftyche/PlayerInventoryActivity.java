package teamgarbo.github.io.eyeoftyche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;

public class PlayerInventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_inventory);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        GridAdapter gridAdapter = new GridAdapter(Engine.getInstance().getPlayer().getInventory(), 4);
        final GridView grid = (GridView) findViewById(R.id.inventoryHolder);
        grid.setAdapter(gridAdapter);

    }
}
