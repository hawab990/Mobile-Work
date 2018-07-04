package bit.hawwag2.qrcodecamerapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class CameraIntent extends AppCompatActivity {

    SurfaceView cameraView;
    TextView barcodeInfo;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_intent);
        //he layout should have a SurfaceView in order to display the preview frames captured by the camera.
        // add a TextView to display the contents of the QR codes the API detects.
        cameraView = (SurfaceView)findViewById(R.id.camera_view);
       // barcodeInfo = (TextView)findViewById(R.id.code_info);




        //fetch a stream of images from the device’s camera and display them in the SurfaceView,
        // create a new instance of the CameraSource class using CameraSource.Builder
        barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            //Next, add a callback to the SurfaceHolder of the SurfaceView so that you know when you can start drawing the preview
            //frames. The callback should implement the SurfaceHolder.Callback interface.
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
                // stop drawing the preview frames.
                cameraSource.stop();

            }
        });

        //Output of QRCode contents detected
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    String temp=barcodes.valueAt(0).displayValue;
                    Intent returnIntent= new Intent();

                            returnIntent.putExtra("requestedResult",temp);

                    setResult(Activity.RESULT_OK,returnIntent);


                    finish();



                }
            }
        });
    }//End of Oncreate
}
