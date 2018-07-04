package bit.hawwag2.musicschoolapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.security.PublicKey;

import static android.R.attr.checked;
import static android.R.attr.text;

public class MainActivity extends AppCompatActivity {
    //Global variables
    Spinner spinner;
    TextView txtViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Reference to the spinner control
        Spinner monthPicker=(Spinner) findViewById(R.id.monthSpinner);
        //Creating an arrayAdapter using the string array and a defualt spinner layout
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(MainActivity.this,R.array.Months_Array,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        txtViewDisplay = (TextView) findViewById(R.id.textViewResult);

        // Apply the adapter to the spinner
        monthPicker.setAdapter(adapter);


       addListenerOnButton();


    }


    public void addListenerOnButton()
    {

        //Referencing the radioGroup
        final RadioGroup instrumentGroup=(RadioGroup) findViewById(R.id.rdoGroup);
        Button btnDisplay=(Button) findViewById(R.id.btnDisplay);



        //Binding the onclicklistener using an anoynomous function
        btnDisplay.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //re-referencing spinner to resolve scope issues.
                Spinner spinnerMonthList=(Spinner) findViewById(R.id.monthSpinner);
                // get selected radio button from radioGroup
                int selectedId = instrumentGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton ChosenButton = (RadioButton) findViewById(selectedId);
                //Check for the ID representation and write feedback to a textview accordingly
                if (ChosenButton == findViewById(R.id.radio_Accordian)) {
                    String ChosenMonth=(String) spinnerMonthList.getSelectedItem();
                    String feedbackString = "You have enrolled for Accordian Lessons on "+ChosenMonth;
                    txtViewDisplay.setText(feedbackString);
                }
                // find the radiobutton by returned id
                //Check for the ID representation and write feedback to a textview accordingly
                if (ChosenButton == findViewById(R.id.radio_Bassoon)) {
                    String ChosenMonth=(String) spinnerMonthList.getSelectedItem();
                    String feedbackString = "You have enrolled for Bassoon Lessons on "+ChosenMonth;
                    txtViewDisplay.setText(feedbackString);
                }
                // find the radiobutton by returned id
                //Check for the ID representation and write feedback to a textview accordingly
                if (ChosenButton == findViewById(R.id.radio_Cello)) {
                    String ChosenMonth=(String) spinnerMonthList.getSelectedItem();
                    String feedbackString = "You have enrolled for Cello Lessons on "+ChosenMonth;
                    txtViewDisplay.setText(feedbackString);
                }
            }
        });
    }
}





















