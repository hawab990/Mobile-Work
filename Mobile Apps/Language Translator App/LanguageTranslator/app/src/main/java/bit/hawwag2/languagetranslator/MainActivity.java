package bit.hawwag2.languagetranslator;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.skyfishjy.library.RippleBackground;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
//Global Fields
    ImageView ivTextToSpeech;
    //Input language to translate
    EditText inputLanguage;
    //Variable holding input tex
    String inputStringHolder;
    //reassigning of inputStringHolder and passed in convetJSONString function
    String inputLanguageTexTresult;
    //Response from webservice for debugging purposes
    private int responseCode;
    //Initialised variable holding JSONSTRing from parser
    private String displayTranslations=null;
    private RippleBackground rippleBackground;
    public boolean isTextToSpeech=true;
    private TextToSpeech tts;
    private TextView tvSourceLanguage;
    public  String wordDefinition;
    private LangInfoDialogue LangInfoDialogue;
    public Spinner sourceLangSpinner;
    public Spinner destinationLangSpinner;
    private String destinationSelection;
    private String convertedSourceLang;
    private String convertedDestination;
    private ImageView ivDefineTranslation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setting up the controllers and their interfaces
        setUpControllers();
        //Sets up the custom spinner populated with lang images and text upon initialisation.
       setUpOutPutLangArray();

    }//end of oncreate

    public void setUpControllers()
    {
        //Creating ripplebackground animation
        rippleBackground=(RippleBackground)findViewById(R.id.content);

        //Referencing EditText
        inputLanguage=(EditText) findViewById(R.id.etInputLanguage);

        //Assigning Variable to user input
        inputLanguageTexTresult= inputLanguageText();

        //Image button to translate text.
        //Reference the click to translate image view.
        ImageView ivClickTranslate=(ImageView)findViewById(R.id.ivClickTranslate);
        //set and on click handler to the image view
        ivClickTranslate.setOnClickListener(new clickTranslatehandler());

        //Reference the image view
        ivTextToSpeech=(ImageView)findViewById(R.id.ivTextToSpeech);
        //set an click handler to the image view.
        ivTextToSpeech.setOnClickListener(new speechOnClickHandler());

        //Set an onclick handler for imageButton to define translate text

        ivDefineTranslation=(ImageView) findViewById(R.id.ivDefineWord);
        //setting listeners for click action
        ivDefineTranslation.setOnClickListener(new definitionIBOnclickHanlder());

        //Casting the reference iteger of the icon to the imageview object.
        ImageView ivBtnSelectKeyBoard=(ImageView) findViewById(R.id.ivChooseKeyboard);
        //Event setter method for image button
        ivBtnSelectKeyBoard.setOnClickListener(new keyBoardOnClickHandler() );

        //Start the ripple affect descendant of the RippleBackground class  upon activity initialisation
        rippleBackground.startRippleAnimation();

        //Applying constrains by disabling both text to speech and define translation button upon initialization.
        ivTextToSpeech.setEnabled(false);
        ivDefineTranslation.setEnabled(false);
    }

    //Click listener inner class  for onclick  action
    private class keyBoardOnClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Making an instance of the inputm method manager and c casting its getsystemservice call to an Input Method Manager Object
            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.showInputMethodPicker();
        }
    }
    //A method that will be called in the oncreate to house the array/list for the spinner selection.

    public void setUpOutPutLangArray()
    {
        //An Array list of object CustomoutPutDropDown
        ArrayList<CustomOutPutDropDown> destinationList=new ArrayList<>();
        //populating the array with country flags and names.
        destinationList.add(new CustomOutPutDropDown(R.drawable.ukflagicon,"English"));
        destinationList.add(new CustomOutPutDropDown(R.drawable.arabicflagicon,"Arabic"));
        destinationList.add(new CustomOutPutDropDown(R.drawable.germanflagicon,"German"));
        destinationList.add(new CustomOutPutDropDown(R.drawable.italianflagicon,"Italian"));
        destinationList.add(new CustomOutPutDropDown(R.drawable.frenchflagicon,"French"));

        ArrayList<CustomOutPutDropDown> souceList=new ArrayList<>();
        //populating the array with country flags and names.
        souceList.add(new CustomOutPutDropDown(R.drawable.ukflagicon,"English"));
        souceList.add(new CustomOutPutDropDown(R.drawable.arabicflagicon,"Arabic"));
        souceList.add(new CustomOutPutDropDown(R.drawable.germanflagicon,"German"));
        souceList.add(new CustomOutPutDropDown(R.drawable.italianflagicon,"Italian"));
        souceList.add(new CustomOutPutDropDown(R.drawable.frenchflagicon,"French"));

        //Reference the spinner with its layout.
        destinationLangSpinner =(Spinner)findViewById(R.id.spnnerDestinationLanguage);
        //Make the spinner adapter and set the custom layout.
        SpinnerAdapter adapter=new SpinnerAdapter(this,
                R.layout.custom_select_lang_list,R.id.ivLangFlag,destinationList);
        //Populate the contents of the spinner  with the adapter.
        destinationLangSpinner.setAdapter(adapter);
        //set an onclickhandler when a selection is made in the spinner,
        destinationLangSpinner.setOnItemSelectedListener(new setOnclickLanguageSelection());

        //Make the spinner adapter and set the custom layout.
        SpinnerAdapter adapter1=new SpinnerAdapter(this,
                R.layout.custom_select_lang_list,R.id.ivLangFlag,souceList);

        //Make an identical spinner for the source language referencing t
        sourceLangSpinner =(Spinner)findViewById(R.id.spnnerSourceLanguage);

        //set an adapter to the spinner to fill its contents
        sourceLangSpinner.setAdapter(adapter1);

        //Item selection listener Event setter method for image view button
        sourceLangSpinner.setOnItemSelectedListener(new setOnClickSourceLangHandler());


    }

    //Inner class that  converts the content of the selected spinner item into an translatable IANA lang code
    private class setOnClickSourceLangHandler implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String rawSourceLang= sourceLangSpinner.getItemAtPosition(position).toString();
                switch (rawSourceLang){
                    case "2130837609English":
                        convertedSourceLang ="en";
                        break;
                    case "2130837590French":
                        convertedSourceLang ="fr";
                        break;
                    case "2130837591German":
                        convertedSourceLang ="de";
                        break;
                    case "2130837592Italian":
                        convertedSourceLang ="it";
                        break;
                    case "2130837587Arabic":
                        convertedSourceLang ="ar";
                }

        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    //Inner class that converts the content of the selected spinner item into an translatable IANA destination lang code
    public class setOnclickLanguageSelection implements AdapterView.OnItemSelectedListener
    {

        @Override
        //Method that accepts a Raw Language and converts it to its general presentation.
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //Gets the items raw selection at position x
            String rawDestinationLang= destinationLangSpinner.getItemAtPosition(position).toString();
            //Converts the raw selection to a recognized API lang Code
             destinationSelection = ConvertDestination(rawDestinationLang);

        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    // convert the rawDestination spinner selection into IanaCode destination
   final public String ConvertDestination(String rawDestinationlang)
    {
        //switch on the selected item selection
        switch (rawDestinationlang){
            case "2130837609English":
                //Replace the string and assign it the global variable.
                convertedDestination ="en";
                break;
            case "2130837590French":
                convertedDestination ="fr";
                break;
            case "2130837591German":
                convertedDestination ="de";
                break;
            case "2130837592Italian":
                convertedDestination ="it";
                break;
            case "2130837587Arabic":
                convertedDestination ="ar";
                Toast.makeText(MainActivity.this, "This Language does not support sound",Toast.LENGTH_LONG).show();
        }

        //Return the converted Iana code eg ar.
        return convertedDestination;

    }

    //Class click handler to execute api async thread.
    public class definitionIBOnclickHanlder implements View.OnClickListener
    {
        //Event setter method of the button
        @Override
        public void onClick(View v) {
            CallbackTask OxfordApiThread= new CallbackTask();
            OxfordApiThread.execute(dictionaryEntries());
        }
    }

    //Inner class that will listen to clicks of Speech image view
    private class speechOnClickHandler implements View.OnClickListener {
        //Get the text from the textview and assign it to To Speak
        @Override
        public void onClick(View v) {
            String toSpeak = tvSourceLanguage.getText().toString();
            //If is text to speech it true, run the api wrapping and and enable text to speech button
                if(isTextToSpeech=true)
                {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ttsGreater21(toSpeak);
                        ivTextToSpeech.setEnabled(true);

                    }
                    else {ttsUnder20(toSpeak);}
                    isTextToSpeech=false;
                }


        }
    }

    //Api wrapping and method that will accept a string call the speak method to synthesize sound
    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        //calling the key param utterence service
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    //More Api Wrapping for for lollipop and up
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId=this.hashCode() + "";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    // struct for the dictionary entry variables.
    private String dictionaryEntries() {
        final String language =convertedSourceLang;
        final String word = wordDefinition;
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }

    //Asynthread for executing the thread. Takes a string, uses and integer and a string.
    private class CallbackTask extends AsyncTask<String, Integer, String> {
        private StringBuilder JsonStringBuilder;
        private  String wordInfoJSONString;

        //Asynchronous thread of http service request
        @Override
        //Asynchronous thread for url request inject and JSON string response.
        protected String doInBackground(String... params) {

            //TODO: replace with your own app id and app key
            final String app_id = "5d496948";
            final String app_key = "6e232527efd9f9506ac34e722cf01089";
            try {
                URL url = new URL(params[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept","application/json");
                urlConnection.setRequestProperty("app_id",app_id);
                urlConnection.setRequestProperty("app_key",app_key);

                //Reponse status from the service
                responseCode=urlConnection.getResponseCode();
                // read the output from the server
                //String parser
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                //build the string if line isnt empty and split it into new lines.
                String line = null;
                while ((line = reader.readLine()) != null) {
                     JsonStringBuilder=stringBuilder.append(line+"\n");
                }

                //Stringify the JSON String further.
                 wordInfoJSONString = JsonStringBuilder.toString();
            }
            //Exception handling
            catch (Exception e) {
                System.out.println(responseCode+"/n"+"Something went wrong with JSON Parsing");
                e.printStackTrace();
                return e.toString();
            }
            //OutPut response string
            //Return the compiled JSONstring
            return wordInfoJSONString;
        }

        //Bundling the data to be passed to the custom dialog view
        protected void onPostExecute(String fetchedString)
        {
            //create a bundle instance
            Bundle bundle= new Bundle();
            //passing the string object and its tag.
            bundle.putString("Word Definitions",convertDefToObject(fetchedString));
            //create a new instance of the dialog
            LangInfoDialogue =new LangInfoDialogue();
            //set the arguments
            LangInfoDialogue.setArguments(bundle);
            //Give a custom heading
            LangInfoDialogue.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
            //call the fragment manager
            LangInfoDialogue.show(getFragmentManager(),"Definition Dialogue");
        }

        //Class the converts the JSONString of definition into an object.
        public String convertDefToObject(String definitionObject)
        {
            //class variables for displaying object information.
            String lexicalCategory;
            String lexicalText;
            //Creates an array of the top artists to be displayed
            ArrayList<JSONArray> displayDefinitions = new ArrayList();

            if(wordInfoJSONString==null)
            {
                Toast.makeText(MainActivity.this, "This Language does not support sound,",Toast.LENGTH_LONG).show();
                return null;
            }
            try {
                //convert string to JSON object
                JSONObject wordData= new JSONObject(wordInfoJSONString);

                //grab the object artists
                JSONArray resultsObject=wordData.getJSONArray("results");

                //Grab the first object(0) from results
                JSONObject resultsEntry=resultsObject.getJSONObject(Integer.parseInt("0"));

                //Get the array of the object array of objects type 0
                JSONArray lexicalArray=resultsEntry.getJSONArray("lexicalEntries");

                //Get the first array Lexicalentries which is entries
                JSONObject lexcialEntry=lexicalArray.getJSONObject(Integer.parseInt("0"));
                /*
                 lexicalCategory=lexcialEntry.getString("lexicalCategory");
                 lexicalText=lexcialEntry.getString("language");

*/
                //grab the entries object array
                JSONArray entriesArray=lexcialEntry.getJSONArray("entries");

                //Get the first  array entries array which is 0
                JSONObject entriesFirstInstance=entriesArray.getJSONObject(Integer.parseInt("0"));

                //Get the get the array senses inside entriesFirstInstances object value 0
                JSONArray sensesArray=entriesFirstInstance.getJSONArray("senses");
                int numberOfSenses=sensesArray.length();

                for(int i=0;i<numberOfSenses; i++)
                {
                    JSONObject currentEntry=sensesArray.getJSONObject(i);

                    JSONArray DefinitionArray=currentEntry.getJSONArray("definitions");

                    displayDefinitions.add(DefinitionArray);
                }

            }catch (Exception e)
            {
                System.out.println("Something went wrong creating  Json Object");
                e.printStackTrace();

                System.out.println(definitionObject);

            }
            return String.valueOf(displayDefinitions);


        }

    }

    //Async task for translation api
    public class AsyncAPIShowRawJSON extends AsyncTask<String, Void,String>
    {

        @Override
        protected String doInBackground(String... params) {
            String rawJson=convertToJSONString(inputStringHolder,convertedSourceLang, destinationSelection);
            return rawJson;
        }
        //Synchronous thread of accepting the rawJSON converting it to an object.
        protected  void onPostExecute(String fetchedString)
        {
            //Fetched string from jsonString parson used as a parameter to be converted into an object
            //and displayed in the textview
            tvSourceLanguage=(TextView)findViewById(R.id.tvResult);
            tvSourceLanguage.setText(convertoJSONOBjects(fetchedString));


            //Convert the JsonString and assign it the variable
              wordDefinition= convertoJSONOBjects(fetchedString);

                //Apply regex expression matching for constraint handling. Match for only a single english word.
                String pattern="^\\b[a-zA-Z0-9_]+\\b$";
                //Creat the pattern object
                Pattern r=Pattern.compile(pattern);
                //Create the matcher object
                Matcher m=r.matcher(wordDefinition);
                if (m.matches())
                {
                    //if its a single word enable  the translation button
                    ivDefineTranslation.setEnabled(true);
                }
                else{
                    Toast.makeText(MainActivity.this,"Sentences are not supported by definition",Toast.LENGTH_SHORT).show();
                    ivDefineTranslation.setEnabled(false);
                }



            //This call is located here to reduce the latency of initialization of text to speech!!!
            tts=new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener(){

                @Override
                public void onInit(int status) {
                    Locale loc= new Locale("ar");

                    if (status != TextToSpeech.ERROR)

                    {
                      switch (convertedDestination){
                            case "en":
                                tts.setLanguage(Locale.ENGLISH);
                                break;
                            case "it":
                                tts.setLanguage(Locale.ITALIAN);
                                break;
                            case "fr":
                                tts.setLanguage(Locale.FRENCH);
                                break;
                          case "de":
                              tts.setLanguage(Locale.GERMAN);
                              break;
                            case "ar":
                                Toast.makeText(MainActivity.this, "This Language does not support sound,",Toast.LENGTH_LONG).show();
                                tts.setLanguage(loc);
                        }
                    }
                }
            });

        }
    }

    //inner class to handler translation
    private class clickTranslatehandler implements View.OnClickListener {
        @Override
        //event method
        public void onClick(View v) {
            {
                //Run the thread and execute the chaining input language text method.
                AsyncAPIShowRawJSON ApiThread= new AsyncAPIShowRawJSON();
                ApiThread.execute(inputLanguageText());
                ivTextToSpeech.setEnabled(true);

            }
        }
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
            Toast.makeText(MainActivity.this, "Please enter word or sentence",Toast.LENGTH_LONG).show();
        }
        //Return the string
        return inputStringHolder;
    }


    //Method that will accept a user input text, a string source code and a destination code
    public String convertToJSONString(String userInput, String sourceLangInputs, String destinationLanguageInput)
    {


        // Declare here so its not local to the try block
        String JSONString=null;
        //the input string to be inserted into the URl


        try {

            //insert input Variable into  url String
            String urlString="https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170604T034745Z.a586a2b1d8cd55b5.a1b923e173c0c47cda2be3dd00a09380bff248af" +
                    "&lang="+sourceLangInputs+"-"+destinationLanguageInput+
                    "&text="+userInput+
                    "&format=html&"+
                    "&options=1&HTTP/1.1";

            System.out.println(urlString);
            //remove whitespace when making http request
            String modifiedURL=urlString.replaceAll(" ","%20");

            //Convert the urlString into an URL Object
            URL URLObject=new URL(modifiedURL);

            //Create a httlURL Connection object via openConnection command of URLObject
            HttpURLConnection connection= (HttpURLConnection) URLObject.openConnection();

            //send the url
            connection.connect();

            //if it doesnt connect return 200, you dont have data...
            responseCode=connection.getResponseCode();

            //Get an inputstream from the httpurlconnection object and set up a buffereader
            InputStream is= connection.getInputStream();

            //creating a bufferreader object to pass in the inputStreamReader
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(is,HTTP.UTF_8));

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
            if(JSONString=="null")
            {
                Toast.makeText(MainActivity.this,"Please put text",Toast.LENGTH_LONG);
            }


        }  catch (Exception e)
        {
            System.out.println(responseCode);

        }
        //Return the response jsonString
        return JSONString;
    }

    //Convert raw translation JSON response and extra the translation
    public String convertoJSONOBjects(String rawJson)
    {
        String translation;
        //Creates an array of the top artists to be displayed
        try {

            //convert string to JSON object
            JSONObject languageData= new JSONObject(rawJson);

            //Get the value of the object array text;
             translation=languageData.getString("text");

            //Removing Brackets from JSON response(Array)
            String rmBrackets=translation.replaceAll("[^\\u0621-\\u064A\\u0660-\\u0669 0-9A-Za-z ',-]","");

            //Variable to display translation
            displayTranslations=rmBrackets;
        }catch (Exception e)
        {
            System.out.println("|||||||||||||||Something went wrong when creating Json Object.||||||||||||||");
            e.printStackTrace();
        }
        //Returned a refined version of the translation.
        return displayTranslations;
    }

}
