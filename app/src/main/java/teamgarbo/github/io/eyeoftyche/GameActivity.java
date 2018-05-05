package teamgarbo.github.io.eyeoftyche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;

public class GameActivity extends AppCompatActivity {

    Engine engine;
    BarcodeHandler barcodeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        engine = Engine.getInstance();

        barcodeHandler = new BarcodeHandler(this);
    }

    public void scan(View view){
        barcodeHandler.showScanner();
        parseBarcode(barcodeHandler.getLastBarcode());
    }

    public void parseBarcode(String barcode){
        if(!engine.getPastCodes().contains(barcode)){
            engine.getPastCodes().add(barcode);
        }
        else{
            //some form of penalisation
        }
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        barcodeHandler.getBarcode(requestCode, resultCode, data);
    }
}
