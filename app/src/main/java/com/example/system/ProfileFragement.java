package com.example.system;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragement extends Fragment {


    TextView nm, em, pas, ad;
    SharedPreferences sharedPreferences;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "pass";
    private static final String KEY_ADD = "address";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile,container,false);


        nm = root.findViewById(R.id.pro_name);
        em = root.findViewById(R.id.pro_email);
        pas = root.findViewById(R.id.pro_pass);
        ad = root.findViewById(R.id.pro_ad);

        sharedPreferences= getContext().getSharedPreferences("session", Context.MODE_PRIVATE);



        String id = sharedPreferences.getString(KEY_ID,null);
        String Name=sharedPreferences.getString(KEY_NAME,null);
        String Email=sharedPreferences.getString(KEY_EMAIL,null);
        String PASS=sharedPreferences.getString(KEY_PASS,null);
        String Address=sharedPreferences.getString(KEY_ADD,null);

        Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
        nm.setText(Name);
        em.setText(Email);
        pas.setText(PASS);
        ad.setText(Address);



        return root;
    }
}