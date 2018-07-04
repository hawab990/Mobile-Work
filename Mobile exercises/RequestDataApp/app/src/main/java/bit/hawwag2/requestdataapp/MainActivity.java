package bit.hawwag2.requestdataapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnChangeColour= (Button) findViewById(R.id.btnChangeColour);
        btnChangeColour.setOnClickListener(new btnClickHandler());
    }

    public class btnClickHandler implements View.OnClickListener
    {


        @Override
        public void onClick(View v) {
            Intent ChangeActivityIntent = new Intent(MainActivity.this, Settings.class);
            startActivityForResult(ChangeActivityIntent, 0);
        }
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        TextView txtViewDisplayColour= (TextView) findViewById(R.id.textViewDisplayResult);
        txtViewDisplayColour.toString();
        if ((requestCode==0))
        {
            if(resultCode==Activity.RESULT_OK)
            {
                // the two activities have to agree on the key
                //Pass the integer from Second Activity
                int  result = data.getIntExtra("requestedResult",0);
                txtViewDisplayColour.setTextColor(result);

            }


    }
}}
