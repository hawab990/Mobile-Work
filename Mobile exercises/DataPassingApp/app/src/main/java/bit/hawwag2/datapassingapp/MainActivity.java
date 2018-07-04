package bit.hawwag2.datapassingapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView txtViewResultUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewResultUsername= (TextView) findViewById(R.id.TextViewUserNameResult);

        Button btnPassData=(Button) findViewById(R.id.buttonGoToSettings);
        btnPassData.setOnClickListener(new StartForResultHandler());

    } //End Oncreate

    public class StartForResultHandler implements View.OnClickListener
    {


        @Override
        public void onClick(View v) {
            //The Second argument is a code that allows  the reciever activity to deal with multiple requests
            Intent changeActivityIntent= new Intent(MainActivity.this, SettingActivity.class);
            startActivityForResult(changeActivityIntent,0);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Use the request Code(Set to 0 in startActivityResult) and resultCode to determine behaviour
        if ((requestCode ==0))
        {
            if(resultCode== Activity.RESULT_OK)
            {
                // Fetch the result you were sent
                String result =data.getStringExtra("requestResult");//
                // Set the text field with the passed data.
                txtViewResultUsername.setText(result);


            }


        }



    }


}
