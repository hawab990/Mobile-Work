package op_bit.ayaapp;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class EditHealthInformation extends AppCompatActivity {
    XmlParser xmlParser;
    EditText firstName;
    EditText lastName;
    EditText gender;
    EditText nHINumber;
    TextView dob;
    EditText bSAtDiagnosis;
    EditText gPName;
    EditText gPAddress;
    EditText gPNumber;
    EditText allergies;
    EditText other;
    EditText contacts;
    EditText phone;
    EditText oncologist;
    EditText ayaKeyWorker;
    private Button MpickDate;


    ArrayList<ViewHealthInformationData> healthInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edithealth_information);

        SetupControllers();

        setUpListeners();


        fillEditTexts();

    }//endOncreate




    public void SetupControllers()
    {

        firstName=(EditText)findViewById(R.id.etFirstName);
        lastName=(EditText)findViewById(R.id.etLastName);
        gender=(EditText)findViewById(R.id.etGender);
        nHINumber=(EditText)findViewById(R.id.etNHINumber);
        dob=(TextView)findViewById(R.id.tv_DOB);
        bSAtDiagnosis=(EditText)findViewById(R.id.etBSAAtDiagnosis);
        gPName=(EditText)findViewById(R.id.etGPName);
        gPAddress=(EditText)findViewById(R.id.etGPAddress);
        gPNumber=(EditText)findViewById(R.id.etGPNumber);
        allergies=(EditText)findViewById(R.id.etExistingAllergies);
        other=(EditText)findViewById(R.id.etOther);
        contacts =(EditText)findViewById(R.id.etContacts);
        phone =(EditText)findViewById(R.id.etPhone);
        oncologist=(EditText)findViewById(R.id.etOncologist);
        ayaKeyWorker=(EditText)findViewById(R.id.etAYaKeyWorker);



    }

    public void setUpListeners()
    {
        Button btnDoneEditing=(Button)findViewById(R.id.btn_Done);
        btnDoneEditing.setOnClickListener(new healthInfoDoneClickHandler());

        MpickDate=(Button)findViewById(R.id.btn_SelectDate);
        MpickDate.setOnClickListener(new dateSelectOnclickhandler());


        Button btnTOMenu=(Button)findViewById(R.id.btn_menu);
        btnTOMenu.setOnClickListener(new healthInfoMenuCLickHandler());

        Button btnTOHome=(Button)findViewById(R.id.btn_home);
        btnTOHome.setOnClickListener(new healthInfoHomeClickHandler());

        Button btnGoBack=(Button)findViewById(R.id.btn_Back);
        btnGoBack.setOnClickListener(new healthInfobackClickHandler());



    }

    private void fillEditTexts()
    {
        XmlParser xmlParser = new XmlParser(getApplicationContext());
        ViewHealthInformationData getHealthInfo=xmlParser.readTempHealthInfomation(getApplicationContext());

        firstName.setText(getHealthInfo.FirstName);
        lastName.setText(getHealthInfo.LastName);
        gender.setText(getHealthInfo.Gender);
        nHINumber.setText(getHealthInfo.NHINumber);
        dob.setText(getHealthInfo.DOB);
        bSAtDiagnosis.setText(getHealthInfo.BSA);
        gPName.setText(getHealthInfo.GPName);
        gPAddress.setText(getHealthInfo.GPAddress);
        gPNumber.setText(getHealthInfo.GPNumber);
        allergies.setText(getHealthInfo.Allergies);
        other.setText(getHealthInfo.Other);
        contacts.setText(""+getHealthInfo.Contacts);
        phone.setText(getHealthInfo.Phone);
        oncologist.setText(getHealthInfo.Oncologist);
        ayaKeyWorker.setText(getHealthInfo.AYAKeyWorker);
    }



    public void saveTempXML(){
        XmlParser xmlParser = new XmlParser(getApplicationContext());

        xmlParser.saveTempHealthInfo(getInfoDataFromFields(),getApplicationContext());
    }



    private class dateSelectOnclickhandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            DialogFragment newFragment = new CustomDatePicker();
            newFragment.show(getSupportFragmentManager(), "datePicker");

        }


    }
    //callback



    private  ViewHealthInformationData getInfoDataFromFields()
    {
        ViewHealthInformationData healthInfo = new ViewHealthInformationData();

        //Creating an instance of the healthINfo and storing the xmlParser information thats read.
        healthInfo.FirstName=firstName.getText().toString();
        healthInfo.LastName=lastName.getText().toString();
        healthInfo.Gender=gender.getText().toString();
        healthInfo.NHINumber=nHINumber.getText().toString();
        healthInfo.DOB=dob.getText().toString();
        healthInfo.BSA=bSAtDiagnosis.getText().toString();
        healthInfo.GPName=gPName.getText().toString();
        healthInfo.GPAddress=gPAddress.getText().toString();
        healthInfo.GPNumber=gPNumber.getText().toString();
        healthInfo.Allergies=allergies.getText().toString();
        healthInfo.Other=other.getText().toString();
        healthInfo.Contacts=contacts.getText().toString();
        healthInfo.Phone=phone.getText().toString();
        healthInfo.Oncologist=oncologist.getText().toString();
        healthInfo.AYAKeyWorker=ayaKeyWorker.getText().toString();



        return healthInfo;

    }
    private class healthInfoDoneClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            XmlParser xmlParser = new XmlParser(getApplicationContext());

            xmlParser.saveTempHealthInfo(getInfoDataFromFields(), getApplicationContext());
            xmlParser.saveHealthInfoXml(getInfoDataFromFields());

            finish();

        }
    }
    private class healthInfoMenuCLickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {


            saveTempXML();
            Intent gotoMenuIntent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(gotoMenuIntent);
        }
    }
    private class healthInfoHomeClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            saveTempXML();
            Intent goHomeIntent=new Intent(getApplicationContext(),Home.class);
            startActivity(goHomeIntent);
        }
    }
    private class healthInfobackClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            saveTempXML();
            Intent gobackIntent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(gobackIntent);
        }
    }
}




