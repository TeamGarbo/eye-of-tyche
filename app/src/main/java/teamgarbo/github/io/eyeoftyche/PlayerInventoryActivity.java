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
import android.widget.TextView;

import org.w3c.dom.Text;

import teamgarbo.github.io.eyeoftyche.Engine.ContentGenerator;
import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties.Inventory;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Armour;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Consumable;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Weapon;

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

    public void itemInfo(View v)
    {
        final Item item = inventoryAdapter.getItem(inventoryAdapter.mSelectedItem);

        // new item dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

            }
        });

        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Engine.getInstance().getPlayer().removeItem(item);
            }
        });

        TextView textView = view.findViewById(R.id.text_name);
        textView.setText(inventoryAdapter.getItem(inventoryAdapter.mSelectedItem).getName());
        textView = view.findViewById(R.id.text_cost);
        textView.setText(inventoryAdapter.getItem(inventoryAdapter.mSelectedItem).getCost() + "");
        if(item instanceof Weapon) {
            textView = view.findViewById(R.id.text_first_name);
            textView.setText("Dexterity");
            textView = view.findViewById(R.id.text_second_name);
            textView.setText("Strnegth");
            Weapon weapon = (Weapon)item;
            textView = view.findViewById(R.id.text_first);
            textView.setText(weapon.getDex()+"");
            textView = view.findViewById(R.id.text_second);
            textView.setText(weapon.getStr()+"");
        }
        if(item instanceof Armour) {
            textView = view.findViewById(R.id.text_first_name);
            textView.setText("Health");
            textView = view.findViewById(R.id.text_second_name);
            textView.setText("Mana");
            Armour armour = (Armour)item;
            textView = view.findViewById(R.id.text_first);
            textView.setText(armour.getHealth()+"");
            textView = view.findViewById(R.id.text_second);
            textView.setText(armour.getMana()+"");
        }
        if(item instanceof Consumable) {
            textView = view.findViewById(R.id.text_first_name);
            textView.setText("Health");
            textView = view.findViewById(R.id.text_second_name);
            textView.setText("Mana");
            Consumable consumable = (Consumable)item;
            textView = view.findViewById(R.id.text_first);
            textView.setText(consumable.getHealth()+"");
            textView = view.findViewById(R.id.text_second);
            textView.setText(consumable.getMana()+"");
        }
    }

    public void reforge(Item item)
    {
        ContentGenerator.regenerateItem(Engine.getInstance().getSeed(), item);
        itemInfo(null);
    }

    public void reforgeButton(View view)
    {
        // new yesno dialog
        final AlertDialog.Builder yesnoBuilder = new AlertDialog.Builder(this);
        yesnoBuilder.show();
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
        yesnoBuilder.setMessage("Are you sure you want to reroll stats?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener);




    }

}
