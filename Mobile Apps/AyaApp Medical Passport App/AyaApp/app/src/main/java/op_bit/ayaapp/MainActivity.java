package op_bit.ayaapp;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button healthInfoButton = (Button)findViewById(R.id.btn_HealthInformation);
        Button appointmentsButton = (Button)findViewById(R.id.btn_Appoinments);
        Button diagnosisButton = (Button)findViewById(R.id.btn_DiagnosisInformation);
        Button keyWorkersButton = (Button)findViewById(R.id.btn_AYAKeyWorkers);
        Button helpfulTipsButton = (Button)findViewById(R.id.btn_HelpfulTips);
        Button longTermEffectsButton = (Button)findViewById(R.id.btn_LongTermEffects);
        Button medicationButton = (Button)findViewById(R.id.btn_Medication);
        Button websitesButton = (Button)findViewById(R.id.btn_Websites);
        Button homeButton = (Button)findViewById(R.id.btn_home);

        healthInfoButton.setOnClickListener(new buttonListener());
        appointmentsButton.setOnClickListener(new buttonListener());
        diagnosisButton.setOnClickListener(new buttonListener());
        keyWorkersButton.setOnClickListener(new buttonListener());
        helpfulTipsButton.setOnClickListener(new buttonListener());
        longTermEffectsButton.setOnClickListener(new buttonListener());
        medicationButton.setOnClickListener(new buttonListener());
        websitesButton.setOnClickListener(new buttonListener());
        homeButton.setOnClickListener(new buttonListener());

    }

    public class buttonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            startIntent(view);
        }
    }


    public void startIntent(View v){
        Intent intent = new Intent();

        switch(v.getId()){
            case R.id.btn_DiagnosisInformation:
                intent = new Intent(MainActivity.this,ViewDiagnosisInformation.class);
                break;
            case R.id.btn_HealthInformation:
                intent = new Intent(MainActivity.this,ViewHealthInformation.class);
                break;
            case R.id.btn_Appoinments:
                intent = new Intent(MainActivity.this,Appointments.class);
                break;
            case R.id.btn_AYAKeyWorkers:
                intent = new Intent(MainActivity.this,AYAKeyWorkers.class);
                break;
            case R.id.btn_HelpfulTips:
                intent = new Intent(MainActivity.this,HelpfulTips.class);
                break;
            case R.id.btn_LongTermEffects:
                intent = new Intent(MainActivity.this,PotentialLongTermEffects.class);
                break;
            case R.id.btn_Medication:
                intent = new Intent(MainActivity.this,Medication.class);
                break;
            case R.id.btn_Websites:
                intent = new Intent(MainActivity.this,Websites.class);
                break;
            case R.id.btn_home:
                intent = new Intent(MainActivity.this,Websites.class);   //change when we get a home activity
                break;

        }
        startActivity(intent);
    }
}
