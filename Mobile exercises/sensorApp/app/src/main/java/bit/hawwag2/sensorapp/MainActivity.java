package bit.hawwag2.sensorapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView tvShowSensors;
    List<Sensor>sensorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SensorManager sm;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

       sensorList=sm.getSensorList(Sensor.TYPE_ALL);
         tvShowSensors=(TextView)findViewById(R.id.tvShowResults);
        Button btnShowList=(Button)findViewById(R.id.btnShowResults);
        btnShowList.setOnClickListener(new btnShowClickHandler());

    }//End of Oncreat

    public class btnShowClickHandler implements View.OnClickListener {


        @Override
        public void onClick(View v) {

            for(int i=0;i<sensorList.size();i++)
            {
                String SensorName=  sensorList.get(i).getName();
                tvShowSensors.append(SensorName);

            }

        }
    }


}
