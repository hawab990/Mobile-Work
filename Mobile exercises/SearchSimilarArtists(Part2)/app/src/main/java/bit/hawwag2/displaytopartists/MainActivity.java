package bit.hawwag2.displaytopartists;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText inputSimilarArtist;
    ArrayList<String>displayTopArtists;
    String JSONString=null;
    String inputStringHolder = null;
    JSONObject artistPbjectSnapShot =null;
    int responseCode;
    String currentArtist;
    Button btnDisplayTopTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set up the references to the controllers

        btnDisplayTopTracks=(Button) findViewById(R.id.btnSubmitSearch);

        btnDisplayTopTracks.setOnClickListener(new fillListOnClickHandler());

        inputSimilarArtist= (EditText) findViewById(R.id.editTextSearchFam);

    }

    public class fillListOnClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

           AsyncApiShowRawJSON APIThread= new AsyncApiShowRawJSON();

            //execute the inputArtist called  method for input validation
            APIThread.execute(inputArtist());
        }
    }
    //Create an input method Edit text and  and get the text and pass it into DOInBackgroundMethod for processing
    //The Do InBackgroundMethod will return a JSON Query Comparing it with the editText.
    public String inputArtist() {

        //Stores the input as length
        int inputArtists = inputSimilarArtist.length();

        //If input is more  then two characters
        if (inputArtists > 0) {

            //Store the string version into a variable
            inputStringHolder = inputSimilarArtist.getText().toString();

        } else {
            Toast.makeText(MainActivity.this, "Please Enter an Exact Name",Toast.LENGTH_LONG).show();
        }
        //Return the string
        return inputStringHolder;
    }

    public class AsyncApiShowRawJSON extends AsyncTask<String,Void,String>
    {
        @Override
        //params meaning the doInBackground will accept
        protected String doInBackground(String... params) {

            //call the convertToString Method that concatenates input into a variable to be passed into the URL and outputs the
            // API getSimiler jsonString.
            convertToJSONString();

            return JSONString;
        }

        //Executed on the main thread. Accepts whatever doInBackground returns
        protected void onPostExecute(String fetchedString)
        {
            convertoJSONOBjects();

            ListView lvTopArtistInfo=(ListView)findViewById(R.id.lvTopArtists);

            ArrayAdapter lvArrayDapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, displayArtistInfo());

            lvTopArtistInfo.setAdapter(lvArrayDapter);
        }

        public String convertToJSONString()
        {
            // Declare here so its not local to the try block
            //the input string to be inserted into the URl
            String input= inputStringHolder;


            try {
                /*

                   artist.getSimilar
                   Params:
                   limit (Optional) : Limit the number of similar artists returned
                   artist (Required (unless mbid)] : The artist name
                 */
                //insert input Variable into  url String
                String urlString="http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist="+input+"&limit=10&api_key=58384a2141a4b9737eacb9d0989b8a8c&format=json";


                //Convert the urlString into an URL Object
                URL URLObject=new URL(urlString);

                //Create a httlURL Connection object via openConnection command of URLObject
                HttpURLConnection connection= (HttpURLConnection) URLObject.openConnection();

                //send the url
                connection.connect();

                //if it doesnt connect return 200, you dont have data...
                 responseCode=connection.getResponseCode();

                //Get an inputstream from the httpurlconnection object and set up a buffereader
                InputStream is= connection.getInputStream();

                //creating an object of type inputstreamReader and passing it the streamReader
                InputStreamReader inputstreamReader= new InputStreamReader(is);

                //creating a bufferreader object to pass in the inputStreamReader
                BufferedReader bufferedReader= new BufferedReader(inputstreamReader);

                //read the input.
                String responseString;

                //Create an instance of the stringbuilder to start compositioning a string
                StringBuilder stringBuilder=new StringBuilder();

                //if the line isnt empty, using the string builder, make a string
                while((responseString=bufferedReader.readLine()) !=null)
                {
                    stringBuilder=stringBuilder.append(responseString);
                }

                //Get the string from the stringBuilder JSONString ready for parsing!
                JSONString=stringBuilder.toString();

            }  catch (Exception e)
            {
                System.out.println(responseCode);


            }

            return JSONString;
        }

        public JSONObject convertoJSONOBjects()
        {
            //Creates an array of the top artists to be displayed
            displayTopArtists=new ArrayList();
            try {

                //conver string to JSON object
                JSONObject artistData= new JSONObject(JSONString);

                //grab the object artists
                JSONObject artistObject=artistData.getJSONObject("similarartists");

                //Get the value of the object, array of objects type artit
                JSONArray artistArray=artistObject.getJSONArray("artist");
                // To save memory
                int numberOfArtists=artistArray.length();

                //loops over artist objects extracting desired data to be displayed
                for(int i=0;i<numberOfArtists; i++)

                {
                    //gets an element of the object array
                    JSONObject currentArtistObject=artistArray.getJSONObject(i);

                    //looks for a stores  the elements at object(i)
                    currentArtist=currentArtistObject.getString("name");

                    //currentArtistlistenerCount=currentArtistObject.getInt("listeners");
                    displayTopArtists.add(currentArtist);
                }

            }catch (Exception e)
            {
                System.out.println("|||||||||||||||Something went wrong when creating Json Object.||||||||||||||");

                System.out.println("|||||||||||||||Please Enter exact name.|||||||||||||||||||||||||||||||||||||");

                e.printStackTrace();
            }
            return artistPbjectSnapShot;
        }
        //returns a programmatically mutable arraylist holding entire looped artists(convertoJSONOBjects())

        public ArrayList<String> displayArtistInfo()
        {
            return displayTopArtists;
        }
    }






}
