package com.bit.iotdatabaseapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.bit.iotdatabaseapp.R.id.viewItems;

public class SearchDatabase extends AppCompatActivity {
    EditText inputLanguage;
    String inputStringHolder;
    String inputLanguageTexTresult;
    public  String base_Url = "https://iis-dev.op-bit.nz/IoTDatabase/api/itemsIssued?";
    String searchTypeJSONString = null;
    Spinner typeSpinner;
    String SelectedSpinnerValue;
    String SwappedValueContainer;
    String storeJSON;
    ArrayList<String> SearchJSONResults;
    ArrayList<String> displayQSearchResults;
    HttpWorkerSearch APiSearchThread;
    TextView tv_personID;
    TextView tv_phoneNumber;
    TextView tv_name;
    TextView tv_email;
    String personNameProp;
    String personPhoneProp;
    String PersonEmailProp;
    String personID;






    @Override
    //Oncreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_database);
        //setting up the controllers and spinner call
        setUpControllers();
        setUpSpinner();
        SelectedSpinnerValue = typeSpinner.getSelectedItem().toString();
        // Find the toolbarsearchdatabase view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbarsearchdatabase exists in the activity and is not null
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.databaseicon);
        toolbar.setTitle("Search Database");
        getSupportActionBar().setDisplayUseLogoEnabled(true);



    }//end Of Oncreat

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_without_search, menu);

        return true;
    }


    @Override
    //gets the intent based on the XML item.
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intentSwitcher;
        switch (item.getItemId()) {
            case viewItems:

                intentSwitcher = new Intent(SearchDatabase.this,ViewDatabase.class);
                startActivity(intentSwitcher);
                break;

            // do whatever
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    //Setting up the spinner
    public void setUpSpinner() {
        typeSpinner = (Spinner) findViewById(R.id.spner_searchTyp);
        //String Array that will be in the Spinnner items
        String[] searchType = {"Item Issued By Type", "Items Issued By Person"};

        //Adapter that populates the list view.
        ArrayAdapter<String> searchTypeAdapter = new ArrayAdapter(SearchDatabase.this, android.R.layout.simple_spinner_item, searchType);

        typeSpinner.setAdapter(searchTypeAdapter);
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //On item selected Spinner
        typeSpinner.setOnItemSelectedListener(new onClickHandler());
    }


    public void setUpControllers()
    {
        ImageView ivsearch=(ImageView) findViewById(R.id.iv_buttonSearch);
        ivsearch.setOnClickListener(new SearchOnclickListener());
        inputLanguage=(EditText) findViewById(R.id.et_search);//Assigning Variable to user input
        inputLanguageTexTresult= inputLanguageText();
        inputLanguage.setEnabled(false);
        tv_personID =(TextView)findViewById(R.id.tv_fillerPersonID);
        tv_phoneNumber =(TextView)findViewById(R.id.tv_PhoneNumber);
        tv_name =(TextView)findViewById(R.id.tv_Name);
        tv_email =(TextView)findViewById(R.id.tv_email);


    }

    private class SearchOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            //API thread the HTTP worker is going to work on.
                APiSearchThread = new SearchDatabase.HttpWorkerSearch();
                try {
                    //after the call parameters are executed, execute the API thread.
                    APiSearchThread.execute(inputLanguageText()).get();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();

                }




        }
    }
    //OnItemSelected gets the ID of the list element
    private class onClickHandler implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            SelectedSpinnerValue=typeSpinner.getSelectedItem().toString();
            inputLanguage.setEnabled(true);


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    //Converts the Spinner Item into the URL parameters and returns the JSONString accordingly
    public String convertToApiCommand()
    {

        switch(SelectedSpinnerValue)
        {

            case "Item Issued By Type":
                SwappedValueContainer ="type=";
                storeJSON=HTTPGetJSONString(SwappedValueContainer,inputStringHolder);
                break;

            case "Items Issued By Person":
                SwappedValueContainer ="person=";
                storeJSON=HTTPGetJSONString(SwappedValueContainer,inputStringHolder);
                break;
        }

        return storeJSON;
    }



    //User input validation
    public String inputLanguageText() {

        //Stores the input as length
        int languageInputChars = inputLanguage.length();

        //If input is more  then two characters
        if (languageInputChars >2) {

            //Store the string version into a variable
            inputStringHolder = inputLanguage.getText().toString();

        } else {
            Toast.makeText(SearchDatabase.this, "Please enter a valid name or component type.",Toast.LENGTH_LONG).show();
        }
        //Return the string
        return inputStringHolder;
    }



    public String HTTPGetJSONString(String apiCommand,String SearchValue)
    {

        try
        {
            //searchValue==     type=
            String api = base_Url + apiCommand+SearchValue;

            String replace = api.replaceAll(" ","%20");     // Remove whitespace in query parameters
            URL url = new URL(replace);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200)
            {
                InputStream stream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader br = new BufferedReader(reader);

                String responseString;
                StringBuilder builder = new StringBuilder();

                while ((responseString = br.readLine()) != null)
                {
                    builder = builder.append(responseString);
                }
                searchTypeJSONString = builder.toString();



            }

        } catch (Exception e) {
            System.out.println("|||||||||||||||Something went wrong when creating Json Object.||||||||||||||");

            e.printStackTrace();
        }



        return searchTypeJSONString;
    }


    //HTTP worker Search that runs the JSON Parser and converts the string into JSON objects
    public class HttpWorkerSearch extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {
            // Store the JSON String and return it
            String convertedStorage=convertToApiCommand();
            return convertedStorage;
        }

        @Override
        protected void onPostExecute(String fetchedString)
        {

            // Bundle the searchresults to the search Listview
                Bundle bundle = new Bundle();

                SearchJSONResults = ConvertToJSONObjectSearch(convertToApiCommand());

            //System.out.println(SearchJSONResults);
            //Check if an invalid search is made  and return a toast.
            if (SearchJSONResults.isEmpty())
            {
                Toast.makeText(SearchDatabase.this, "Invalid Search.", Toast.LENGTH_LONG).show();
            }
            else
            {
                // Add a key to the JSON
                bundle.putSerializable("SearchJSONResults", SearchJSONResults);
                search_listview dynamicFragments = new search_listview();
                dynamicFragments.setArguments(bundle);
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.searchListFragholder, dynamicFragments);
                ft.commit();
            }


            }


        }




        //Converts the JSONString into JSONObject
    public ArrayList ConvertToJSONObjectSearch(String JsonString)
    {

        displayQSearchResults = new ArrayList();

        if (SwappedValueContainer =="type=")
        {
            try
            {
                //conver string to JSON object
                JSONArray searchData= new JSONArray(JsonString);

                int indexSearch= searchData.length();
                for(int j=0; j<indexSearch; j++)
                {
                    JSONObject SearchResultObjectStructure=searchData.getJSONObject(j);

                    //Base Object Propery
                    String itemIssueProperty=SearchResultObjectStructure.getString("itemIssuedID");

                    //Person object
                    JSONObject PersonObject=SearchResultObjectStructure.getJSONObject("person");

                    //itemObject
                    JSONObject itemObjectPropertyVar=SearchResultObjectStructure.getJSONObject("item");
                    String itemDeployedIDVar=itemObjectPropertyVar.getString("itemID");
                    String itemDeployedNote=itemObjectPropertyVar.getString("itemNote");

                    //subtype object
                    JSONObject subtypeDeployedObject=itemObjectPropertyVar.getJSONObject("subType");
                    String subTypeIDVar=subtypeDeployedObject.getString("subTypeID");
                    String subTupeNameProperty=subtypeDeployedObject.getString("subTypeName");
                    String descriptionProperty=subtypeDeployedObject.getString("description");



                    //type object
                    JSONObject deployedTypeObject=subtypeDeployedObject.getJSONObject("type");
                    String typeDeployedPropertyID=deployedTypeObject.getString("typeID");
                    String typenameDeployedProperty=deployedTypeObject.getString("typeName");


                    //For Debugging
                /*
                String key;
                Iterator<String> thing =  itemobjectVar.keys();
                while(thing.hasNext())
                {
                    key = thing.next();
                    System.out.println(key);
                }
                */



                    displayQSearchResults.add(  itemIssueProperty+ "\n" + itemDeployedNote + "\n"
                            + subTypeIDVar + "\n" + typeDeployedPropertyID + typenameDeployedProperty);
                    System.out.println("out");

                }

            }

            catch (JSONException e) {

                System.out.println("|||||||||||||||Something went wrong when creating Json deployed Object.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!||||||||||||||");
                e.printStackTrace();
            }
        }else
        {
            try
            {
                //conver string to JSON object
                JSONArray searchData= new JSONArray(JsonString);

                int indexSearch= searchData.length();
                for(int j=0; j<indexSearch; j++)
                {
                    JSONObject SearchResultObjectStructure=searchData.getJSONObject(j);

                    //Base Object Propery
                    String itemIssueProperty=SearchResultObjectStructure.getString("itemIssuedID");



                    //Person object
                    JSONObject PersonObject=SearchResultObjectStructure.getJSONObject("person");
                     personNameProp=PersonObject.getString("name");
                     personPhoneProp=PersonObject.getString("phoneNumber");
                     PersonEmailProp=PersonObject.getString("email");
                    personID=PersonObject.getString("personID");



                    //itemObject
                    JSONObject itemObjectPropertyVar=SearchResultObjectStructure.getJSONObject("item");
                    String itemDeployedIDVar=itemObjectPropertyVar.getString("itemID");
                    String itemDeployedNote=itemObjectPropertyVar.getString("itemNote");


                    //subtype object
                    JSONObject subtypeDeployedObject=itemObjectPropertyVar.getJSONObject("subType");
                    String subTypeIDVar=subtypeDeployedObject.getString("subTypeID");
                    String subTypeNameProperty=subtypeDeployedObject.getString("subTypeName");
                    String descriptionProperty=subtypeDeployedObject.getString("description");



                    //type object
                    JSONObject deployedTypeObject=subtypeDeployedObject.getJSONObject("type");
                    String typeDeployedPropertyID=deployedTypeObject.getString("typeID");
                    String typenameDeployedProperty=deployedTypeObject.getString("typeName");


                    //For Debugging
                /*
                String key;
                Iterator<String> thing =  itemobjectVar.keys();
                while(thing.hasNext())
                {
                    key = thing.next();
                    System.out.println(key);
                }
                */



                    displayQSearchResults.add(
                          "TypeID:    "+typeDeployedPropertyID + "\n" +
                                  "Category:   " +typenameDeployedProperty+
                                  "\n"+"Component Name:   "+subTypeNameProperty+"\n"+
                                  "Item Note:   "+itemDeployedNote);
                    System.out.println("out");

                }
                tv_personID.setText("Person ID:  "+personID);
                tv_name.setText("Name:  "+personNameProp);
                tv_phoneNumber.setText("Phone:  "+personPhoneProp);
                tv_email.setText("Email:  "+PersonEmailProp);



            }

            catch (JSONException e) {

                System.out.println("|||||||||||||||Something went wrong when creating Json deployed Object.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!||||||||||||||");
                e.printStackTrace();
            }
        }

        return displayQSearchResults;
    }



}
