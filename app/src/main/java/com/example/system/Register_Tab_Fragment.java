package com.example.system;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Register_Tab_Fragment extends Fragment {


    TextView nm, em, pa, ad;
    Button reg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_register__tab_, container, false);


        nm = root.findViewById(R.id.name);
        em = root.findViewById(R.id.email);
        pa = root.findViewById(R.id.pass);
        ad = root.findViewById(R.id.address);
        reg = root.findViewById(R.id.register_btn);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String name = nm.getText().toString();
                String email = em.getText().toString();
                String pass = pa.getText().toString();
                String add = ad.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getContext());

                String url ="http://api.yadavabhi.link/Webservice1.asmx/RegisterApi?nn="+name+"&ee="+email+"&pp="+pass+"&aa="+add;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();

                        nm.setText("");
                        em.setText("");
                        pa.setText("");
                        ad.setText("");

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequest);
            }
        });

        return root;
    }
}