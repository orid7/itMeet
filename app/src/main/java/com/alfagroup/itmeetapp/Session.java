package com.alfagroup.itmeetapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 5/5/2016.
 */
public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("com.techobbyist.signuplogin", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean logggedin,String email){
        editor.putBoolean("loggedInmode",logggedin);
        editor.putString("userEmail",email);
        editor.commit();

      //  Log.d("Session","email:"+email+"   share:"+ prefs.getString("userEmail", "") );
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }

    public String getUserEmail(){
        return prefs.getString("userEmail", "");
    }


}
