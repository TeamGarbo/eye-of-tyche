package teamgarbo.github.io.eyeoftyche;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.Globals;
import teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties.Player;

public class PlayerStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Player player = Engine.getInstance().getPlayer();

        String output = Globals.PLAYER_NAME + "\n"+

                "Your Health: " + player.getHealth() + "\n" +
                "Your Mana: " + player.getMana() + "\n" +
                "Your Money: " + player.getMoney() + "\n"+
                "Your Strenght: " + player.getStr()+ "\n"+
                "Your Dexterity: " + player.getDex() + "\n"+
                "Your XP: " + player.getTotalXp() + "\n" +
                "\n"+
                "Current Attack: " + player.getCurrentSpell().getName();

        TextView statsText = (TextView) findViewById(R.id.text_stats);
        statsText.setText(output);
    }
}
