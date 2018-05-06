package teamgarbo.github.io.eyeoftyche;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.Globals;

public class CreatePlayerActivity extends AppCompatActivity {

    boolean worldScanned = false;
    boolean statsScanned = false;
    Engine engine;
    BarcodeHandler barcodeHandlerWorld;
    BarcodeHandler barcodeHandlerStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        barcodeHandlerWorld = new BarcodeHandler(this);
        engine = Engine.getInstance();
    }

    public void setPlayer(View view){
        if(worldScanned && statsScanned){
            Globals.PLAYER_NAME = ((TextView) findViewById(R.id.editText)).getText().toString();
            Globals.STARTED = true;

            Intent myIntent = new Intent(CreatePlayerActivity.this, GameActivity.class);
            startActivity(myIntent);
        }
    }

    public void setWorldSeed(View view){
        barcodeHandlerWorld.showScanner();

        String barcode = barcodeHandlerWorld.getLastBarcode();
        engine.setSeed(barcode);
        engine.initWorld();
        System.out.println(barcode + " BARCODE E E AE A");
        FloatingActionButton button = ((FloatingActionButton) findViewById(R.id.scan_cp1));
        button.setBackgroundColor(getResources().getColor(R.color.green));
        button.setEnabled(false);
        worldScanned = true;
    }

    public void setStatsSeed(View view){
        barcodeHandler.showScanner();
        String barcode = barcodeHandler.getLastBarcode();
        engine.initPlayerStats(barcode);
        FloatingActionButton button = ((FloatingActionButton) findViewById(R.id.scan_cp2));
        button.setBackgroundColor(getResources().getColor(R.color.green));
        button.setEnabled(false);
        statsScanned = true;
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        barcodeHandlerWorld.getBarcode(requestCode, resultCode, data);
    }

}
