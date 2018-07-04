package bit.hawwag2.activityapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PageC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtViewFeedback = (TextView) findViewById(R.id.txtViewFeedback);
        txtViewFeedback.setText("This is Page C");
        Button btnChangeDisplay = (Button) findViewById(R.id.btnChangeActivity);
        btnChangeActivityHandler handler = new btnChangeActivityHandler();
        btnChangeDisplay.setOnClickListener(handler);


    }



    public class btnChangeActivityHandler implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            Uri goSeeMickey= Uri.parse("http://www.disney.com");
            Intent mickeyIntent =new Intent(Intent.ACTION_VIEW, goSeeMickey);
            startActivity(mickeyIntent);
        }
    }

}
