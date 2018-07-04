package bit.hawwag2.displaytopartists;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<String>displayTopArtists;
    String JSONString=null;
    JSONObject artisObjectSnapshot =null;
    int responseCode;
    String imageText;
    String currentArtist;
    int currentArtistlistenerCount;
    ImageView ivTopArtistImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDisplayTopTracks=(Button) findViewById(R.id.btnFillList);
        btnDisplayTopTracks.setOnClickListener(new fillListOnClickHandler());

        //reference the imageView

         ivTopArtistImage=(ImageView) findViewById(R.id.ivDisplayTopArtis);


    }

    public class fillListOnClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            AsyncApiShowRawJSON APIThread= new AsyncApiShowRawJSON();
            APIThread.execute();




        }
    }

    //First Inner Class Aync
    public class AsyncApiShowRawJSON extends AsyncTask<Void,Void,String>
    {
        //"http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&limit=20&api_key=58384a2141a4b9737eacb9d0989b8a8c&format=json"

        @Override
        protected String doInBackground(Void... Params) {
            convertToJSONString();

            return JSONString;
        }

        //Executed on the main thread. Accepts whatever doInBackground returns
        protected void onPostExecute(String fetchedString)
        {
            try {
                //conver string to JSON object
                JSONObject artistData= new JSONObject(JSONString);

                //grab the object artists
                JSONObject artistObject=artistData.getJSONObject("artists");

                //Get the value of the object, array of objects type artit
                JSONArray artistArray=artistObject.getJSONArray("artist");

                //Retrieve the first object of the Artist which is the image.
                JSONObject topartist=artistArray.getJSONObject(0);

                //Now of that first object get the image which happens to be an array
                JSONArray topArtistImage=topartist.getJSONArray("image");
                int nimageArray=topArtistImage.length();

                //loops over artist objects extracting desired data to be displayed
                for(int i=0;i<nimageArray; i++)

                {
                    //gets an element of the object array
                    JSONObject image=topArtistImage.getJSONObject(i);
                    //looks for a stores  the elements at object(i)
                    //Looks for the key in the size and checks against
                    if(image.getString("size").equals("medium"))
                    {
                        //Hold the array under the image we want
                        imageText=image.getString("text");
                    }

                }

            }catch (Exception e)
            {
                System.out.println("Something went wrong creating  Json Object");
                e.printStackTrace();
            }

            imageAsync imgSync=new imageAsync();
            imgSync.execute(imageText);

        }


        public String convertToJSONString()
        {// Declare here so its not local to the try block

            try {
                String urlString="http://ws.audioscrobbler.com/2.0/?" +
                        "method=chart.gettopartists&limit=1&" +
                        "api_key=5cff6bd4a02a240bbfef15567f21c45d&" +
                        "format=json";

                //Convert the urlString into an URL Object
                URL URLObject=new URL(urlString);

                //Create a httlURL Connection object via openConnection comand of URLObject
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
                JSONObject artistObject=artistData.getJSONObject("artists");

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
                    currentArtistlistenerCount=currentArtistObject.getInt("listeners");

                    displayTopArtists.add(currentArtist+":  "+currentArtistlistenerCount);

                }

            }catch (Exception e)
            {
                System.out.println("Something went wrong creating  Json Object");
                e.printStackTrace();
            }


            return artisObjectSnapshot;
        }

        public ArrayList<String> displayArtistInfo()
        {
            return displayTopArtists;
        }

    }





    //The Second aysync that will create an image out of the url string
     class imageAsync extends AsyncTask<String, Void, Bitmap>
     {

         @Override
         protected Bitmap doInBackground(String... params) {
             //Read in the first oaran ti get url
             String imageURL=params[0];

             //Creat an empty BitMap
             Bitmap bitMap=null;

             try {

                 URL URLObject=new URL(imageURL);

                 //Create a httlURL Connection object via openConnection comand of URLObject
                 HttpURLConnection connections= (HttpURLConnection) URLObject.openConnection();

                 //send the url
                 connections.connect();

                 //if it doesnt connect return 200, you dont have data...
                 responseCode=connections.getResponseCode();
                 if (responseCode==200)
                 {
                     //use the bitMapFactory.decodeSteam to turn a urlString to its Image
                     InputStream is=(InputStream) new URL(imageURL).getContent();
                     bitMap = BitmapFactory.decodeStream(is);
                 }



             }catch (IOException e)
             {
                 e.printStackTrace();
             }


             return bitMap;
         }

         protected void onPostExecute(Bitmap bitmap)
         {
             ivTopArtistImage.setImageBitmap(bitmap);

             // create the instance of the syn
         }


     }






}
