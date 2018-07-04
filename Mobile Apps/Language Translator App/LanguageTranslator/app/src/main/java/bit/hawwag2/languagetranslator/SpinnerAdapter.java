package bit.hawwag2.languagetranslator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class SpinnerAdapter extends ArrayAdapter<CustomOutPutDropDown> {
    //Data members
    int groupid;
    ArrayList<CustomOutPutDropDown> list;
    ImageView ivChooseLang;
    TextView tvDisplayCountry;
    LayoutInflater inflater;

    //Inner Worker Class Custom Adapter
    public SpinnerAdapter(Activity context, int groupid, int id, ArrayList<CustomOutPutDropDown>
            list){
        super(context,id,list);
        this.list=list;
        //Inflate the xml layout
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid=groupid;
    }

    //Returns  view filled up from the data in the itemsArray[position]
    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView=inflater.inflate(groupid,parent,false);


        //fill the controls of the view with the information from the array
        //Using the findviewById method from the customview

         ivChooseLang=(ImageView)itemView.findViewById(R.id.ivLangFlag);
        ivChooseLang.setImageResource(list.get(position).getOutPutLangImage());
        //Calling the extention of the adapter method, getItem is used to get the list from the array using its position
        //Position is passed in by the system and When filling the ListView, the system calls getView with 0, then with 1, then with 2, and so on.
        //Get the instances(CustomOutPutDropDown) current position  from the array.
        CustomOutPutDropDown currentItem=getItem(position);

         tvDisplayCountry=(TextView)itemView.findViewById(R.id.tvOutPutLanguages);
        tvDisplayCountry.setText(list.get(position).getOutPutLangName());

        //Use the instance data to initilize the view controls.

        tvDisplayCountry.setTag(currentItem.getOutPutLangName());
        return itemView;
    }

    //Returns the view  given its paramets.
    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }



}


