package bit.hawwag2.welcometodunedinapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.DialogFragment;

public class Dining extends AppCompatActivity {
TouristActivity[]restaurantArray;
    String imageFileName;
    int imageResourceID;
    resterauntArrayAdapter restaurantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);
        initializeArray();

        resterauntArrayAdapter restaurantAdapter =null;
        //Creating the array adapter extended from it is the get view(instance snapshot) and the contstructor that houses the customized adapter inner class.
         restaurantAdapter=new resterauntArrayAdapter(this,R.layout.custom_list_item, restaurantArray);

        //Binding the listview to the adapter Note to self dont forget!!!!
        ListView lvRestaurant=(ListView)findViewById(R.id.lvRestaurants);
        lvRestaurant.setAdapter(restaurantAdapter);


    }//End of onCreate!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!




    //Inner Worker Class Custom Adapter
    public class resterauntArrayAdapter extends ArrayAdapter<TouristActivity>
    {
        //Custom Class contructor extends from the Array Adapter
        //needs an contstructor
        public resterauntArrayAdapter(Context context, int resource, TouristActivity[] objects) {
            super(context, resource, objects);

        }

        @Override
        //Returns  view filled up from the data in the itemsArray[position]
        public View getView(int position, View convertView, ViewGroup Container)
        {
            //Get inflator from the activity
            LayoutInflater inflater=LayoutInflater.from(Dining.this);
            //Specifiy the layout its going to inflate
            View customView=inflater.inflate(R.layout.custom_list_item, Container,false);

            //fill the controls of the view with the information from the array
            //Using the findviewById method from the customview
            ImageView restaurantIV=(ImageView)customView.findViewById(R.id.ivItemImage);
            TextView restaurantNameTextView=(TextView)customView.findViewById(R.id.tvRestaurants);


            //Calling the extention of the adapter method, getItem is used to get the list from the array using its position
            //Position is passed in by the system and When filling the ListView, the system calls getView with 0, then with 1, then with 2, and so on.
            //Get the instances(TouristActivity) current position  from the array.
            TouristActivity currentItem= getItem(position);

            //Use the instance data to initilize the view controls.
            //Setting the current image to the imagview
            restaurantIV.setImageResource(currentItem.getRestaurantImage());
            restaurantNameTextView.setText(currentItem.restaurantName);

            restaurantIV.setTag(currentItem.getRestaurantImage());

            //Setting a separate (SetTag)  onclick handler for when the icon  is clicked.
            restaurantIV.setOnClickListener(new iconClickHandler());


            restaurantNameTextView.setText(currentItem.getRestaurantName());


            return customView;

        }
    }
    public class iconClickHandler implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            int imageResourceID = (int)v.getTag();
            DialogZoom zoomDialog= new DialogZoom();
            Bundle zoomData= new Bundle();
            zoomData.putInt("imageID",imageResourceID);
            zoomDialog.setArguments(zoomData);

            FragmentManager fm=getFragmentManager();
            zoomDialog.show(fm,"zoom");

        }
    }




    // public class iconClickHandler extends





    public void initializeArray()
    {


        //create array of instances of array object TouristActivity
        restaurantArray=new TouristActivity[5];
        restaurantArray[0]=new TouristActivity(R.drawable.dunedinsmokehouse,"Dunedin Smoke House");
        restaurantArray[1]=new TouristActivity(R.drawable.angussteakhouse,"Angus Stake House");
        restaurantArray[2]=new TouristActivity(R.drawable.goldenhavest,"Golden Harvest");
        restaurantArray[3]=new TouristActivity(R.drawable.dunedinsmokehouse,"Dunedin Ironic Cafe");
        restaurantArray[4]=new TouristActivity(R.drawable.etrusco,"Etrusco");
    }




}
