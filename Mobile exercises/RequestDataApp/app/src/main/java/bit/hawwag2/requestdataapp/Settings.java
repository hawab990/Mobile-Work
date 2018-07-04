package bit.hawwag2.requestdataapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView RedText=(TextView) findViewById(R.id.textViewRed);
        //Saving Hex representation of color
        int Hex= RedText.getCurrentTextColor();
        //Making the intent
        Intent returnIntent= new Intent();


        returnIntent.putExtra("requestedResult", Hex);
        setResult(Activity.RESULT_OK, returnIntent);
        //popping the activity stack and passes control to mainActivity
        finish();


    }
}
