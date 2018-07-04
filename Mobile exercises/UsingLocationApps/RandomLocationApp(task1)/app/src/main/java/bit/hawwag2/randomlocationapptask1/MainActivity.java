package bit.hawwag2.randomlocationapptask1;

import android.content.Context;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvLongitude;
    TextView tvLatitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Refence the  buttons
        Button btnClickRandomLocation= (Button) findViewById(R.id.btnCreateRandomLocation);
        btnClickRandomLocation.setOnClickListener(new btnClickHandler());
        //Reference the TextViews
         tvLongitude=(TextView)findViewById(R.id.textViewLongitude);
         tvLatitude=(TextView)findViewById(R.id.textViewLatitude);


        // Create you utiltiy object


    }

    public class btnClickHandler implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            setRandomLocation();
        }
    }


    //Setting up the random Location
    public void setRandomLocation()
    {
        //setting the decimal formating
        DecimalFormat df = new DecimalFormat("#.###");
        Random rand=new Random();
        //Random Longs
        Double longitudeminX=-180.00;
        Double longitudemaxX= 180.00;
        Double longitude= rand.nextFloat()*(longitudemaxX-longitudeminX)+longitudeminX;
        tvLongitude.setText (String.valueOf(df.format(longitude)));

        //Random Latitudes
        Double latitudeMinX=-90.00;
        Double latitudeMaxX= 90.00;
        Double latitude= rand.nextFloat()*(latitudeMaxX-latitudeMinX)+latitudeMinX;
        tvLatitude.setText(String.valueOf(df.format(latitude)));
    }
}
