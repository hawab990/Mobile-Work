package op_bit.ayaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Globals
public class ViewHealthInformation extends AppCompatActivity {
    public TextView firstName;
    public TextView lastName;
    public TextView gender;
    public TextView nHINumber;
    public TextView dob;
    public TextView bSAtDiagnosis;
    public TextView gPName;
    public TextView gPAddress;
    public TextView gPNumber;
    public TextView allergies;
    public TextView other;
    public TextView phone;
    public TextView contactsName;
    public TextView oncologist;
    public TextView ayaKeyWorker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_information_background);

        //Read the passed bundle from the next activity get Long and convert it to Date


        setupControls();

        setUpListeners();
    }//End Of Oncreate

    //Referencing the textViews
    public void setupControls()
    {
        firstName=(TextView)findViewById(R.id.tv_FirstName);
        lastName=(TextView)findViewById(R.id.tv_LastName);
        gender=(TextView)findViewById(R.id.tv_Gender);
        nHINumber=(TextView)findViewById(R.id.tv_NHINumber);
        dob=(TextView)findViewById(R.id.tv_DOB);
        bSAtDiagnosis=(TextView)findViewById(R.id.tv_BSAAtDiagnosis);
        gPName=(TextView)findViewById(R.id.tv_GPName);
        gPAddress=(TextView)findViewById(R.id.tv_GPAddress);
        gPNumber=(TextView)findViewById(R.id.tv_GPNumber);
        allergies=(TextView)findViewById(R.id.tv_ExistingAllergies);
        other=(TextView)findViewById(R.id.tv_Other);
        phone=(TextView)findViewById(R.id.tv_Phone);
        contactsName =(TextView)findViewById(R.id.tv_Contacts);
        oncologist=(TextView)findViewById(R.id.tv_Oncologist);
        ayaKeyWorker=(TextView)findViewById(R.id.tv_AYaKeyWorker);
    }

    //An onclick listener with a handlerinstance to manage button click.
    public void setUpListeners()
    {
        Button btnEditEntries=(Button)findViewById(R.id.btn_Edit);
        btnEditEntries.setOnClickListener(new editHealthOnClickHandler() );
    }

    //when the activity is resumed after its paused and forgrounded.
    public void onResume()
    {
        //call  the method getData to fill the textviews.
        getData();
        super.onResume();
    }
    //Method to set the xml information to the ViewHealthInformation object.
    public void getData()
    {


        XmlParser xmlParser=new XmlParser(getApplicationContext());

        ViewHealthInformationData getHealthInfo = xmlParser.readHealthInfo();




        //Displaying the results of the xml structure
        try {
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
            contactsName.setText(getHealthInfo.Contacts);
            phone.setText(getHealthInfo.Phone);
            oncologist.setText(getHealthInfo.Oncologist);
            ayaKeyWorker.setText(getHealthInfo.AYAKeyWorker);

        }
        catch ( Exception e)
        {

            e.printStackTrace();
        }
    }
    //An intent to navigate to the EditHealthInformation activity.
    private class editHealthOnClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent editHealthInfoIntent=new Intent(ViewHealthInformation.this,EditHealthInformation.class);
            startActivity(editHealthInfoIntent);
        }
    }

}

