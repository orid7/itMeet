package com.alfagroup.itmeetapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseTimeFragment extends Fragment {

    String date,time,amPm;
    Spinner spinnerDate,spinnerTime,spinnerAmPm;
    private DbHelper db;
    public ChooseTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_choose_time, container, false);

        db = DbHelper.getInstance(view.getContext());
         spinnerDate = (Spinner) view.findViewById(R.id.spinnerDate);

        ArrayAdapter<CharSequence> adapterDate = ArrayAdapter.createFromResource(getActivity(),
                R.array.date_array,R.layout.spinner_adapter);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(adapterDate);
        date=spinnerDate.getSelectedItem().toString();
         spinnerTime = (Spinner) view.findViewById(R.id.spinnerTime);
        ArrayAdapter<CharSequence> adapterTime = ArrayAdapter.createFromResource(getActivity(),
                R.array.time_array, R.layout.spinner_adapter);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapterTime);
        time=spinnerTime.getSelectedItem().toString();
         spinnerAmPm = (Spinner) view.findViewById(R.id.spinnerAmPm);
        ArrayAdapter<CharSequence> adapterAmpm = ArrayAdapter.createFromResource(getActivity(),
                R.array.amPm_array, R.layout.spinner_adapter);
        adapterAmpm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAmPm.setAdapter(adapterAmpm);
        amPm=spinnerAmPm.getSelectedItem().toString();


        Button bChoose=(Button) view.findViewById(R.id.btnChooseDT);
        bChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                if(validate()){
                    db.addUserChooseDateAndTime(date,time+amPm,view.getContext());
                    refreshChosenListFragment();
                }
            }
        });

        return view;
    }

    private void refreshChosenListFragment() {
        Fragment fragment2=new ChosenListFragment();
        Fragment fragment3=new PersonMatchFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.placeholderTimeChoose, fragment2)
                .replace(R.id.placeholderMatching, fragment3)
                .commit();


    }

    private void initialize(){
        date=spinnerDate.getSelectedItem().toString();
        time=spinnerTime.getSelectedItem().toString();
        amPm=spinnerAmPm.getSelectedItem().toString();
    }
    private boolean validate(){
        boolean valid=true;

        if (date.equals("DATE")||time.equals("TIME")){
            Toast.makeText(getContext(),date + "  Please Enter Date and Time", Toast.LENGTH_SHORT).show();
           valid=false;
       }
        return valid;
    }

}
