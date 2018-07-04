package op_bit.ayaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViewDiagnosisInformation extends AppCompatActivity {
    TextView diagnosis;
    TextView dateDiagnosed;
    TextView treatment;
    TextView Remission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_information);

        setUpListners();

        setUpTextviews();

        getData();

    }//end Oncreate
    private void setUpTextviews()
    {
        diagnosis = (TextView)findViewById(R.id.tv_diDiagnosis);
        dateDiagnosed = (TextView)findViewById(R.id.tv_diDiagnosisDate);
        treatment = (TextView)findViewById(R.id.tv_diTreatment);
        Remission = (TextView)findViewById(R.id.tv_diRemision);
    }

    private void setUpListners()
    {
        Button editButton = (Button) findViewById(R.id.btn_Edit);

        editButton.setOnClickListener(new editButton());

        Button backButton = (Button) findViewById(R.id.btn_Back);

        backButton.setOnClickListener(new backButton());
        Button menuButton = (Button) findViewById(R.id.btn_menu);

        menuButton.setOnClickListener(new homeMenuClick());

        Button home = (Button)findViewById(R.id.btn_home);

        home.setOnClickListener(new homeMenuClick());
    }

    @Override
    protected void onResume()
    {
        getData();
        super.onResume();
    }

    public class homeMenuClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent i=new Intent();
            switch(view.getId()){
                case R.id.btn_home:
                    i=new Intent(ViewDiagnosisInformation.this, MainActivity.class);//change when we get a home activity
                    break;
                case R.id.btn_menu:
                    i=new Intent(ViewDiagnosisInformation.this, MainActivity.class);
                    break;
            }
            startActivity(i);
        }
    }

    private void getData()
    {
        XmlParser xmlParser = new XmlParser(getApplicationContext());


        ViewDiagnosisInformationData diagnosisInfo = xmlParser.readDiagnosisInfo();

        diagnosis.setText(""+diagnosisInfo.Diagnosis);

        dateDiagnosed.setText(""+diagnosisInfo.DateDiagnosed);

        treatment.setText(""+diagnosisInfo.Treatment);

        Remission.setText(""+diagnosisInfo.Remission);
    }

    public class editButton implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(ViewDiagnosisInformation.this,EditDiagnosisInformation.class);

            startActivity(intent);
        }
    }

    public class backButton implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {

            finish();
        }
    }

}
