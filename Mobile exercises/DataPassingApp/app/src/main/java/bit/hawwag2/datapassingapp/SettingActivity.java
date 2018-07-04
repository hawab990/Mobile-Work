package bit.hawwag2.datapassingapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    EditText enterUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button btnReturnResult =(Button) findViewById(R.id.btnReturnToMenu);
        //For the editText
        enterUsername= (EditText) findViewById(R.id.editTextUsername);

        btnReturnResult.setOnClickListener(new returnControlToLaunchHandler());



    }//end Oncreate


//Create an input method Edit text and  and get the text and pass it into DOInBackgroundMethod for processing
    //The Do InBackgroundMethod will return a JSON Query Comparing it with the editText.


    public class  returnControlToLaunchHandler implements View.OnClickListener
    {


        @Override
        public void onClick(View v) {
            //Must get the text of th
            String usernameString= enterUsername.getText().toString();
            if (usernameString.length()>2)
            {
                //Createan intent. No arguments because this just pops the activity stack
                Intent returnIntent= new Intent();
                //Load up the return data, Use a bundle to return multiple fields.
                returnIntent.putExtra("requestResult", usernameString);

                //set the return code. Another result is RESULT_CANCELLED, for cancel buttons

                setResult(Activity.RESULT_OK, returnIntent);
                //pop yourself off the Activity Stack.
                //Control and intent object go to Activity who launched you
                finish();

            }
            else{
                Toast.makeText(SettingActivity.this, "Your username is too short", Toast.LENGTH_LONG).show();
            }

        }
    }
}
