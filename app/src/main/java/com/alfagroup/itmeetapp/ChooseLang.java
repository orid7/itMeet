package com.alfagroup.itmeetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseLang extends AppCompatActivity {

    String rbLearn;
    String rbTeach;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lang);
        db = DbHelper.getInstance(this);
        RadioGroup rg1= (RadioGroup) findViewById(R.id.learnGroup);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // checkedId is the RadioButton selected
                        rbLearn=((RadioButton)findViewById(checkedId)).getText().toString();

                    }
                });

        RadioGroup rg2= (RadioGroup) findViewById(R.id.teachGroup);

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                rbTeach=((RadioButton)findViewById(checkedId)).getText().toString();

            }
        });

        Button bfinish=(Button) findViewById(R.id.btnFinishChoose);
        bfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                   db.addUserLang(rbLearn,rbTeach,getApplicationContext());
                    finish();
                    startActivity(new Intent(ChooseLang.this, MainActivity.class));
                    finish();
                }
            }
        });

    }

    private boolean validate(){
        boolean valid=true;
        if (rbLearn.isEmpty()||rbTeach.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter  Languages", Toast.LENGTH_SHORT).show();
            valid=false;
        }
        if(rbTeach==rbLearn){
            Toast.makeText(getApplicationContext(), "Please Enter  different language", Toast.LENGTH_SHORT).show();
            valid=false;
        }

        return valid;
    }

}
