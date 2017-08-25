package com.alfagroup.itmeetapp.Logic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alfagroup.itmeetapp.Logic.PersonMatch;
import com.alfagroup.itmeetapp.R;

import java.util.ArrayList;

import static com.alfagroup.itmeetapp.R.id.personCountry;
import static com.alfagroup.itmeetapp.R.id.personDate;
import static com.alfagroup.itmeetapp.R.id.personFname;
import static com.alfagroup.itmeetapp.R.id.personLname;
import static com.alfagroup.itmeetapp.R.id.personTime;


public class MyPersonMatchRecyclerViewAdapter extends ArrayAdapter<PersonMatch> {



    public MyPersonMatchRecyclerViewAdapter(Context context, ArrayList<PersonMatch> personMatches) {
        super(context, 0, personMatches);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_personmatch, parent, false);
        }

        // Get the object located at this position in the list
        PersonMatch currentPersonMatch = getItem(position);

        TextView fNameText = (TextView) listItemView.findViewById(personFname);
        fNameText.setText(currentPersonMatch.getmFname());


        TextView lNameText = (TextView) listItemView.findViewById(personLname);
        lNameText.setText(currentPersonMatch.getmLname());

        TextView countryText = (TextView) listItemView.findViewById(personCountry);
        countryText.setText(currentPersonMatch.getmCountry());

        TextView DateText = (TextView) listItemView.findViewById(personDate);
        DateText.setText(currentPersonMatch.getmDate());


        TextView TimeText = (TextView) listItemView.findViewById(personTime);
        TimeText.setText(currentPersonMatch.getmTime());



        return listItemView;
    }
}
