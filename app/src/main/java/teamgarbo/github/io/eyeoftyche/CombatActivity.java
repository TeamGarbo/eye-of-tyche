package teamgarbo.github.io.eyeoftyche;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.ContentGenerator;
import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.Globals;
import teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties.Player;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Mob;

public class CombatActivity extends AppCompatActivity {

    Engine engine;
    Mob mob;

    BarcodeHandler barcodeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        engine = Engine.getInstance();
        mob = engine.getTopMob();

        barcodeHandler = new BarcodeHandler(this,1);

        updateMobText();
    }

    public void updateMobText(){
        String output = Globals.PLAYER_NAME + " vs. " + "MOB" + "\n"+
                        "\n"+
                        "Mob Health: " + mob.getHealth() + "\n" +
                        "Mob Mana: " + mob.getMana() + "\n" +
                        "Mob Strenght: " + mob.getStr()+ "\n"+
                        "Mob Dexterity: " + mob.getDex() + "\n"+
                        "Mob Money: " + mob.getMoney() + "\n" +
                        "\n"+
                        "Your Health: " + engine.getPlayer().getHealth() + "\n" +
                        "Your Mana: " + engine.getPlayer().getMana() + "\n" +
                        "Your Money: " + engine.getPlayer().getMoney() + "\n"+
                        "Your Strenght: " + engine.getPlayer().getStr()+ "\n"+
                        "Your Dexterity: " + engine.getPlayer().getDex() + "\n"+
                        "Your XP: " + engine.getPlayer().getTotalXp() + "\n" +
                        "\n"+
                        "Current Attack: " + engine.getPlayer().getCurrentSpell().getName();

        TextView mobText = (TextView) findViewById(R.id.mobText);
        mobText.setText(output);
    }

    public void attackMob(View view){
        //TODO attack mob
//        mob.setHealth(mob.getHealth() - engine.getPlayer().getDex());
//        if(mob.getHealth()<= 0 ){
//            engine.getPlayer().addXP(mob.getXpDrop());
//            finish();
//        }
//        else{
//            engine.getPlayer().setHealth(engine.getPlayer().getHealth() - mob.getDex());
//        }

        Player player = Engine.getInstance().getPlayer();

        if(player.getMana() >= player.getCurrentSpell().getMana()) {
            mob.setHealth(mob.getHealth() - player.getCurrentSpell().getHealth() * player.getDex() / mob.getDex());
            player.setMana(player.getMana() - player.getCurrentSpell().getMana());
        }
        else
        {
            // out of mana
            AlertDialog.Builder builder = new AlertDialog.Builder(CombatActivity.this, R.style.AlertDialogTheme);
            builder.setMessage("You are out of mana!")
                    .setTitle("Combat");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    barcodeHandler.showScanner();
                }
            });
            builder.show();
        }

        if(mob.getHealth()<= 0 ){
            player.addXP(mob.getXpDrop());
            player.setMoney(player.getMoney() + mob.getMoney());

            AlertDialog.Builder builder = new AlertDialog.Builder(CombatActivity.this, R.style.AlertDialogTheme);
            builder.setMessage("Mob killed...")
                    .setTitle("Combat Result");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    barcodeHandler.showScanner();
                }
            });
            builder.show();

            finish();
        }
        else{
            player.setHealth(player.getHealth() - mob.getDex() * mob.getDex() / player.getDex() );
            if(player.getHealth() <= 0)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(CombatActivity.this, R.style.AlertDialogTheme);
                builder.setMessage("Oh dear, you are dead!")
                        .setTitle("Game Over");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        barcodeHandler.showScanner();
                    }
                });
                builder.show();
            }
        }

        CombatActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                updateMobText();
            }
        });
    }

    public void openSpellBook(View view){
        Intent myIntent = new Intent(CombatActivity.this, PlayerSpellBookActivity.class);
        startActivity(myIntent);
    }

    public void runAway(View view){
        finish();
    }

    public void scanSpell(View view){
        //TODO spells
        barcodeHandler.showScanner();
    }

    public void addSpell(String barcode)
    {
        Engine.getInstance().getPlayer().addSpell(ContentGenerator.generateSpell(barcode));
        updateMobText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String barcode = barcodeHandler.getBarcode(requestCode, resultCode, data, 1);
        if(barcode != null)
        {
            addSpell(barcode);
        }
    }
}
