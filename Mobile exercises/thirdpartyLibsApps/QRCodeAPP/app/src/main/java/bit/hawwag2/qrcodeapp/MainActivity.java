package bit.hawwag2.qrcodeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    /*
    SurfaceView cameraView;
    TextView barcodeInfo;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    */
    SparseArray<Barcode> barcodes;

    @Override
    //Create a new Java class called MainActivity.java. Make it a subclass of Activity and override its onCreate method.
    // Inside the onCreate method, call setContentView to apply the layout you created in the previous step.
    // Next, use findViewById to get references to the widgets defined in the layout.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        TextView tvQRContent=(TextView)findViewById(R.id.tvQRContents);
        /*
        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        barcodeInfo = (TextView) findViewById(R.id.code_info);

        */

        /*

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(cameraView.getHolder());

                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //Similarly, inside the surfaceDestroyed method, call the stop method of the CameraSource to stop drawing the preview frames
                cameraSource.stop();
            }
        });*/




        try {
            //open the photo using the open method of the AssetManager class and pass the InputStream
            // returned to the decodeStream method of BitmapFactory.
            // To keep it simple, do it inside the onCreate method of your Activity.
            Bitmap myQRCode = BitmapFactory.decodeStream(
                    getAssets().open("march.png")
            );

            //To detect QR codes(and other types of barcodes), you should use an instance of the BarcodeDetector class.
            // The following code shows you how to create one using BarcodeDetector.Builder:
            BarcodeDetector barcodeDetector =
                    new BarcodeDetector.Builder(this)
                            .setBarcodeFormats(Barcode.QR_CODE)
                            .build();

//

            //Use Frame.Builder to create a Frame using the Bitmap you created earlier.
            Frame myFrame = new Frame.Builder()
                    .setBitmap(myQRCode)
                    .build();

            //Call the detect method of the BarcodeDetector to generate a SparseArray containing all the QR
            // codes the BarcodeDetector detected in your photo.
             barcodes = barcodeDetector.detect(myFrame);

            //However, I suggest you use the easier to read displayValue field instead.
            // Here’s some code that prints the contents of the first QR code the API detected:

            // Check if at least one barcode was detected
            if (barcodes.size() != 0) {
                String temp=barcodes.valueAt(0).displayValue;
                tvQRContent.setText("My QR Code's Data is:  "+ temp);
                // Print the QR code's message
                Log.d("My QR Code's Data", temp

                );
            }

            //To fetch a stream of images from the device’s camera and display them in the SurfaceView,
            // create a new instance of the CameraSource class using CameraSource.Builder.
            // Because the CameraSource needs a BarcodeDetector, create one using the BarcodeDetector.Builder class.
            // If you want, you can adjust the dimensions of the camera preview using the setRequestedPreviewSize method.

            /*
            barcodeDetector =
                    new BarcodeDetector.Builder(this)
                            .setBarcodeFormats(Barcode.DATA_MATRIX|Barcode.QR_CODE)
                            .build();

            cameraSource = new CameraSource
                    .Builder(this, barcodeDetector)
                    .setRequestedPreviewSize(640, 480)
                    .build();

*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Camera Initialization works.
        //barcode detector does not

/*
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() == 0) {
                    barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
                        public void run() {
                            barcodeInfo.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue
                            );
                        }
                    });
                }
            }
        });*/
    }
}