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
public class search_listview extends Fragment {
    ListView lvSearchResult;

    public search_listview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView= inflater.inflate(R.layout.fragment_search_listview, container, false);
        Bundle bundle=getArguments();


        ArrayList<String> searchArray = (ArrayList<String>)bundle.getSerializable("SearchJSONResults");



        lvSearchResult =fragmentView.findViewById(R.id.lv_searchResult);


        ArrayAdapter<String> personAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, searchArray);
        lvSearchResult.setAdapter(personAdapter);


        //bundlegetSerializable("itemsQuery")

        return fragmentView;
    }
    }


