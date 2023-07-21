package com.example.system;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totaltabs;

    public LoginAdapter(@NonNull FragmentManager fm, Context context, int totaltabs) {
        super(fm);
        this.context = context;
        this.totaltabs = totaltabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                Login_Tab_Fragment login_tab_fragment = new Login_Tab_Fragment();
                return login_tab_fragment;

            case 1:
                Register_Tab_Fragment register_tab_fragment = new Register_Tab_Fragment();
                return register_tab_fragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return totaltabs;
    }
}
