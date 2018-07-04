package bit.hawwag2.formattedfileio;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String JSONInput="";
    String titleJSONFocus;
    String assetFileName;
    JSONObject titleObject=null;
    JSONObject descriptionObject=null;
    String currentTitleName;
    ArrayList<String>displayTitlenames;
    String currentDiscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnFillList = (Button) findViewById(R.id.btnFillEvents);
        btnFillList.setOnClickListener(new btnOnclickHandler());
        Resources resourcesMachine = getResources();
        readFile();



    }

    public class btnOnclickHandler implements View.OnClickListener

    {
        @Override
        public void onClick(View v) {

            titlesToJSON();
            ListView lvTitles=(ListView) findViewById(R.id.lvDunedinEvents);
            ArrayAdapter lvtitlesArrayAdapter= new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,displayTitlenames);
            lvTitles.setAdapter(lvtitlesArrayAdapter);
            // onclicklistner for discription listview option
            lvTitles.setOnItemClickListener(new setDiscriptionTitleHandler());
        }

    }

    public class setDiscriptionTitleHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Call the eventDescription method passing in the JSONInput
            eventDescriptionTOJSON(JSONInput,position);


        }
    }



    public String readFile() {

         assetFileName = "dunedin_events.json";

        //Get an asset managet and creat an input steam from the JSON FIle.
        AssetManager am = getAssets();

        try {
            InputStream inputStream = am.open(assetFileName);

            //determine numbr of bytes in fle, and prepare buffer array for read.
            int fileSizeInBytes = inputStream.available();
            byte[] JSONBuffer = new byte[fileSizeInBytes];

            //Read in the steam into the buffer, and close it
            inputStream.read(JSONBuffer);
            inputStream.close();

            //Creat a string from the byte
            JSONInput = new String(JSONBuffer);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONInput;
    }


    //JSONObject modifiable set of name/value mappings and extends Object.WARNING=Not thread safe, but works.
    public JSONObject titlesToJSON()
    {

        //create an array to return a structured  list
        //of the title names
        displayTitlenames= new ArrayList();
        //Requires to be in a try catch
        try {
            //No need to reassign JSONINnput
             titleJSONFocus=JSONInput;
            //Convert file string to JSONObject
            JSONObject eventData = new JSONObject(titleJSONFocus);
            //Grab the array of events  Events
            // Save event object  by its keys(Events)
            JSONObject EventsObject= eventData.getJSONObject("events");


            //Now get the value of the object(an array of objects)
            JSONArray eventsArray=EventsObject.getJSONArray("event");

            //get a count of the number of values to loop through
            int ntitles=eventsArray.length();

            for (int index=0; index<ntitles; index++)
            {
                //get an element from the array
                JSONObject currentEventObject=eventsArray.getJSONObject(index);
                //Access the title Value. This is a string, not a object
                currentTitleName= currentEventObject.getString("title");
                //when when found
                //adding each item to the structure array and call the  string method.
                displayTitlenames.add(displayTitlename());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return titleObject;
    }



    //a method that passes in the JSONInput string and its position
    public void eventDescriptionTOJSON(String titleJSONFocus, int position) {
        //create an array to return a structured  list  //of the descriptions

        ListView lvTitles=(ListView) findViewById(R.id.lvDunedinEvents);


        try {
            //Convert file string to JSONObject
            JSONObject eventData = new JSONObject(titleJSONFocus);


            JSONObject EventsObject = eventData.getJSONObject("events");

            JSONArray eventsArray = EventsObject.getJSONArray("event");

            int nDescription = eventsArray.length();

            for (int descriptionIndext = 0; descriptionIndext < nDescription; descriptionIndext++) {
                // get an element from the array
                JSONObject currentEventObject = eventsArray.getJSONObject(descriptionIndext);

                // Access the title value(String) again for check
                currentTitleName = currentEventObject.getString("title");
                //Checks the position of the title name and pops a toast for its discription
                //if "Title"==Description" at position click
                if(currentTitleName.equals(lvTitles.getItemAtPosition(position).toString()))
                {
                    String currentEventDescript=currentEventObject.getString("description");
                    Toast.makeText(this,currentEventDescript,Toast.LENGTH_LONG).show();

                }


            }


        } catch (JSONException e) {
            {
                e.printStackTrace();
            }

        }

    }

    //String to display title names
    public String displayTitlename()
    {

        return currentTitleName;
    }


}