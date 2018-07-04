package bit.hawwag2.listtocolourfragmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.util.List;

/**
 * Created by USER987 on 25/03/2017.
 */






public class List_dialogue_view extends DialogFragment {


    View dialogueView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {

       // inflate the layout view to produce  a list dialogue fragment
        dialogueView =inflater.inflate(R.layout.list_dialogue_view, container);
        setUpCreaturesList();
        return dialogueView;


    }

    public void setUpCreaturesList()
    {
        //Creat the string
        String[] animals={"Meerkats","Alpacas","Manatee","Panda"};
        //Adapter to populate listview with array
        ArrayAdapter <String> animalListViewAdapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, animals);

        ListView breedGroupAdapterListView =(ListView)  dialogueView.findViewById(R.id.lvCreature);
        //Binding adapter with listView
        breedGroupAdapterListView.setAdapter(animalListViewAdapter);
        //Set the on item click handler
        breedGroupAdapterListView.setOnItemClickListener(new setOnAnimalChooseHandler());

    }
    public class setOnAnimalChooseHandler implements AdapterView.OnItemClickListener
    {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String ClickedItem= (String) parent.getItemAtPosition(position).toString();
            MainActivity myActivity=(MainActivity) getActivity();

            switch (ClickedItem) {
                case"Meerkats":
                    myActivity.giveMeMyData(ClickedItem);
                    break;

                case"Alpacas":
                    myActivity.giveMeMyData(ClickedItem);
                    break;

                case"Manatee":
                    myActivity.giveMeMyData(ClickedItem);
                    break;
                case"Panda":
                    myActivity.giveMeMyData(ClickedItem);
                    break;


                default:

            }






        }


    }




}
