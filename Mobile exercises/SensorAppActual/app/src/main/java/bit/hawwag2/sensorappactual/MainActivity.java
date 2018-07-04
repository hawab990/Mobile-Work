package bit.hawwag2.sensorappactual;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.Resources;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TextView tvShowSensors;
   // List<Sensor> sensorList;
    TextView tvShowX;
    TextView tvShowY;
    TextView tvShowZ;
    Sensor sensorSensor;
    SensorManager sm;
    Float ZCurrent;
    Float xCurrent;
    Float yCurrent;
    ImageView ivMovingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Show Sensor List
        super.onCreate(savedInstanceState);

         ZCurrent=0.0f;
         xCurrent=0.0f;
         yCurrent=0.0f;


        setContentView(R.layout.activity_main);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);




        //!!!!!!!!!Code to list sensors commented out!!!!!!!!!!!!///////
       // sensorList=sm.getSensorList(Sensor.TYPE_ALL);
       // tvShowSensors=(TextView)findViewById(R.id.tvShowResults);
        //Button btnShowList=(Button)findViewById(R.id.btnShowResults);
        //btnShowList.setOnClickListener(new btnShowClickHandler());
        //AccelerometerApp

        sensorSensor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(new showSensorClickHandler(),sensorSensor,sm.SENSOR_DELAY_NORMAL);

        //Using the scrollTo Method
        ivMovingImage=(ImageView)findViewById(R.id.ivMovingImage);
        ivMovingImage.scrollTo(Math.round(xCurrent),Math.round(yCurrent));
        ivMovingImage.setImageResource(R.drawable.trollie);


        tvShowX=(TextView)findViewById(R.id.tvXCoordinate);
        tvShowY=(TextView)findViewById(R.id.tvYCoordinate);
       tvShowZ=(TextView)findViewById(R.id.tvZCoordinate);




    }//endOncreat

    protected void onPause()
    {

        super.onPause();
        sm.unregisterListener(new showSensorClickHandler());



    }

    protected void onResume()
    {

        super.onResume();
        sm.registerListener(new showSensorClickHandler(),sensorSensor,sm.SENSOR_DELAY_NORMAL);



    }



    public class showSensorClickHandler implements SensorEventListener
    {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onSensorChanged(SensorEvent event) {
            Sensor senzor=event.sensor;


            if(senzor.getType()==Sensor.TYPE_ACCELEROMETER)
            {
                float x=event.values[0];
                float y=event.values[1];
                float z=event.values[2];

                    tvShowX.setText("X Change:"+xCurrent);
                    xCurrent=x;




                   tvShowZ.setText("Z Change:"+ZCurrent);
                    ZCurrent=z;



                    tvShowY.setText("y Change:"+yCurrent);
                    yCurrent=y;

                ivMovingImage.scrollBy(Math.round(x), Math.round(y));
                ivMovingImage.invalidate();

            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    /*

    public class btnShowClickHandler implements View.OnClickListener {


        @Override
        public void onClick(View v) {

            for(int i=0;i<sensorList.size();i++)
            {
                String SensorName=  sensorList.get(i).getName();
                tvShowSensors.append(SensorName);

            }


        }
    }*/

}
