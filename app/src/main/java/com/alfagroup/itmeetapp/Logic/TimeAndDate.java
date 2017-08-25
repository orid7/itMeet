package com.alfagroup.itmeetapp.Logic;

/**
 * Created by ori dahari on 30/06/2017.
 */

public class TimeAndDate {


    private String date;
    private String time;


    public TimeAndDate(String dateText, String timeText) {
        date = dateText;
        time = timeText;

    }


    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}

