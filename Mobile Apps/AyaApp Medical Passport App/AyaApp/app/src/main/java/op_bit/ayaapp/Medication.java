package op_bit.ayaapp;

import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Medication extends AppCompatActivity {

    public ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        ArrayList<String>array= new ArrayList<>();

        array.add(0,"Bleomycin");
        array.add(1,"Etopiside");
        array.add(2, "Cisplatin");

        lv=(ListView)findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        lv.setAdapter(adapter);


    }
}
