package teamgarbo.github.io.eyeoftyche;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCaptureActivity;

/**
 * Created by hercu on 05-May-18.
 */

public class BarcodeHandler {

    private Context context;
    private String lastBarcode = "";
    private int offset = 0;

    BarcodeHandler(Context context, int offset)
    {
        this.context = context;
        this.offset = offset;
    }

    public static final int RC_BARCODE_CAPTURE = 9001;

    public void showScanner()
    {
        Intent intent;
        intent = new Intent(context, BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
        ((Activity)context).startActivityForResult(intent, RC_BARCODE_CAPTURE + offset);
    }

    public String getBarcode(int requestCode, int resultCode, Intent data, int offset)
    {
        if (requestCode == RC_BARCODE_CAPTURE + offset && resultCode == CommonStatusCodes.SUCCESS) {
            if (data != null) {
                Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                return barcode.displayValue;
            }
        }
        return null;
    }

    public String getLastBarcode() {
        return lastBarcode;
    }
}
