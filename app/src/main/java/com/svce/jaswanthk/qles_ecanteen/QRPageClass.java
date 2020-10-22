package com.svce.jaswanthk.qles_ecanteen;

import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import static android.graphics.Color.BLACK;

public class QRPageClass extends AppCompatActivity {
    public final static int WIDTH=500;
    public static int white = 0xFFFFFFFF;
    public static int black = 0xFF000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpage_class);

        ImageView imageView = (ImageView) findViewById(R.id.qrimage);
        try {
            Bitmap bitmap = encodeAsBitmap("Jaswanth-Tiffin-Idly-2 nos-25");
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();


        }

    }
    Bitmap encodeAsBitmap (String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE,WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }

        int w = 500;
        int h =500;
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? black : white;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
        return bitmap;
    }
}
