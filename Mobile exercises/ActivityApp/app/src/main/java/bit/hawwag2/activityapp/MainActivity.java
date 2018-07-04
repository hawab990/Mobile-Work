package bit.hawwag2.activityapp;

import android.content.Intent;
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

        //Referencing the buttons
        final Button btnChangeActivity = (Button) findViewById(R.id.btnChangeActivity);
        //Referencing the textView
        TextView txtViewFeedback = (TextView) findViewById(R.id.txtViewFeedback);
        txtViewFeedback.setText(" This is the main Activity");
        //Creating an instance of the button handler
        btnChangeActivityHandler handler = new btnChangeActivityHandler();
        //Binding the button to the onClickListner
        btnChangeActivity.setOnClickListener(handler);
    }//End Oncreat

    //Using Inner classes instead of anonymous functions
    public class btnChangeActivityHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, pageA.class);
            startActivity(intent);


        }
    }
}



