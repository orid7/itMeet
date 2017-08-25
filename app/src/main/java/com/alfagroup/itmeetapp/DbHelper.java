package com.alfagroup.itmeetapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import static android.R.attr.data;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 5/5/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 1;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_FNAME = "fname";
    public static final String COLUMN_LNAME = "lname";
    public static final String COLUMN_BIRTH = "birth";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_LEARNLANG = "learnlang";
    public static final String COLUMN_TEACHLANG = "teachlang";

    public static final String MATCH_TABLE = "Match";
    public static final String COLUMN_MATCH_ID = "_id";
    public static final String COLUMN_MATCH_EMAIL = "email";
    public static final String COLUMN_MATCH_DATE = "date";
    public static final String COLUMN_MATCH_TIME = "time";



    public static DbHelper sInstance;


    public static synchronized DbHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DbHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    /*
    create table users(
        id integer primary key autoincrement,
        email text,
        password text);
     */
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT,"
            + COLUMN_FNAME + " TEXT,"
            + COLUMN_LNAME + " TEXT,"
            + COLUMN_BIRTH + " TEXT,"
            + COLUMN_COUNTRY + " TEXT,"
            + COLUMN_LEARNLANG + " TEXT,"
            + COLUMN_TEACHLANG + " TEXT);";

    public static final String CREATE_TABLE_MATCH = "CREATE TABLE " + MATCH_TABLE + "("
            + COLUMN_MATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MATCH_EMAIL + " TEXT,"
            + COLUMN_MATCH_DATE + " TEXT,"
            + COLUMN_MATCH_TIME + " TEXT);";




    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_MATCH);
        Log.d(TAG,  CREATE_TABLE_USERS);
        Log.d(TAG,  CREATE_TABLE_MATCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(  "DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL(  "DROP TABLE IF EXISTS " + MATCH_TABLE);
        onCreate(db);
    }

    public void addUserLang(String learn, String teach,Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LEARNLANG, learn);
        values.put(COLUMN_TEACHLANG, teach);


// Which row to update, based on the title
        String selection = COLUMN_EMAIL + " LIKE ?";
        SharedPreferences prefs = context.getSharedPreferences("com.techobbyist.signuplogin", MODE_PRIVATE);
        String userEmail=prefs.getString("userEmail","");

        String[] selectionArgs = { userEmail };
        int count = db.update(
                USER_TABLE,
                values,
                selection,
                selectionArgs);
        db.close();

        Log.d(TAG, "user Lang inserted" + learn +"  "+ teach);
    }
    /**
     *  INSERT USER DETAILS
     * */
    public void addUserDetails(String fName, String lName,String birth,String country,Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FNAME, fName);
        values.put(COLUMN_LNAME, lName);
        values.put(COLUMN_BIRTH, birth);
        values.put(COLUMN_COUNTRY, country);

// Which row to update, based on the title
        String selection = COLUMN_EMAIL + " LIKE ?";
        SharedPreferences prefs = context.getSharedPreferences("com.techobbyist.signuplogin", MODE_PRIVATE);
        String userEmail=prefs.getString("userEmail","");

        String[] selectionArgs = { userEmail };
       int count = db.update(
                USER_TABLE,
                values,
                selection,
                selectionArgs);
        db.close();

        Log.d(TAG, "user details inserted" + userEmail +" first name: "+ fName+ " last name: "+ fName+ "   "+ birth +"  " +country);
    }
    /**
     * Storing user details in database
     * */
    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);


        long id = db.insert(USER_TABLE, null, values);
        db.close();
        Log.d(TAG, "user inserted" + id);

    }

    public boolean getUser(String email, String pass){

        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public void addUserChooseDateAndTime(String date, String time, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();


        SharedPreferences prefs = context.getSharedPreferences("com.techobbyist.signuplogin", MODE_PRIVATE);
        String userEmail=prefs.getString("userEmail","");

        ContentValues values = new ContentValues();
        values.put(COLUMN_MATCH_EMAIL, userEmail);
        values.put(COLUMN_MATCH_DATE, date);
        values.put(COLUMN_MATCH_TIME, time);


// Which row to update, based on the title


        long id = db.insert(MATCH_TABLE, null, values);
        db.close();
        Log.d(TAG, "Time inserted" + data+" "+time);


    }


    public ArrayList<TimeAndDate> getListDateAndTime(Context context){

        SharedPreferences prefs = context.getSharedPreferences("com.techobbyist.signuplogin", MODE_PRIVATE);
        String userEmail=prefs.getString("userEmail","");


        String selectQuery = "select * from  " + MATCH_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+userEmail+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<TimeAndDate> timeAndDateArrayList = new ArrayList<TimeAndDate>();
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String date = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_MATCH_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_MATCH_TIME));
                timeAndDateArrayList.add(new TimeAndDate(date,time));
                cursor.moveToNext();
            }
        }
        else {
            timeAndDateArrayList.add(new TimeAndDate("Choose",""));
        }
        cursor.close();
        db.close();

            return timeAndDateArrayList;



    }

    public ArrayList<PersonMatch> getPersonMatch(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("com.techobbyist.signuplogin", MODE_PRIVATE);
        String userEmail=prefs.getString("userEmail","");


        ArrayList<PersonMatch> personMatch= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select C.date,C.time,users.*\n" +
                "from(select A.*\n" +
                "from\n" +
                "(select *\n" +
                "from Match m1\n" +
                "where m1.email not like '"+userEmail+"')A\n"+
                "inner join\n" +
                "(select *\n" +
                "from Match m2\n" +
                "where m2.email like '"+userEmail+"')B\n" +
                "on A.date=B.date and A.time=B.time)C\n" +
                "inner join users\n" +
                "on C.email=users.email\n", null);

        if (cursor.moveToFirst()){
           do {
                String email = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_EMAIL));
                String county = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_COUNTRY));
                String fName = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_FNAME));
                String lName = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_LNAME));
                String date = cursor.getString(0);
                String time = cursor.getString(1);
                personMatch.add(new PersonMatch(email,fName,lName,county,date,time));

           } while(cursor.moveToNext());
        }
        else {
            personMatch.add(new PersonMatch("","no","match","","",""));
       }
        cursor.close();
        db.close();

        return personMatch;
    }


    public void deleteTimeAndDate(View view, String date,String time) {

        SQLiteDatabase db = this.getWritableDatabase();

        SharedPreferences prefs = view.getContext().getSharedPreferences("com.techobbyist.signuplogin", MODE_PRIVATE);
        String userEmail=prefs.getString("userEmail","");


        db.delete(MATCH_TABLE,COLUMN_MATCH_EMAIL+"=? and "+COLUMN_MATCH_DATE+"=? and " +COLUMN_MATCH_TIME+"=?",new String[]{userEmail,date,time});
        db.close();
    }
}
