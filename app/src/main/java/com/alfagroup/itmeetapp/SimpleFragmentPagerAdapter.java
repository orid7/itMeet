package com.alfagroup.itmeetapp;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by ori dahari on 22/02/2017.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        mContext=context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {

        Log.v("fragrem", "getItem  " +position);
        if (position==0)
            return new SetMeetActivity();
        else{

            return new NextMeetActivity();


        }

    }
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.Set_meet);
        } else
            return mContext.getString(R.string.next_meet);

    }
}
