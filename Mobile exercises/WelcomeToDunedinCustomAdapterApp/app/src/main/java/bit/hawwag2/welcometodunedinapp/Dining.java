package bit.hawwag2.welcometodunedinapp;

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

public class Dining extends AppCompatActivity {
TouristActivity[]restaurantArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);
        initializeArray();

        //Creating the array adapter extended from it is the get view(instance snapshot) and the contstructor that houses the customized adapter inner class.
        resterauntArrayAdapter restaurantAdapter=new resterauntArrayAdapter(this,R.layout.custom_list_item, restaurantArray);

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
            restaurantIV.setImageDrawable(currentItem.restaurantImage);
            restaurantNameTextView.setText(currentItem.restaurantName);

            return customView;

        }
    }

    public void initializeArray()
    {
        //fetch the drawables using the resources child of the appcombatactivity parent
        Resources ResourceMachine=getResources();

        //store the drawables
        Drawable dunedinSmokeHouseImage=ResourceMachine.getDrawable(R.drawable.dunedinsmokehouse,null);
        Drawable angustStakeHouseImage=ResourceMachine.getDrawable(R.drawable.angussteakhouse,null);
        Drawable goldenHarvestImage=ResourceMachine.getDrawable(R.drawable.goldenhavest,null);
        Drawable dunedinIronicCafeImage=ResourceMachine.getDrawable(R.drawable.dunnazironiccafe,null);
        Drawable etrusco=ResourceMachine.getDrawable(R.drawable.etrusco,null);

        //create array of instances of array of object TouristActivity
        restaurantArray=new TouristActivity[5];
        restaurantArray[0]=new TouristActivity(dunedinSmokeHouseImage,"Dunedin Smoke House");
        restaurantArray[1]=new TouristActivity(angustStakeHouseImage,"Angus Stake House");
        restaurantArray[2]=new TouristActivity(goldenHarvestImage,"Golden Harvest");
        restaurantArray[3]=new TouristActivity(dunedinIronicCafeImage,"Dunedin Ironic Cafe");
        restaurantArray[4]=new TouristActivity(etrusco,"Etrusco");
    }




}
