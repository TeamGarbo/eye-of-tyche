package teamgarbo.github.io.eyeoftyche;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.os.Vibrator;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        barcodeHandlerWorld = new BarcodeHandler(this, 1);
        barcodeHandlerStats = new BarcodeHandler(this, 2);
        engine = Engine.getInstance();
    }

    public void setPlayer(View view){
        if(worldScanned && statsScanned){
            Globals.PLAYER_NAME = ((TextView) findViewById(R.id.editText)).getText().toString();
            Globals.STARTED = true;

            String barcode = barcodeHandlerWorld.getLastBarcode();
            engine.setSeed(barcode);
            engine.initWorld();

            System.err.println("BARCODE ---- " + barcode);

            String statcode = barcodeHandlerStats.getLastBarcode();
            engine.initPlayerStats(statcode);
            engine.initPlayer();


            System.err.println("BARCODE ---- " + statcode);

            vibrate(100);
            Intent myIntent = new Intent(CreatePlayerActivity.this, GameActivity.class);
            startActivity(myIntent);
        }
    }

    public void setWorldSeed(View view){
        barcodeHandlerWorld.showScanner();
        FloatingActionButton button = ((FloatingActionButton) findViewById(R.id.scan_cp1));
        button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        button.setEnabled(false);
        worldScanned = true;
    }

    public void setStatsSeed(View view){
        barcodeHandlerStats.showScanner();
        FloatingActionButton button = ((FloatingActionButton) findViewById(R.id.scan_cp2));
        button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        button.setEnabled(false);
        statsScanned = true;
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        barcodeHandlerWorld.getBarcode(requestCode, resultCode, data, 1);
        barcodeHandlerStats.getBarcode(requestCode, resultCode, data, 2);
        vibrate(100);
    }

    public void vibrate(int millis){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(millis);
    }

}
