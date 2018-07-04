package bit.hawwag2.welcometodunedinapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class listViewFragment extends Fragment {
View fragmentViewer;

    public listViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentViewer= inflater.inflate(R.layout.fragment_list_view, container, false);

        setUpCreaturesList();



        return fragmentViewer;
    }


    public void setUpCreaturesList()
    {
        //Creat the string
        String[] activities={"Services","Shopping","Eating","Activities"};
        //Adapter to populate listview with array
        ArrayAdapter<String> animalListViewAdapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, activities);

        ListView breedGroupAdapterListView =(ListView)  fragmentViewer.findViewById(R.id.ivListFragment);
        //Binding adapter with listView
        breedGroupAdapterListView.setAdapter(animalListViewAdapter);
        //Set the on item click handler
        breedGroupAdapterListView.setOnItemClickListener(new setActivityChooseHandler());


    }

    public class setActivityChooseHandler implements AdapterView.OnItemClickListener
    {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String ClickedItem= (String) parent.getItemAtPosition(position).toString();
            MainActivity myActivity=(MainActivity) getActivity();

            switch (ClickedItem) {
                case"Services":
                    myActivity.giveMeMyData(ClickedItem);
                    break;

                case"Eating":
                    myActivity.giveMeMyData(ClickedItem);
                    break;

                case"Shopping":
                    myActivity.giveMeMyData(ClickedItem);
                    break;
                case"Activities":
                    myActivity.giveMeMyData(ClickedItem);
                    break;


                default:

            }


        }
    }


}
