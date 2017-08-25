package com.alfagroup.itmeetapp.Activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alfagroup.itmeetapp.Fragments.DateDialogFragment;
import com.alfagroup.itmeetapp.Logic.DbHelper;
import com.alfagroup.itmeetapp.R;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;

public class MoreDetailsActivity extends AppCompatActivity {
    private Button country;
    private Button next;
    private EditText etFName, etLName,etBirth;
    private DbHelper db;
    private Context mContext;
    String fName,lName,birth,countryString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
       mContext=this;
        db = DbHelper.getInstance(this);
        etFName = (EditText)findViewById(R.id.etFname);
        etLName = (EditText)findViewById(R.id.etLname);
        next = (Button)findViewById(R.id.btnNextDetails);

        country = (Button) findViewById(R.id.chooseCountry);
        country.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            chooseCountry();
        }
    });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                if(validate()){

                    addDetails();
                }
            }
        });
    }

    private void addDetails(){

            db.addUserDetails(fName,lName,birth,countryString,mContext);
            finish();
            startActivity(new Intent(MoreDetailsActivity.this, ChooseLangActivity.class));
           finish();
        }


    private void initialize(){
        fName = etFName.getText().toString().trim();
        lName = etLName.getText().toString().trim();
        birth=etBirth.getText().toString().trim();
    }

    private boolean validate(){
        boolean valid=true;

        if(fName.isEmpty() ||lName.isEmpty()||birth.isEmpty() || countryString.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter all details", Toast.LENGTH_SHORT).show();
            valid=false;
        }

        return valid;
    }


    public void chooseCountry(){

        final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                // Implement your code here
                country.setText(name);
                countryString=name;
                picker.dismiss();
            }
        });

    }

    public void onStart(){
        super.onStart();

        EditText txtDate=(EditText)findViewById(R.id.etDate);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialogFragment dialog=new DateDialogFragment(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");

                }
            }

        });
        etBirth=txtDate;

    }



}
