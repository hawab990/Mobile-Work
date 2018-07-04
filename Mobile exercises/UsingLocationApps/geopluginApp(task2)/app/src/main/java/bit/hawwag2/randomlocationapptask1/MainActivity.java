package bit.hawwag2.randomlocationapptask1;

import android.content.Context;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DecimalFormat df;
    int responseCode;
    String JSONString = null;
    TextView tvClosestCity;
    TextView tvLongitude;
    TextView tvLatitude;
    String longitudeInput;
    String latitudeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Refence the  buttons
        Button btnClickRandomLocation = (Button) findViewById(R.id.btnCreateRandomLocation);
        btnClickRandomLocation.setOnClickListener(new btnClickHandler());
        tvLongitude = (TextView) findViewById(R.id.textViewLongitude);
        tvLatitude = (TextView) findViewById(R.id.textViewLatitude);


    }

    public class btnClickHandler implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            //Reference the TextViews
            createRandomLocation();

            AsyncApiShowRawJSON APIThread = new AsyncApiShowRawJSON();
            //execute the inputArtist called  method for input validation
            APIThread.execute();
        }
    }

    public String convertoToJSONString() {
        try {

            String urlString = "http://www.geoplugin.net/extras/location.gp?lat=" + latitudeInput + "&long=" + longitudeInput + "&format=json";

            //convert urlString into a URL object
            URL URLObject = new URL(urlString);

            // create a http URl Connection object via openConnection command of URLOnbject
            HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

            //Send the url
            connection.connect();

            //if it doesnt connect return 209, you dont have data..
            responseCode = connection.getResponseCode();

            //Get an inputstream from the httpurlconnection object and set up a buffereader
            InputStream is = connection.getInputStream();

            //creating an object of type inputstreamReader and passing it the streamReader
            InputStreamReader inputstreamReader = new InputStreamReader(is);

            //creating a bufferreader object to pass in the inputStreamReader
            BufferedReader bufferedReader = new BufferedReader(inputstreamReader);

            //read the input.
            String responseString;

            //Create an instance of the stringbuilder to start compositioning a string
            StringBuilder stringBuilder = new StringBuilder();

            //if the line isnt empty, using the string builder, make a string
            while ((responseString = bufferedReader.readLine()) != null) {
                stringBuilder = stringBuilder.append(responseString);
            }

            //Get the string from the stringBuilder JSONString ready for parsing!
            JSONString = stringBuilder.toString();


        } catch (Exception e) {
            System.out.println(responseCode);
        }
        return JSONString;
    }


    //Creating the Random Locations
    public void createRandomLocation() {
        //setting the decimal formating and a random Location
        df = new DecimalFormat("#.###");
        Random rand = new Random();
        //Random Longs
        Double longitudeminX = -180.00;
        Double longitudemaxX = +180.00;
        longitudeInput = String.valueOf(rand.nextFloat() * (longitudemaxX - longitudeminX) + longitudeminX);
        tvLongitude.setText(longitudeInput);

        //Random Latitudes
        Double latitudeMinX = -90.00;
        Double latitudeMaxX = +90.00;
        latitudeInput = String.valueOf(rand.nextFloat() * (latitudeMaxX - latitudeMinX) + latitudeMinX);
        tvLatitude.setText(latitudeInput);


    }

    //Sends a toast when theres a null value
    public void TheresTheOcean() {
        Toast.makeText(MainActivity.this, "Landed In the Ocean", Toast.LENGTH_SHORT).show();
    }

    // Async Task for lat, long webservice
    public class AsyncApiShowRawJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            //call the convertToString Method that concatenates input into a variable to be passed into the URL and outputs the
            //Creating the randomLocation

            convertoToJSONString();
            //return the JSON string version
            return JSONString;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String fetchedString) {
            //Fetching the JSON String or the string from the doInBackground
            String cityData = JsonStringToObject(fetchedString);
            //Calling the function fillcity Name and passing in the cityData

            //Reference the TextView
            tvClosestCity = (TextView) findViewById(R.id.tvClosestCity);
            //display the cityData object collection(region) from citDataFunction.
            tvClosestCity.setText(cityData);


        }

        public String JsonStringToObject(String JSONString) {
            //region is the collective of the city information
            String region = null;
            String cityName;
            String country;
            try {
                if (JSONString == null) {
                    return null;
                } else {
                    JSONObject geopluginData = new JSONObject(JSONString);
                    //Grab the JSON OBject
                    cityName = geopluginData.getString("geoplugin_place");
                    country = geopluginData.getString("geoplugin_countryCode");
                }
                region =cityName + ", " + country;



            } catch (Exception e) {
                System.out.println("Something went wrong creating  Json Object");
                TheresTheOcean();
                e.printStackTrace();
            }
            return region;
        }


    }

}