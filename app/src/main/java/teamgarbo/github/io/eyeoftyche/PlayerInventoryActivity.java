package teamgarbo.github.io.eyeoftyche;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import teamgarbo.github.io.eyeoftyche.Engine.ContentGenerator;
import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties.Inventory;
import teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties.Player;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Armour;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Consumable;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Weapon;

public class PlayerInventoryActivity extends AppCompatActivity {

    InventoryAdapter inventoryAdapter;

    BarcodeHandler barcodeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_inventory);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        inventoryAdapter = new InventoryAdapter(this,Engine.getInstance().getPlayer().getInventory());
        final ListView listView  = (ListView) findViewById(R.id.inventoryHolder);
        listView.setAdapter(inventoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inventoryAdapter.mSelectedItem = position;
                inventoryAdapter.setObjects(Engine.getInstance().getPlayer().getInventory());
                inventoryAdapter.notifyDataSetChanged();
        }
        });

        barcodeHandler = new BarcodeHandler(this, 1);
    }

    public void itemInfo(View v)
    {
        final Item item = inventoryAdapter.getItem(inventoryAdapter.mSelectedItem);

        // new item dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        builder.setTitle("Item info:");

        final View view = getLayoutInflater().inflate(R.layout.layout_item_info, null);
        builder.setView(view);

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Engine.getInstance().getPlayer().removeItem(item);
                inventoryAdapter.setObjects(Engine.getInstance().getPlayer().getInventory());
                inventoryAdapter.notifyDataSetChanged();
            }
        });
        builder.show();

        if(inventoryAdapter.getItem(inventoryAdapter.mSelectedItem) != null) {
            TextView textView = view.findViewById(R.id.text_name);
            textView.setText(inventoryAdapter.getItem(inventoryAdapter.mSelectedItem).getName());
            textView = view.findViewById(R.id.text_cost);
            textView.setText(inventoryAdapter.getItem(inventoryAdapter.mSelectedItem).getCost() + "");
            if (item instanceof Weapon) {
                textView = view.findViewById(R.id.text_first_name);
                textView.setText("Dexterity");
                textView = view.findViewById(R.id.text_second_name);
                textView.setText("Strnegth");
                Weapon weapon = (Weapon) item;
                textView = view.findViewById(R.id.text_first);
                textView.setText(weapon.getDex() + "");
                textView = view.findViewById(R.id.text_second);
                textView.setText(weapon.getStr() + "");
            }
            if (item instanceof Armour) {
                textView = view.findViewById(R.id.text_first_name);
                textView.setText("Health");
                textView = view.findViewById(R.id.text_second_name);
                textView.setText("Mana");
                Armour armour = (Armour) item;
                textView = view.findViewById(R.id.text_first);
                textView.setText(armour.getHealth() + "");
                textView = view.findViewById(R.id.text_second);
                textView.setText(armour.getMana() + "");
            }
            if (item instanceof Consumable) {
                textView = view.findViewById(R.id.text_first_name);
                textView.setText("Health");
                textView = view.findViewById(R.id.text_second_name);
                textView.setText("Mana");
                Consumable consumable = (Consumable) item;
                textView = view.findViewById(R.id.text_first);
                textView.setText(consumable.getHealth() + "");
                textView = view.findViewById(R.id.text_second);
                textView.setText(consumable.getMana() + "");
            }
        }
    }

    public void reforge(Item item, String barcode)
    {
        Engine.getInstance().getPlayer().setReforgeCharge(false);
        ContentGenerator.regenerateItem(barcode, item);
        itemInfo(null);
    }

    public void reforgeButton(View view)
    {
        if(inventoryAdapter.getItem(inventoryAdapter.mSelectedItem) != null)
        if(Engine.getInstance().getPlayer().isReforgeCharge())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayerInventoryActivity.this, R.style.AlertDialogTheme);
            builder.setMessage("Are you sure you want to re-roll stats?")
                .setTitle("Eye of Tyche");
            builder.setPositiveButton("Re-roll", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                barcodeHandler.showScanner();
            }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
            });
            builder.show();

        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayerInventoryActivity.this, R.style.AlertDialogTheme);
            builder.setMessage("No charge left for reforge.")
                    .setTitle("Eye of Tyche");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    barcodeHandler.showScanner();
                }
            });
            builder.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String barcode = barcodeHandler.getBarcode(requestCode,resultCode,data,1);
        if(barcode != null)
        {
            reforge(inventoryAdapter.getItem(inventoryAdapter.mSelectedItem), barcode);
        }
    }

    public void useItem(Item item)
    {
        Player player = Engine.getInstance().getPlayer();

        if(item instanceof Consumable)
        {
            Consumable consumable = (Consumable) item;
            player.setHealth(player.getHealth() + consumable.getHealth());
            if(player.getHealth() > player.getMaxHealth())
                player.setHealth(player.getMaxHealth());
            player.setMana(player.getMana() + consumable.getMana());
            if(player.getMana() > player.getMaxMana())
                player.setMana(player.getMaxMana());


        }
        if(item instanceof Weapon)
        {
            Weapon weapon = (Weapon) item;
            player.setDex(player.getDex() + weapon.getDex());
            player.setStr(player.getStr() + weapon.getStr());
        }

        if(item instanceof Armour)
        {
            Armour armour = (Armour) item;
            player.setMaxHealth(player.getMaxHealth() + armour.getHealth());
            player.setMaxMana(player.getMaxMana() + armour.getMana());
        }

        Engine.getInstance().getPlayer().removeItem(item);
        inventoryAdapter.setObjects(Engine.getInstance().getPlayer().getInventory());
        inventoryAdapter.notifyDataSetChanged();

    }

    public void useItem(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(PlayerInventoryActivity.this, R.style.AlertDialogTheme);
        builder.setMessage("Are you sure you want to use this item stats?")
                .setTitle("Use Item");
        builder.setPositiveButton("Use", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            useItem(inventoryAdapter.getItem(inventoryAdapter.mSelectedItem));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.show();
    }
}
