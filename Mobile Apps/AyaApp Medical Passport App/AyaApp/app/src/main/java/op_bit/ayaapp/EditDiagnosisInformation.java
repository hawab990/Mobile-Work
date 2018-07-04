package op_bit.ayaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditDiagnosisInformation extends AppCompatActivity {

    EditText diagnosis;
    EditText dateDiagnosed;
    EditText treatment;
    EditText remission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diagnosis_information);

        SetUpListeners();

        setUpEditTexts();

        fillEditTexts();
    }

    private void SetUpListeners()
    {
        Button doneButton = (Button) findViewById(R.id.btn_Done);

        doneButton.setOnClickListener(new doneClick());

        Button backButton = (Button) findViewById(R.id.btn_Back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button menuButton = (Button) findViewById(R.id.btn_menu);

        menuButton.setOnClickListener(new menuClick());

        Button home = (Button)findViewById(R.id.btn_home);

        home.setOnClickListener(new homeClick());
    }
    private void setUpEditTexts()
    {

        diagnosis = (EditText) findViewById(R.id.edt_ediDiagnosis);
        dateDiagnosed = (EditText) findViewById(R.id.edt_ediDateDiagnosed);
        treatment = (EditText) findViewById(R.id.edt_ediTreatment);
        remission = (EditText) findViewById(R.id.edt_ediRemision);
    }
    private void fillEditTexts()
    {
        XmlParser xmlParser = new XmlParser(getApplicationContext());

        ViewDiagnosisInformationData diagnosisInfo = xmlParser.readTempDiagnosisInfo(getApplicationContext());

        diagnosis.setText(""+diagnosisInfo.Diagnosis);

        dateDiagnosed.setText(""+diagnosisInfo.DateDiagnosed);

        treatment.setText(""+diagnosisInfo.Treatment);

        remission.setText(""+diagnosisInfo.Remission);
    }


    public void saveTempXML(){
        XmlParser xmlParser = new XmlParser(getApplicationContext());

        xmlParser.saveTempDiagnosisInfo(getDataFromFields(),getApplicationContext());
    }


    public class menuClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            saveTempXML();

            Intent gotoMenu = new Intent(EditDiagnosisInformation.this, MainActivity.class);

            gotoMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(gotoMenu);
        }
    }

    public class homeClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            saveTempXML();

            Intent gotoHome = new Intent(EditDiagnosisInformation.this, MainActivity.class); //change when a home activity is added

            gotoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(gotoHome);
        }
    }
    private ViewDiagnosisInformationData getDataFromFields()
    {
        ViewDiagnosisInformationData diagnosisInfo = new ViewDiagnosisInformationData();

        diagnosisInfo.Diagnosis = diagnosis.getText().toString();

        diagnosisInfo.DateDiagnosed = dateDiagnosed.getText().toString();

        diagnosisInfo.Treatment = treatment.getText().toString();

        diagnosisInfo.Remission = remission.getText().toString();

        return diagnosisInfo;
    }
    public class doneClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            XmlParser xmlParser = new XmlParser(getApplicationContext());

            xmlParser.saveTempDiagnosisInfo(getDataFromFields(),getApplicationContext());



            xmlParser.saveDiagnosisInfoXml(getDataFromFields());

            finish();
        }
    }

}
