package bit.hawwag2.mapsapp;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    int responseCode;
    String JSONString = null;
    private GoogleMap mMap;
    String longitudeInput;
    String latitudeInput;
    Double longitude;
    Double latitude;
    String region = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        AsyncApiShowRawJSON APIThread = new AsyncApiShowRawJSON();
        //execute the inputArtist called  method for input validation
        APIThread.execute();


    }
    public class AsyncApiShowRawJSON extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            createRandomLocation();
            convertoToJSONString();
            return JSONString;
        }

        protected void onPostExecute(String fetchedString) {
            //Fetching the JSON String or the string from the doInBackground
            LatLng city = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(city).title(region).snippet(region));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(city));

            //region is the collective of the city information

            try {
                JSONObject geopluginData = new JSONObject(fetchedString);
                String cityName = geopluginData.optString("geoplugin_place");
                String country = geopluginData.optString("geoplugin_countryCode");

                if (cityName != null && country != null) {
                    region =cityName + ", " + country;

                } else {
                    System.out.println("Blah");
                }


            } catch (Exception e) {
                System.out.println("Something went wrong creating  Json Object");
                createRandomLocation();
                e.printStackTrace();
            }

        }


        }


/*
        public String JsonStringToObject(String JSONString) {
            //region is the collective of the city information
            String region = null;
            String cityName;
            String country;
            try {
                if (JSONString == null||JSONString=="[[]]") {
                    System.out.println("jsonObject is null");
                    return null;
                } else {
                    JSONObject geopluginData = new JSONObject(JSONString);
                    //Grab the JSON OBject
                    cityName = geopluginData.getString("geoplugin_place");
                    country = geopluginData.getString("geoplugin_countryCode");
                    region =cityName + ", " + country;
                }
                region =cityName + ", " + country;



            } catch (Exception e) {
                System.out.println("Something went wrong creating  Json Object");
                createRandomLocation();
                e.printStackTrace();
            }
            return region;
        }
        */


        //setting the decimal formating
        public void createRandomLocation() {
            Random rand = new Random();
            //Random Longs
            Double longitudeminX = -180.00;
            Double longitudemaxX = 180.00;
            longitude = rand.nextFloat() * (longitudemaxX - longitudeminX) + longitudeminX;
            longitudeInput = String.valueOf(rand.nextFloat() * (longitudemaxX - longitudeminX) + longitudeminX);

            //Random Latitudes
            Double latitudeMinX = -90.00;
            Double latitudeMaxX = 90.00;
            latitude = rand.nextFloat() * (latitudeMaxX - latitudeMinX) + latitudeMinX;
            // Add a marker in Sydney and move the camera
            latitudeInput = String.valueOf(rand.nextFloat() * (latitudeMaxX - latitudeMinX) + latitudeMinX);
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
            }catch (Exception e) {
                System.out.println(responseCode);
            }
            return JSONString;
        }








    }



