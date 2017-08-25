package com.alfagroup.itmeetapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.app.Activity;


import com.alfagroup.itmeetapp.Logic.DbHelper;
import com.alfagroup.itmeetapp.Logic.MyChosenListViewAdapter;
import com.alfagroup.itmeetapp.R;
import com.alfagroup.itmeetapp.Logic.TimeAndDate;

import java.util.ArrayList;


public class ChosenListFragment extends Fragment {

    private DbHelper db;
    public static Activity myActivity;

    public ChosenListFragment() {
    }
    MyChosenListViewAdapter adapter;
    ListView listView;
    ImageButton imageButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chosen_list, container, false);
        db = DbHelper.getInstance(view.getContext());

      refreshList(view);


        return view;
    }


    public void refreshList(View view){
        ArrayList<TimeAndDate> timeAndDateArrayList =db.getListDateAndTime(getContext());
        adapter = new MyChosenListViewAdapter(getContext(), timeAndDateArrayList);
        listView = (ListView) view.findViewById(R.id.listDateAndTime);
        listView.setAdapter(adapter);


    }


}
