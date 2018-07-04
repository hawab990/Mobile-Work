package bit.hawwag2.usernameinputapp;

import android.media.MediaCodec;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///For KeyStroke
        EditText inputTextHandler = (EditText) findViewById(R.id.editText);
        inputTextHandler keyStrokeHandler = new inputTextHandler();

        // Call setOnKeyListener passing in the Handler instance.
        inputTextHandler.setOnKeyListener(keyStrokeHandler);

        //Storing the edit text into a variable.


        // grab the text in the EditText as a string and check its length
        ///int length = str.length();
        inputTextHandler.getText();

    }


    public class inputTextHandler implements View.OnKeyListener {

        @Override

        public boolean onKey(View v, int keyCode, KeyEvent event) {

                  if (keyCode ==KeyEvent.KEYCODE_ENTER) {

                if (keyCode!=KeyEvent.KEYCODE_ENTER)

                    if (inputTextHandler.super.toString().length()!=7) {
                        Toast.makeText(MainActivity.this, "Thank You", Toast.LENGTH_LONG).show();

                    }
                    //matches string with regular expression. If there are no characters
                    //Prompt a toast if the number of characters does no match Reg ex
                    else  if (inputTextHandler.super.toString().length()==7){
                        Toast.makeText(MainActivity.this, "UserName must be at least 8 characters long", Toast.LENGTH_LONG).show();


                    }
                return true;
            }

            return false;

        }
    }
}

