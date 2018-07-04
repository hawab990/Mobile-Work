package bit.hawwag2.activityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pageB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtViewFeedback = (TextView) findViewById(R.id.txtViewFeedback);
        txtViewFeedback.setText("This is Page B");
        Button btnChangeDisplay = (Button) findViewById(R.id.btnChangeActivity);
        btnChangeActivityHandler handler = new btnChangeActivityHandler();
        btnChangeDisplay.setOnClickListener(handler);

    }


    public class btnChangeActivityHandler implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            Intent intent= new Intent(pageB.this, PageC.class);
            startActivity(intent);
        }
    }

}
