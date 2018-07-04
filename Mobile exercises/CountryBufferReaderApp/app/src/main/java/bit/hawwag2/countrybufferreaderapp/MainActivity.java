package bit.hawwag2.countrybufferreaderapp;

import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String>stringHolder;
    String assetFileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// instantiating the array adapter and passing in the loadStringArray method callpassing in the string assetfilename.
        ArrayAdapter<String>stringsArrayAdapter= new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,LoadStringArray("cities.txt"));

        ListView lvCities= (ListView) findViewById(R.id.LvCities);
        lvCities.setAdapter(stringsArrayAdapter);





    }








// Method  that accepts a delcared string and loads an array through a stream reader.

    public ArrayList<String> LoadStringArray(String assetFileName)
    {

        stringHolder= new ArrayList<String>();
        // Get assetmanager
        AssetManager am=getAssets();

        try
        {
            //Set up io Stream
            InputStream is=am.open(assetFileName);
            InputStreamReader ir= new InputStreamReader(is);
            BufferedReader br=new BufferedReader(ir);

            //Read it in
            String currentString;
            //While there are characters keep reading it in.
            while ((currentString=br.readLine()) !=null)
            {
                //fill the Array with Strings/
                stringHolder.add(currentString);

            }
            br.close();

        }catch (IOException e)
        {
           e.printStackTrace();
        }

return  stringHolder;
    }




}
