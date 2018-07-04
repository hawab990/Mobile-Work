package bit.hawwag2.firstfragmentapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayListView extends Fragment {


    public DisplayListView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_display_list_view, container, false);
        //Get a reference to the List View.
        ListView lvAnimals=(ListView) fragmentView.findViewById(R.id.lvAnimals);

        // make The adapter
        String[] animals={"Antelope", "Buffalo","Coyote","Donkey","Emu","Ferret","Gorilla","Heron" };
        //populate the adapter
        ArrayAdapter<String> animalsAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,animals);

        //Bind adapter to the ListView
        lvAnimals.setAdapter(animalsAdapter);

        return fragmentView;





    }





}
