package com.alfagroup.itmeetapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alfagroup.itmeetapp.Logic.DbHelper;
import com.alfagroup.itmeetapp.R;
import com.alfagroup.itmeetapp.Logic.Session;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button reg,MoveLog;
    private EditText etEmail, etPass;
    private DbHelper db;
    private Session session;
    String email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DbHelper(this);
        reg = (Button)findViewById(R.id.btnReg);
        MoveLog = (Button)findViewById(R.id.btnMoveLogin);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        reg.setOnClickListener(this);
        MoveLog.setOnClickListener(this);
        session = new Session(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnReg:
                register();
                break;
            case R.id.btnMoveLogin:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
            default:

        }
    }

    private void register(){
        initialize();
        if(!validate()){
            displayToast("Please enter details");
        }else{

            db.addUser(email,pass);
            displayToast("User registered");
           finish();
           session.setLoggedin(true,email);
           startActivity(new Intent(RegisterActivity.this, MoreDetailsActivity.class));
            finish();
        }
    }

    private void initialize(){
        email = etEmail.getText().toString().trim();
        pass = etPass.getText().toString().trim();

    }
    private boolean validate(){
        boolean valid=true;

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please Enter valid Email Address");
        valid=false;
        }

        if(pass.isEmpty()){
            etPass.setError("Please Enter Password");
            valid=false;
        }
        return valid;
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
