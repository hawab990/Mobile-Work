package bit.hawwag2.firstfebfridayapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import static bit.hawwag2.firstfebfridayapp.R.id.txtDisplay;

public class MainActivity extends AppCompatActivity {
    TextView febFridays; //Delare the reference variable.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView febFridays; //Delare the reference variable.
        febFridays = (TextView) findViewById(R.id.txtDisplay); //cast and assign it.


        // retrieve the integer array
        Resources resourceResolver=getResources();
        String s="";
        //Storing the array
        int datesArray[]= resourceResolver.getIntArray(R.array.FebFridays);
                for (int i=0 ; i<datesArray.length; i++) {
                    //Adding spaces between integers concatenating and them.
                    s=s+datesArray[i]+" ";
                    febFridays.setText("February Fridays are on the: "+s);
                }


    }
}
