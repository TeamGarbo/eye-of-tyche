package teamgarbo.github.io.eyeoftyche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayerSpellBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_spell_book);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
