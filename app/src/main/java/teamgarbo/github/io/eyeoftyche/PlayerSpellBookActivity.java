package teamgarbo.github.io.eyeoftyche;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import teamgarbo.github.io.eyeoftyche.Engine.ContentGenerator;
import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Armour;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Consumable;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Weapon;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Spell;

public class PlayerSpellBookActivity extends AppCompatActivity {

    SpellAdapter spellAdapter;
    private BarcodeHandler barcodeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_spell_book);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        barcodeHandler = new BarcodeHandler(this, 1);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        spellAdapter = new SpellAdapter(this, Engine.getInstance().getPlayer().getSpellBook().getSpellBook());
        final ListView listView  = (ListView) findViewById(R.id.spellbookHolder);
        listView.setAdapter(spellAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spellAdapter.mSelectedItem = position;
                spellAdapter.setObjects(Engine.getInstance().getPlayer().getSpellBook().getSpellBook());
                spellAdapter.notifyDataSetChanged();
            }
        });
    }

    public void itemInfo(View v)
    {
        final Spell spell = spellAdapter.getItem(spellAdapter.mSelectedItem);

        // new item dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        builder.setTitle("Spell info:");

        final View view = getLayoutInflater().inflate(R.layout.layout_item_info, null);
        builder.setView(view);

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

        if(spellAdapter.getItem(spellAdapter.mSelectedItem) != null) {
            TextView textView = view.findViewById(R.id.text_name);
            textView.setText(spellAdapter.getItem(spellAdapter.mSelectedItem).getName());
            textView = view.findViewById(R.id.text_cost);
            textView.setText(spellAdapter.getItem(spellAdapter.mSelectedItem).getMana() + "");
            textView = view.findViewById(R.id.text_first_name);
            textView.setText("Health");
            textView = view.findViewById(R.id.text_second_name);
            textView.setText("Barcode");
            textView = view.findViewById(R.id.text_first);
            textView.setText(spellAdapter.getItem(spellAdapter.mSelectedItem).getHealth() + "");
            textView = view.findViewById(R.id.text_second);
            textView.setText(spellAdapter.getItem(spellAdapter.mSelectedItem).getBarcode() + "");
        }
    }

    public void reforge(Spell spell, String barcode)
    {
        Engine.getInstance().getPlayer().setReforgeCharge(false);
        ContentGenerator.regenerateItem(barcode, spell);
        itemInfo(null);
    }

    public void reforgeSpellButton(View view)
    {
        if(spellAdapter.getItem(spellAdapter.mSelectedItem) != null)
        if(Engine.getInstance().getPlayer().isReforgeCharge())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayerSpellBookActivity.this, R.style.AlertDialogTheme);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayerSpellBookActivity.this, R.style.AlertDialogTheme);
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
            reforge(spellAdapter.getItem(spellAdapter.mSelectedItem), barcode);
        }
    }


}
