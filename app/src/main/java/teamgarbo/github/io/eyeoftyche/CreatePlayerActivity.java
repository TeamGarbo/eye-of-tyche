package teamgarbo.github.io.eyeoftyche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.Globals;

public class CreatePlayerActivity extends AppCompatActivity {

    Engine engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        engine = Engine.getInstance();
    }

    public void setPlayer(View view){
        Globals.PLAYER_NAME = ((TextView) findViewById(R.id.editText)).getText().toString();
        Globals.STARTED = true;

        Intent myIntent = new Intent(CreatePlayerActivity.this, MainMenuActivity.class);
        startActivity(myIntent);
    }
}
