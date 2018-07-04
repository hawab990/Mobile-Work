package bit.hawwag2.qrcodecamerapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {



    TextView tvResult;
    String result;
    Intent goToIntent;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnClickCamera = (Button) findViewById(R.id.btnOpenQRReader);
        btnClickCamera.setOnClickListener(new btnClickHandler());
        tvResult = (TextView) findViewById(R.id.tvResult);


    }//End of oncreate


    public class btnClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(MainActivity.this, CameraIntent.class);
            startActivityForResult(changeActivityIntent, 1);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            //If the result is a string// display the result

            String pattern="^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
            result = data.getStringExtra("requestedResult");
            //Creat the pattern object
            Pattern r=Pattern.compile(pattern);
            //Create the matcher object
            Matcher m=r.matcher(result);


            if (m.matches())
            //if its a url, go to the website.
            {
                Uri goToWebsite = Uri.parse(result);
                goToIntent = new Intent(Intent.ACTION_VIEW, goToWebsite);
                startActivity(goToIntent);
            }
            else{
                tvResult.setText(result);
            }

                /*
                switch (result) {
                    case "january" :
                        int  January++;
                         january="january"+January;
                        break;

                    case "february" :
                        February++;
                         february="february"+February;
                        break;
                    case "march" :
                        March++;
                        march="march"+March;
                        break;
                    case "april" :
                        April++;
                        april="april"+April;
                        break;
                    case "september" :
                        September++;
                        september="september"+September;
                        break;


                }
                */
                /*
                String [] month=new String[2];
                month[0]="march";
                month[1]="september";


                switch (month.length) {
                    case 1:
                        result="march";
                        month[0]=month[0]+1;
                        tvResult.setText(result+""+month.toString()+"\n");
                        break;
                    case 2:
                        result="september";
                        month[0]=month[0]+1;
                        tvResult.setText(result+""+month+"\n");
                        break;


                }

*/

            }

        }

    }

