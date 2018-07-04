package bit.hawwag2.welcometodunedinapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpBreedGroupList();
    }


    public void setUpBreedGroupList()
    {

        //Creating the string
        String[] groups={"Services", "Fun Things To Do", "Dining", "Shopping"};
        //Adapter to populate listview with array
        ArrayAdapter<String> breedGroupAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, groups);
        ListView breedGroupAdapterListView =(ListView) findViewById(R.id.left_drawer);
        //Binding the adapter to the list view
        breedGroupAdapterListView.setAdapter(breedGroupAdapter);
        // setting the indivisual listview(texboxes) to be listened by the click listener.
        breedGroupAdapterListView.setOnItemClickListener(new onClickViewHandler());

    }


    public class onClickViewHandler implements AdapterView.OnItemClickListener
    {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Get the item at position
            String clickedItem = (String) parent.getItemAtPosition(position).toString();
            Intent goToIntent;
    // Navigation switch case when item click.
            switch (clickedItem) {
                case "Services":
                    goToIntent = new Intent(MainActivity.this, Services.class);
                    break;

                case "Fun Things To Do":
                    goToIntent = new Intent(MainActivity.this, FunThingsToDo.class);
                    break;

                case "Dining":
                    goToIntent = new Intent(MainActivity.this, Dining.class);
                    break;

                case "Shopping":
                    goToIntent = new Intent(MainActivity.this, Shopping.class);
                    break;

                default:
                    goToIntent=null;
            }

            if (goToIntent!=null)
                startActivity(goToIntent);

            }





        }
    }


