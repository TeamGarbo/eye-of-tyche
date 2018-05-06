package teamgarbo.github.io.eyeoftyche;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import teamgarbo.github.io.eyeoftyche.Engine.Engine;
import teamgarbo.github.io.eyeoftyche.Engine.Room;

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
        if(barcode != null)
        {
            if(!engine.getPastCodes().contains(barcode)){
                engine.getPastCodes().add(barcode);

            }
            else{
                //some form of penalisation
            }
        }
    }

    public void progressRoom(View view)
    {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        builder.setTitle("Which room?");

        final RoomAdapter adapter = engine.getRoomList(this);

        View v = LayoutInflater.from(this).inflate(R.layout.item_list_holder, null, false);
        ListView listView = v.findViewById(R.id.list_item_list_holder);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Room room = (Room) adapterView.getItemAtPosition(i);
                if (room != null)
                {
                    engine.progressRoom(room);
                    GameActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            appendText("Progressed room");
                        }
                    });

                }
                builder.dismiss();
            }
        });
        builder.setView(listView);

        builder.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        barcodeHandler.getBarcode(requestCode, resultCode, data);
    }

    public void appendText(String message)
    {
        TextView console = (TextView) findViewById(R.id.text_console);
        console.setText(console.getText().toString() + '\n' + message);
    }
}
