package bit.hawwag2.welcometodunedinapp;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
ImageView ivActivities;
    //Global array of type TouristActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabletmode);

// Beginning Fragments
        Fragment dynamicFragment_lView= new listViewFragment();
        android.support.v4.app.FragmentManager fm5=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft5= fm5.beginTransaction();
        ft5.replace(R.id.PlaceHolder1, dynamicFragment_lView);
        ft5.commit();

        Fragment dynamicFragment_ImageView= new ImageDisplayFragment();
        android.support.v4.app.FragmentManager fm1=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft1= fm1.beginTransaction();
        ft1.replace(R.id.PlaceHolder2, dynamicFragment_ImageView);
        ft1.commit();





    }


    //the main activity exposes this method to the listview fragment for Activity/Fragment communication.
    public void giveMeMyData(String chooseImage){

    ivActivities=(ImageView) findViewById(R.id.imageViewFragment);
        TextView tvAcitivity=(TextView) findViewById(R.id.tvActivity);



        Fragment dynamicFragment_ImageView;

        android.support.v4.app.FragmentManager fm4=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft5= fm4.beginTransaction();


        if(chooseImage=="Services")
        {


            ivActivities.setImageResource(R.drawable.services);
            //Make an instance of the services fragment
             dynamicFragment_ImageView= new ServicesFragment();
            tvAcitivity.getText().toString();
            tvAcitivity.setText(chooseImage);



        }
        if(chooseImage=="Eating")
        {


            ivActivities.setImageResource(R.drawable.dining);
             dynamicFragment_ImageView= new EatingFragment();
            tvAcitivity.getText().toString();
            tvAcitivity.setText(chooseImage);


        }
        if(chooseImage=="Shopping")
        {




            ivActivities.setImageResource(R.drawable.shopping);
            dynamicFragment_ImageView= new ShoppingFragment();
            tvAcitivity.getText().toString();
            tvAcitivity.setText(chooseImage);



        }
        if(chooseImage=="Activities")
        {




            ivActivities.setImageResource(R.drawable.thingstodo);
            dynamicFragment_ImageView= new ActivitiesFragment();

            tvAcitivity.getText().toString();
            tvAcitivity.setText(chooseImage);



        }
        ft5.commit();





    }


    }



