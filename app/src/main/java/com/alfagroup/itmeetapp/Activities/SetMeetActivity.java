package com.alfagroup.itmeetapp.Activities;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alfagroup.itmeetapp.Fragments.ChooseTimeFragment;
import com.alfagroup.itmeetapp.Fragments.ChosenListFragment;
import com.alfagroup.itmeetapp.Fragments.PersonMatchFragment;
import com.alfagroup.itmeetapp.R;

public class SetMeetActivity extends Fragment {

    Fragment fragment1,fragment2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_set_meet, container, false);
        ChosenListFragment.myActivity=getActivity();

         fragment1 = new ChooseTimeFragment();
       fragment2 = new ChosenListFragment();
        Fragment fragment3 = new PersonMatchFragment();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.placeholderTimeOpt, fragment1)
              .replace(R.id.placeholderTimeChoose, fragment2)
                .replace(R.id.placeholderMatching, fragment3)
                .commit();



        return view;
    }



}

