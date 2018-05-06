package teamgarbo.github.io.eyeoftyche;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import teamgarbo.github.io.eyeoftyche.Engine.ContentGenerator;
import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties.Inventory;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;

public class PlayerInventoryActivity extends AppCompatActivity {

    InventoryAdapter inventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_inventory);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        inventoryAdapter = new InventoryAdapter(this,Engine.getInstance().getPlayer().getInventory());
        final ListView listView  = (ListView) findViewById(R.id.inventoryHolder);
        listView.setAdapter(inventoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inventoryAdapter.mSelectedItem = position;
                inventoryAdapter.notifyDataSetChanged();
        }
        });
    }

    public void reforge(Item item)
    {
        ContentGenerator.regenerateItem(Engine.getInstance().getSeed(), item);
    }

    public void reforgeButton(View view)
    {
        // new yesno dialog
        final AlertDialog.Builder yesnoBuilder = new AlertDialog.Builder(this);

        // yes no on cancel dialog
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        reforge(inventoryAdapter.getItem(inventoryAdapter.mSelectedItem));
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        yesnoBuilder.setMessage("Do you want to discard current details?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener);

        yesnoBuilder.show();


    }

}
