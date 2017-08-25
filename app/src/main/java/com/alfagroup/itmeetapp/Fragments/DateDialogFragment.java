package com.alfagroup.itmeetapp.Fragments;
import android.annotation.SuppressLint;
        import java.util.Calendar;

import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.DatePicker;
        import android.widget.EditText;
/**
 * Created by ori dahari on 16/06/2017.
 */


@SuppressLint("ValidFragment")
public class DateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText txtdate;
    public DateDialogFragment(View view){
        txtdate=(EditText)view;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {


// Use the current date as the default date in the dialog
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);


    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //show to the selected date in the text box
        String date=day+"-"+(month+1)+"-"+year;
        txtdate.setText(date);
    }



}
