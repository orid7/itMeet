package com.alfagroup.itmeetapp.Logic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alfagroup.itmeetapp.Fragments.ChosenListFragment;
import com.alfagroup.itmeetapp.Fragments.PersonMatchFragment;
import com.alfagroup.itmeetapp.Logic.DbHelper;
import com.alfagroup.itmeetapp.Logic.TimeAndDate;
import com.alfagroup.itmeetapp.R;

import java.util.ArrayList;


public class MyChosenListViewAdapter extends ArrayAdapter<TimeAndDate> {
    View listItemView;


    public MyChosenListViewAdapter(Context context, ArrayList<TimeAndDate> timeAndDate) {
        super(context, 0, timeAndDate);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_chosen, parent, false);
        }

        // Get the object located at this position in the list
        TimeAndDate currentTimeAndDate = getItem(position);


        TextView DateText = (TextView) listItemView.findViewById(R.id.DateText);
        DateText.setText(currentTimeAndDate.getDate());


        TextView TimeText = (TextView) listItemView.findViewById(R.id.TimeText);
        TimeText.setText(currentTimeAndDate.getTime());

        ImageButton imageButton= (ImageButton) listItemView.findViewById(R.id.btnDelete);
        imageButton.setClickable(false);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteTimeAndDate(position);
            }
        });
        return listItemView;
    }

    private void DeleteTimeAndDate(int position) {

        DbHelper db = DbHelper.getInstance(listItemView.getContext());
        db.deleteTimeAndDate(listItemView,getItem(position).getDate(),getItem(position).getTime());
        Fragment fragment2=new ChosenListFragment();
        Fragment fragment3=new PersonMatchFragment();
       // FragmentManager manager = ((Activity)listItemView.getContext()).getFragmentManager();
        android.support.v4.app.FragmentManager manager = ((AppCompatActivity)listItemView.getContext()).getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.placeholderTimeChoose, fragment2)
                .replace(R.id.placeholderMatching, fragment3)
                .commit();

    }


    }

