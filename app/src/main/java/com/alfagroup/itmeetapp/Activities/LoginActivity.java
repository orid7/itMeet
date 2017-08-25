package com.alfagroup.itmeetapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alfagroup.itmeetapp.Logic.DbHelper;
import com.alfagroup.itmeetapp.R;
import com.alfagroup.itmeetapp.Logic.Session;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login;
    private EditText etEmail, etPass;
    private DbHelper db;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DbHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                login();
                break;

            default:

        }
    }

    private void login(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if(db.getUser(email,pass)){
            session.setLoggedin(true,email);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong email/password",Toast.LENGTH_SHORT).show();
        }
    }
}
