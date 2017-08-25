package com.alfagroup.itmeetapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.app.Activity;


import com.alfagroup.itmeetapp.Logic.DbHelper;
import com.alfagroup.itmeetapp.Logic.MyPersonMatchRecyclerViewAdapter;
import com.alfagroup.itmeetapp.Logic.PersonMatch;
import com.alfagroup.itmeetapp.R;

import java.util.ArrayList;


public class PersonMatchFragment extends Fragment {

    private DbHelper db;
    public static Activity myActivity;

    public PersonMatchFragment() {
    }
    MyPersonMatchRecyclerViewAdapter adapter;
    ListView listView;
    ArrayList<PersonMatch> personMatchArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_personmatch_list, container, false);
        db = DbHelper.getInstance(view.getContext());

        refreshList(view);


        return view;
    }

    public void refreshList(View view){
        ArrayList<PersonMatch> personMatchArrayList =db.getPersonMatch(getContext());
        adapter = new MyPersonMatchRecyclerViewAdapter(getContext(), personMatchArrayList);
        listView = (ListView) view.findViewById(R.id.listPersonMatch);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}
