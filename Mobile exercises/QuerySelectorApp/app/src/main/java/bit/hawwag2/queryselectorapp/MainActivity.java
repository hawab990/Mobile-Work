package bit.hawwag2.queryselectorapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase demoDb;

    String []displayStringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoDb=openOrCreateDatabase("demoDb",MODE_PRIVATE,null);
        Button btnRunQuery =(Button) findViewById(R.id.btnQuerySelector);
        btnRunQuery.setOnClickListener(new onclickHandler());

    }
    public  String[] getCities()

    {
        String selectQuery="SELECT * FROM tblCity";
        Cursor recordSet =demoDb.rawQuery(selectQuery,null);

        //returning the number of rows in the cursor
        int recordCount= recordSet.getCount();
        //Populating the first row of each column
        displayStringArray= new String[recordCount];

        //returns the index of cityName and countryName column
        int cityNameIndex=recordSet.getColumnIndex("cityName");
        int countryNameIndex=recordSet.getColumnIndex("countryName");
        //Start from the first row
        recordSet.moveToFirst();

        for (int r=0;r<recordCount;r++)
        {
            String cityName= recordSet.getString(cityNameIndex);
            String countryName= recordSet.getString(countryNameIndex);
            displayStringArray[r]=cityName+", "+countryName;
            recordSet.moveToNext();
        }
        return displayStringArray;

    }


    public class onclickHandler implements View.OnClickListener
    {


        @Override
        public void onClick(View v) {
            //Drop the cityTable()
            //Get the cities name
            ListView lvCountries=(ListView)findViewById(R.id.lvCountries);
            CreateCityTable();
            populateTable();

            ArrayAdapter<String> countryListArrayAdapter=new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,displayStringArray);
            lvCountries.setAdapter(countryListArrayAdapter);


        }
    }



    public void CreateCityTable()


    {
        // Drops the city table
        String dropQuery = "DROP TABLE IF EXISTS tblCity";
        demoDb.execSQL(dropQuery);

        // Create Table
        String createQuery = "CREATE TABLE IF NOT EXISTS tblCity(" +
                "cityID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cityName TEXT NOT NULL," +
                "countryName TEXT NOT NULL);";

        demoDb.execSQL(createQuery);


    }
    ///inserting into the table
    private void populateTable()
    {


        // Insert records
        demoDb.execSQL("INSERT INTO tblCity VALUES(null, 'Amsterdam', 'Netherlands')");
        demoDb.execSQL("INSERT INTO tblCity VALUES(null, 'Berlin', 'Germany')");
        demoDb.execSQL("INSERT INTO tblCity VALUES(null, 'Cairo', 'Egypt')");
        demoDb.execSQL("INSERT INTO tblCity VALUES(null, 'Duendin', 'New Zealand')");

    }





}
