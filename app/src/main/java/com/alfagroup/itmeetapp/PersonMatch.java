package com.alfagroup.itmeetapp;

/**
 * Created by ori dahari on 01/07/2017.
 */

class PersonMatch {

    private String mEmail,mFname,mLname,mCountry,mDate,mTime;

        /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

      private static final int NO_IMAGE_PROVIDED = -1;

    public PersonMatch(String email, String fname, String lname,String country,String date,String time) {
        mEmail=email;
        mFname = fname;
        mLname=lname;
        mCountry=country;
        mDate=date;
        mTime=time;
        mImageResourceId =(R.drawable.ic_search);
    }
    public String getmEmail() {
        return mEmail;
    }
    public String getmFname() {
        return mFname;
    }
    public String getmLname() {
        return mLname;
    }
    public String getmCountry() {
        return mCountry;
    }
    public String getmDate() {
        return mDate;
    }
    public String getmTime() {
        return mTime;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }


    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}
