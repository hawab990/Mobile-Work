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
import android.widget.Spinner;

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

import static com.bit.iotdatabaseapp.R.id.searchDatabase;
import static com.bit.iotdatabaseapp.R.id.viewItems;

public class ViewDatabase extends AppCompatActivity {
    public  String base_Url = "https://iis-dev.op-bit.nz/IoTDatabase/api/";
    String selectionValue;
    ArrayList<String> displayQItemResults;
    ArrayList<String> displayQItemIssuedResults;
    ArrayList<String> displayPersonDetails;
    ArrayList<String> displayDeployedDetails;
    HttpWorkerItems APIThread;
    HttpWorkerItemsIssued ApiThreadItemsIssued;
    HttpWorkerPerson ApiThreadPerson;
    HttpWorkerDeployedItems APIThreadDeployed;
    String JSONString = null;
    Spinner itemSpinner;
    String outPutContainer;

    String storeJSON = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_database);

        setUpSpinner();

        selectionValue = itemSpinner.getSelectedItem().toString();
        convertToApiCommand();


        // Find the toolbarsearchdatabase view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbarsearchdatabase exists in the activity and is not null
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.databaseicon);
        toolbar.setTitle("View Database");
        getSupportActionBar().setDisplayUseLogoEnabled(true);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_without_view, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intentSwitcher;
        switch (item.getItemId()) {
            case searchDatabase:

                intentSwitcher = new Intent(ViewDatabase.this,SearchDatabase.class);
                startActivity(intentSwitcher);
                break;
            // do whatever
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }



    public void setUpSpinner() {
        itemSpinner = (Spinner) findViewById(R.id.spn_itemView);
        String[] searchType = {"Persons", "Items","Items Issued","Deployed Items"};

        ArrayAdapter<String> searchTypeAdapter = new ArrayAdapter(ViewDatabase.this, android.R.layout.simple_spinner_item, searchType);

        itemSpinner.setAdapter(searchTypeAdapter);
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        itemSpinner.setOnItemSelectedListener(new onClickHandler());


    }

    //Run the Seperate API thread based on the Spinner Item selected
    private class onClickHandler implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            selectionValue = itemSpinner.getSelectedItem().toString();
            convertToApiCommand();
            switch (outPutContainer)
            {
                case "item":
                    APIThread = new HttpWorkerItems();
                    try {
                        APIThread.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    break;
                case "itemsIssued":
                    ApiThreadItemsIssued = new HttpWorkerItemsIssued();

                    try {
                        ApiThreadItemsIssued.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    break;
                case "person":
                    ApiThreadPerson = new HttpWorkerPerson();
                    try {
                        ApiThreadPerson.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    break;

                case "Deploy":
                    APIThreadDeployed = new HttpWorkerDeployedItems();
                    try {
                        APIThreadDeployed.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            //bundle.str("itemsQuery",displayQItemResults);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public String convertToApiCommand()
    {

        switch(selectionValue)
        {

            case "Items":
                outPutContainer="item";
                storeJSON=HTTPGetJSON(outPutContainer);
                break;

            case "Items Issued":
                outPutContainer="itemsIssued";
                storeJSON=HTTPGetJSON(outPutContainer);
                break;

            case "Persons":
                outPutContainer="person";
                storeJSON=HTTPGetJSON(outPutContainer);
                break;

            case "Deployed Items":
                outPutContainer="Deploy";
                storeJSON=HTTPGetJSON(outPutContainer);
                break;
        }

        return storeJSON;
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Http workers that HTTP call/parse and convert into JSON objects.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class HttpWorkerItems extends AsyncTask<String, Void, String>
{

    @Override
    protected String doInBackground(String... strings) {

        return convertToApiCommand();
    }

    @Override
    //Bundle the string and send it to the View Database List Fragment.
    protected void onPostExecute(String data)
    {
        Bundle bundle = new Bundle();

        displayQItemResults = ConvertToJSONArrayItem(convertToApiCommand());
        //Bundle the data to be read for transmittion
        bundle.putSerializable("itemsQuery", displayQItemResults);
        bundle.putString("selectionValue",outPutContainer);
        listview dynamicFragment = new listview();
        //transmit the data upon commit.
        dynamicFragment.setArguments(bundle);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Fragholder, dynamicFragment);
        ft.commit();



    }

}

    public class HttpWorkerDeployedItems extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            return convertToApiCommand();
        }

        @Override
        protected void onPostExecute(String data)
        {
            Bundle bundle = new Bundle();

            displayDeployedDetails = ConvertToJSONDeployedItems(convertToApiCommand());
            bundle.putSerializable("deployedQueryResults", displayDeployedDetails);
            bundle.putString("selectionValue",outPutContainer);
            listview dynamicFragment = new listview();
            dynamicFragment.setArguments(bundle);
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Fragholder, dynamicFragment);
            ft.commit();



        }

    }
    public class HttpWorkerItemsIssued extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            return convertToApiCommand();
        }

        @Override
        protected void onPostExecute(String data)
        {
            Bundle bundle = new Bundle();

            displayQItemIssuedResults = ConvertToJSONArrayItemsIssued(convertToApiCommand());
            bundle.putSerializable("itemsQIssuedResult", displayQItemIssuedResults);

            bundle.putString("selectionValue",outPutContainer);
            listview dynamicFragment = new listview();
            dynamicFragment.setArguments(bundle);
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Fragholder, dynamicFragment);
            ft.commit();
        }

    }


    public class HttpWorkerPerson extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            return convertToApiCommand();
        }

        @Override
        protected void onPostExecute(String data)
        {
            Bundle bundle = new Bundle();

            displayPersonDetails = ConvertToJSONPerson(convertToApiCommand());
            bundle.putSerializable("personQResult", displayPersonDetails);

            bundle.putString("selectionValue",outPutContainer);
            listview dynamicFragment = new listview();
            dynamicFragment.setArguments(bundle);
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Fragholder, dynamicFragment);
            ft.commit();
        }

    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Http workers that HTTP call/parse and convert into JSON objects.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Converts the JSONStrings to JSON objects.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public ArrayList ConvertToJSONPerson(String JsonString)
    {

        displayPersonDetails = new ArrayList();
        try
        {
            //conver string to JSON object
            JSONArray queryData= new JSONArray(JsonString);

            int indexedPersonData= queryData.length();
            for(int i=0; i<indexedPersonData; i++)
            {
                JSONObject personObject=queryData.getJSONObject(i);

                String personIDObjectVar=personObject.getString("personID");
                String nameIDVar=personObject.getString("name");
                String  phoneNumberIDVar=personObject.getString("phoneNumber");
                String  personEmailVar=personObject.getString("email");


                displayPersonDetails.add("Person ID:    "+personIDObjectVar+"\n"+"Person Name:    "+nameIDVar+"\n"+"Person PhoneNumber:    "+phoneNumberIDVar+"\n"+"Person Email:    "+personEmailVar+"\n");
            }


        }

        catch (JSONException e) {
            System.out.println("|||||||||||||||Something went wrong when creating Json Object.||||||||||||||");
            e.printStackTrace();
        }


        return displayPersonDetails;
    }


    public ArrayList ConvertToJSONArrayItem(String JsonString)
{

    displayQItemResults = new ArrayList();
    try
    {
        //conver string to JSON object
        JSONArray queryData= new JSONArray(JsonString);

      int indexedItemData= queryData.length();
        for(int i=0; i<indexedItemData; i++)
        {
            JSONObject itemObject=queryData.getJSONObject(i);

            String itemID=itemObject.getString("itemID");
            //grab the object artists

            String itemNote=itemObject.getString("itemNote");
            JSONObject subTypeObject=itemObject.getJSONObject("subType");
            String sybTypeID=subTypeObject.getString("subTypeID");
            String  subtypeNames=subTypeObject.getString("subTypeName");
            String  descript=subTypeObject.getString("description");

            JSONObject typeObject=subTypeObject.getJSONObject("type");

            String typeIDVal=typeObject.getString("typeID");
            String typeNameVal=typeObject.getString("typeName");



            displayQItemResults.add("Item ID:    "+itemID+"\n"+"Item Note:    "+itemNote+"\n"+"Item SubType Name:    "+subtypeNames+"\n"+"Item Description:    "+
                    descript+"\n"+"SubType ID:    "+sybTypeID+"\n"+"Item Type ID:   "+typeIDVal+"\n"+"Type Name:    "+typeNameVal);
        }


    }

    catch (JSONException e) {
        System.out.println();
        e.printStackTrace();
    }


    return displayQItemResults;
}

    public ArrayList ConvertToJSONDeployedItems(String JsonString)
    {

        displayDeployedDetails = new ArrayList();
        try
        {
            //conver string to JSON object
            JSONArray deployedData= new JSONArray(JsonString);

            int indexedDeployedItems= deployedData.length();
            for(int j=0; j<indexedDeployedItems; j++)
            {
                JSONObject deployedItemObjectStructure=deployedData.getJSONObject(j);

                String deployedLocationProperty=deployedItemObjectStructure.getString("location");
                String deployedDateProperty=deployedItemObjectStructure.getString("date");
                String deployedNote=deployedItemObjectStructure.getString("notes");


                //project object
                JSONObject ProjectObjectVar=deployedItemObjectStructure.getJSONObject("project");
                String projectNamePropertyVal=ProjectObjectVar.getString("name");

                //itemObject
                JSONObject itemObjectPropertyVar=deployedItemObjectStructure.getJSONObject("item");
                String itemDeployedIDVar=itemObjectPropertyVar.getString("itemID");
                String itemDeployedNote=itemObjectPropertyVar.getString("itemNote");
                //subtype object
                JSONObject subtypeDeployedObject=itemObjectPropertyVar.getJSONObject("subType");
                String subTypeIDVar=subtypeDeployedObject.getString("subTypeID");


                //type object
                JSONObject deployedTypeObject=subtypeDeployedObject.getJSONObject("type");
                String typeDeployedPropertyID=deployedTypeObject.getString("typeID");
                String typenameDeployedProperty=deployedTypeObject.getString("typeName");


                /*
                String key;
                Iterator<String> thing =  itemobjectVar.keys();
                while(thing.hasNext())
                {
                    key = thing.next();
                    System.out.println(key);
                }
                */

                displayDeployedDetails.add("TypeID:    "+typeDeployedPropertyID+"\n"+ "Type Name:  "+typenameDeployedProperty+"\n"+"Item Note:  "+itemDeployedNote+"\n"+"ItemID:  "+itemDeployedIDVar+"\n"+"SubTypeID:  "+
                        subTypeIDVar+"\n"+"Person Name:  "+projectNamePropertyVal+"\n"+"Location:  "+deployedLocationProperty+"\n"+"Date:    "+deployedDateProperty+"\n"+"Deployment Note:   "+deployedNote);
                System.out.println("out");
            }

        }

        catch (JSONException e) {
            System.out.println();
            e.printStackTrace();
        }
        return displayDeployedDetails;
    }

    public ArrayList ConvertToJSONArrayItemsIssued(String JsonString)
    {

        displayQItemIssuedResults = new ArrayList();
        try
        {
            //conver string to JSON object
            JSONArray queryData= new JSONArray(JsonString);

            int indexedItemsIssuedData= queryData.length();
            for(int j=0; j<indexedItemsIssuedData; j++)
            {
                JSONObject itemIssuedObject=queryData.getJSONObject(j);

                String itemID=itemIssuedObject.getString("itemIssuedID");
                //grab the object artists
                JSONObject personObject=itemIssuedObject.getJSONObject("person");
                String personIDvar=personObject.getString("personID");
                String nameVar=personObject.getString("name");
                String phoneNumber=personObject.getString("phoneNumber");
                String email=personObject.getString("email");

                JSONObject itemobjectVar =itemIssuedObject.getJSONObject("item");
                String itemIssueIDPropery=itemobjectVar.getString("itemID");
                String itemIssueNoteIPropery=itemobjectVar.getString("itemNote");
                /*
                String key;
                Iterator<String> thing =  itemobjectVar.keys();
                while(thing.hasNext())
                {
                    key = thing.next();
                    System.out.println(key);

                }

                */
                    JSONObject itemIssueSubTypeObjectVar = itemobjectVar.getJSONObject("subType");

                    String sybTypeIssuedID = itemIssueSubTypeObjectVar.getString("subTypeID");
                    String subtypeIssuedNames = itemIssueSubTypeObjectVar.getString("subTypeName");
                    String descriptIssued = itemIssueSubTypeObjectVar.getString("description");

                    JSONObject typeIssuedTypeObject = itemIssueSubTypeObjectVar.getJSONObject("type");
                    String typeIssuedID = typeIssuedTypeObject.getString("typeID");
                    String TypeNameIssued = typeIssuedTypeObject.getString("typeName");


                    displayQItemIssuedResults.add("Person ID:    "+personIDvar + "\n" +"Person Name:    "+nameVar + "\n" + "Person Phone Number:    "+phoneNumber + "\n" + "Person Email:    "+email + "\n" + "Item Issue ID:    "+itemIssueIDPropery + "\n"
                            + "Item Issue Note:    "+itemIssueNoteIPropery + "\n" + "SubType ID:    "+sybTypeIssuedID +"\n"+"SubType Name:    "+ subtypeIssuedNames + "\n" + "Description:    "+descriptIssued + "\n" + "Type Issue ID:    "+  typeIssuedID + "\n" + "Type Name:   "+TypeNameIssued + "\n" + "Item ID:    "+itemID);
                    System.out.println("out");

            }

        }

        catch (JSONException e) {
            System.out.println();
            e.printStackTrace();
        }


        return displayQItemIssuedResults;
    }


    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!HTTPJSON Parser that streams/reads in the data from the  HTTP Url connection call!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public String HTTPGetJSON(String apiCommand)
    {

        try
        {

            // Create API URL
            String api = base_Url + apiCommand;

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
                JSONString = builder.toString();
            }

        } catch (Exception e) {
            System.out.println();

            e.printStackTrace();
        }

        return JSONString;
    }


}
