package bit.hawwag2.languagetranslator;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

import static bit.hawwag2.languagetranslator.R.id.ivClickTOGOBACK;
import static bit.hawwag2.languagetranslator.R.id.ivTextToSpeech;

public class LangInfoDialogue extends DialogFragment {
    private View dialogueView;
    public CustomLangInfoListView[] definitionArray;
    TextToSpeech textToSpeech;
    String definition;
    String toSpeak;
    String rmBrackets;
    String AddedCapitals;


    //Need to pass OutPutLangName in onclick to extract Text from CustomArray Adapter.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //set a title for the dialoge fragment
        getDialog().setTitle("Click Speaker To Listen");

        initializeArray();
        // Inflate the layout for this fragment
        dialogueView=inflater.inflate(R.layout.activity_lang_info_diaglogue, container, false);

        //Initialising the array adapter
        LangDefinitionArrayAdapter LangDefinitionAdapter=null;

        //Creating the array adapter extended from it is the get view(instance snapshot) and the constructor that houses the customized adapter inner class.
        LangDefinitionAdapter=new LangDefinitionArrayAdapter(getActivity(),R.layout.custom_lang_info_listview, definitionArray);

        //Binding the listview to the adapter.
        ListView lvOutPutLangInfo=(ListView) dialogueView.findViewById(R.id.lvWordDefinitions);
        lvOutPutLangInfo.setAdapter(LangDefinitionAdapter);



        return dialogueView;
    }//End of oncreateView

    //Inner Worker Class Custom Adapter
    public class LangDefinitionArrayAdapter extends ArrayAdapter<CustomLangInfoListView>
    {
        //Custom Class constructor extends from the Array Adapter
        //needs an constructor
        public LangDefinitionArrayAdapter(Context context, int resource, CustomLangInfoListView[] objects) {
            super(context, resource, objects);
        }

        @Override
        //Returns  view filled up from the data in the itemsArray[position]
        public View getView(int position, View convertView, ViewGroup Container)
        {
            //Get inflator from the activity
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            //specify the layout its going to inflate
            View customView=inflater.inflate(R.layout.custom_lang_info_listview, Container,false);

            //fill the controls of the view with the information from the array
            //Using the findviewById method from the customview
            ImageView ivClickToSpeech=(ImageView)customView.findViewById(R.id.ivIconClickTTS);
            ImageView ivClickToGoBack=(ImageView)customView.findViewById(R.id.ivClickTOGOBACK);

            TextView tvDefinitions=(TextView)customView.findViewById(R.id.tvOutPutDefinitions);


            //Calling the extension of the adapter method, getItem is used to get the list from the array using its position
            //Position is passed in by the system and When filling the ListView, the system calls getView with 0, then with 1, then with 2, and so on.
            //Get the instances(CustomOutPutDropDown) current position  from the array.
            CustomLangInfoListView currentItem= getItem(position);

            //Use the instance data to initialize the view controls.
            //Setting the current image to the imageview
            ivClickToSpeech.setImageResource(currentItem.getTextToSpeech());
            ivClickToGoBack.setImageResource(currentItem.getGoBackImageButton());
            tvDefinitions.setText(currentItem.getLangDefinition());

            //Setting a separate (SetTag)  onclick handler for when the icon  is clicked.
            ivClickToSpeech.setTag(currentItem.getTextToSpeech());
            ivClickToGoBack.setTag(currentItem.getGoBackImageButton());

            ivClickToGoBack.setOnClickListener(new onclickGoBACKBUTTON());
            ivClickToSpeech.setOnClickListener(new onClickIconsHandler());

            //OutPutLangName.setText(currentItem.getRestaurantName());

            toSpeak = AddedCapitals;
            textToSpeech=new TextToSpeech(getContext(),new TextToSpeech.OnInitListener(){

                @Override
                public void onInit(int status) {

                    if (status != TextToSpeech.ERROR)

                    {
                        textToSpeech.setLanguage(Locale.UK);
                        textToSpeech.setPitch(1);
                    }

                }
            });

            return customView;
        }

        private class onclickGoBACKBUTTON implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                textToSpeech.stop();
            }
        }
    }
    //onclick to handle contents of the custom adapter and pass it to mainactivity
    public class onClickIconsHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ttsGreater21(toSpeak);
                }
                else {ttsUnder20(toSpeak);}

            }
        @SuppressWarnings("deprecation")
        private void ttsUnder20(String text) {
            HashMap<String, String> map = new HashMap<>();
            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private void ttsGreater21(String text) {
            String utteranceId=this.hashCode() + "";
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
        }
    }

    // Package, strip and  initialise the array.
    public void initializeArray()
    {
        Bundle bundle=getArguments();
        //Used to pass in the definitions from Main to fill list.
        definition=bundle.getString("Word Definitions");
        //Remove brackets, UTF spaces colons and a break line.
         rmBrackets=definition.replaceAll("[^\\u0621-\\u064A\\u0660-\\u0669 0-9A-Za-z '-]","\n");

        //Remove white spaces
        String rmRemovewhiteSpaces=rmBrackets.trim();

        //Declare a variable and initialize it to zero.
        int pos = 0;
        //capitalize at index 0
        boolean capitalize = true;
        //Pass in the refined string and rebuild it.
        StringBuilder sb = new StringBuilder(rmRemovewhiteSpaces);
        //Build look for break lines
        while (pos < sb.length()) {
            if (sb.charAt(pos) == '\n') {
                //Capitalize the first word of a new sentence.
                capitalize = true;
                //if its capitalize nor a white space and do the following
            } else if (capitalize && !Character.isWhitespace(sb.charAt(pos))) {

                //Uppercase it
                sb.setCharAt(pos, Character.toUpperCase(sb.charAt(pos)));
                capitalize = false;
            }
            //Increment  the positions.
            pos++;
        }
        //build the string and store it in a global
         AddedCapitals=sb.toString();

        //Initialize an array that holds objects for the custom list view.
        definitionArray =new CustomLangInfoListView[1];
        definitionArray[0]=new CustomLangInfoListView(R.drawable.clicktogoback,AddedCapitals,R.drawable.texttospeech);


    }






}



