package com.bit.iotdatabaseapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class listview extends Fragment {
    ListView lvQueryResult;

    public listview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView= inflater.inflate(R.layout.fragment_listview, container, false);
        Bundle bundle=getArguments();

        ArrayList<String> personArray = (ArrayList<String>)bundle.getSerializable("personQResult");
        ArrayList<String> items = (ArrayList<String>)bundle.getSerializable("itemsQuery");
        ArrayList<String> itemsIssued = (ArrayList<String>)bundle.getSerializable("itemsQIssuedResult");
        ArrayList<String> itemsDeployed = (ArrayList<String>)bundle.getSerializable("deployedQueryResults");


        lvQueryResult=fragmentView.findViewById(R.id.lv_queryResult);


        switch(bundle.getString("selectionValue"))
        {


            case "person":
                ArrayAdapter<String> personAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, personArray);
                lvQueryResult.setAdapter(personAdapter);
                break;

            case "item":
                ArrayAdapter<String> itemsAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, items);
                lvQueryResult.setAdapter(itemsAdapter);
                break;

            case "itemsIssued":
                ArrayAdapter<String> itemsIssuedAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, itemsIssued);
                lvQueryResult.setAdapter(itemsIssuedAdapter);
                break;

            case "Deploy":
                ArrayAdapter<String> itemsDeployedAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, itemsDeployed);
                lvQueryResult.setAdapter(itemsDeployedAdapter);
                break;

        }

        //bundle.getSerializable("itemsQuery")

        return fragmentView;
    }

}
