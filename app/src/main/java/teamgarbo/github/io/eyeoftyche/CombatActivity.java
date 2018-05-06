package teamgarbo.github.io.eyeoftyche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    public void updateMobText(){

        String output = "Mob Health: " + mob.getHealth() + "\n" +
                        "Mob Mana: " + mob.getMana() + "\n" +
                        "Mob Money: " + mob.getMoney() + "\n" +
                        "\n"+
                        "Your Health:" + engine.getPlayer();
        TextView mobText = (TextView) findViewById(R.id.mobText);
        mobText.setText(output);

    }
}
