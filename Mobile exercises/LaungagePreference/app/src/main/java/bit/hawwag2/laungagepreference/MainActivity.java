package bit.hawwag2.laungagepreference;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioButton checkedColorChoice;
    RadioButton  checkedButton;
    //Declaring Class fields
    SharedPreferences prefs;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calling the SharedPreference Method
        prefs = getSharedPreferences("ShredFreds", MODE_PRIVATE);
        //Initialize prefs Editor
        prefEditor = prefs.edit();
        //Getting references to button
        Button btnConfirmLanguage = (Button) findViewById(R.id.btnSetLang);
        btnConfirmLanguage.setOnClickListener(new setLanguangeClickHandler());




//Returns null if the provided key=value pair hasnt been set for the color and Language Preference

        //Fetch the stored Language preference
        String languagePreference = prefs.getString("language", null);
        //Fetch the stored color preference
        String colorPreference = prefs.getString("Color", null);


        //Returns null if the provided key=value pair hasnt been set for the color and Language Preference

        if ((languagePreference != null)||(colorPreference != null))
        {
            //Call our getGreerting method to grab the greeting
            String greetingChosenLanguage = getGreeting(languagePreference);
            //Write it to the textView
            TextView txtViewYourChosenLanguage = (TextView) findViewById(R.id.tvChosenLanguage);
            //setting the stored chosen language to the textView.
            txtViewYourChosenLanguage.setText(greetingChosenLanguage);

            //Keep last updated color change
            refreshColor(colorPreference);



        }


    }

    public class setLanguangeClickHandler implements View.OnClickListener
    {


        @Override
        public void onClick(View view) {

            //Get references to the radio button
            RadioGroup rdoGrpLanguages=(RadioGroup) findViewById(R.id.rdoGroupLanguage);
            RadioGroup rdoGrpColor=(RadioGroup) findViewById(R.id.rdoChangeColor);


            //Returns the identifier of the selected radio button in this groups. Upon empty selection, the returned value is -1.
            rdoGrpColor.getCheckedRadioButtonId();
            rdoGrpLanguages.getCheckedRadioButtonId();


            int checkedID= rdoGrpLanguages.getCheckedRadioButtonId();
            int checkedColorID=rdoGrpColor.getCheckedRadioButtonId();


            checkedColorChoice=(RadioButton) findViewById(checkedColorID);
            checkedButton=(RadioButton) findViewById(checkedID);


            String checkedLanguage=checkedButton.getText().toString();
            //Retriecve Color
            String  colorChoice=checkedColorChoice.getText().toString();

            //Storing  the String Color and Language
            prefEditor.putString("Color",colorChoice);
            prefEditor.putString("language", checkedLanguage);
            prefEditor.commit();
            TextView txtViewYourChosenLanguage=(TextView) findViewById(R.id.tvChosenLanguage);
            txtViewYourChosenLanguage.setText(getGreeting(checkedLanguage));
            //updates the color of the textview Real time when the button is clicked.
             refreshColor(colorChoice);
        }

    }

private String getGreeting(String language)
{
    //Switch case that stores the greeting depending on which language chosen
    String greeting="";

    switch (language)
    {
        case"French":
            greeting="Bonjour Le Monde";
            break;

        case "German":
            greeting="Hallo Welt";
            break;


        case "Spanish":
            greeting="Hola Mundo";
            break;

    }
    return greeting;

}

    private void refreshColor(String ColorText)
    {
        TextView txtViewYourChosenLanguage=(TextView) findViewById(R.id.tvChosenLanguage);
        switch(ColorText)
        {
            case "Blue":
                txtViewYourChosenLanguage.setTextColor(Color.BLUE);
                break;

            case "Green":
                txtViewYourChosenLanguage.setTextColor(Color.GREEN);
                break;

            case "Red":
                txtViewYourChosenLanguage.setTextColor(Color.RED);

                break;

        }

    }

}
