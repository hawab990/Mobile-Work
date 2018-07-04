package bit.hawwag2.eventhandler;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.security.PublicKey;

import static android.R.id.edit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the reference to the button
        Button ButtonChangeDisplay=(Button) findViewById(R.id.changeDisplay);
        //Making an instance of the handler class
        ButtonChangeDisplayClickHandler handler=new  ButtonChangeDisplayClickHandler();


        ///For KeyStroke
        EditText displayChangeHandler= (EditText) findViewById(R.id.editText);
        displayChangeHandler keystrokeHandler= new displayChangeHandler();





        // Call setOnClickListener passing in the Handler instance.
        ButtonChangeDisplay.setOnClickListener(handler);
        // Call setLongClickListener passing in the Handler instance.
        ButtonChangeDisplay.setOnLongClickListener(handler);

        // Call setOnKeyListener passing in the Handler instance.
        displayChangeHandler.setOnKeyListener(keystrokeHandler);

    }
    // Inner class  events handler for the button
     public class ButtonChangeDisplayClickHandler implements View.OnClickListener, View.OnLongClickListener
    {
        @Override
        public void onClick(View v)
        {
            Toast.makeText(MainActivity.this, "Normal Standard click!", Toast.LENGTH_LONG).show();

        }

        @Override
        public boolean onLongClick(View v)
        {
            Toast.makeText(MainActivity.this, " Long click!", Toast.LENGTH_LONG).show();
            return true;
        }

    }

    //////////////// FOR the keystroke part2.1///////////////////////

    //Handler inner classes for the OnkeyListner.



    public class  displayChangeHandler implements  View.OnKeyListener
    {
        @Override
        //Look for the @ sign action to trigger toast
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode== KeyEvent.KEYCODE_AT)
            {
                Toast.makeText(MainActivity.this, "Dont Type @", Toast.LENGTH_LONG).show();
            }

            return false;

        }
    }






}

