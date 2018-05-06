package teamgarbo.github.io.eyeoftyche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Mob;

public class CombatActivity extends AppCompatActivity {

    Engine engine;
    Mob mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        engine = Engine.getInstance();
        mob = engine.getTopMob();

        updateMobText();
    }

    public void updateMobText(){
        String output = "Mob Health: " + mob.getHealth() + "\n" +
                        "Mob Mana: " + mob.getMana() + "\n" +
                        "Mob Money: " + mob.getMoney() + "\n" +
                        "\n"+
                        "Your Health: " + engine.getPlayer().getHealth() + "\n" +
                        "Your Mana: " + engine.getPlayer().getMana() + "\n" +
                        "Your Money: " + engine.getPlayer().getMoney() + "\n";

        TextView mobText = (TextView) findViewById(R.id.mobText);
        mobText.setText(output);
    }

    public void attackMob(View view){
        //TODO attack mob
    }

    public void openSpellBook(View view){
        Intent myIntent = new Intent(CombatActivity.this, PlayerSpellBookActivity.class);
        startActivity(myIntent);
    }

    public void runAway(View view){
        Intent myIntent = new Intent(CombatActivity.this, GameActivity.class);
        startActivity(myIntent);
    }

    public void scanSpell(View view){
        //TODO spells
    }
}
