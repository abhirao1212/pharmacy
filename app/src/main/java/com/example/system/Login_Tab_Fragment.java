package com.example.system;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Login_Tab_Fragment extends Fragment {


    EditText email, pass;
    TextView forget;
    Button login_btn;
    StringRequest stringRequest;
    RequestQueue queue;
    SharedPreferences sharedPreferences;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "pass";
    private static final String KEY_ADD = "address";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login__tab_,container,false);


        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.password);
        forget = root.findViewById(R.id.forget_tv);
        login_btn = root.findViewById(R.id.login_btn);


        sharedPreferences= getContext().getSharedPreferences("session", Context.MODE_PRIVATE);

        String aa = sharedPreferences.getString("key","0");



        if (aa!="0"){

            Intent in = new Intent(getContext(),MainActivity.class);
            startActivity(in);
            getActivity().finish();

        }

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forget.setTranslationX(800);
        login_btn.setTranslationX(800);


        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forget.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login_btn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String em = email.getText().toString();
                String ps = pass.getText().toString();

                queue = Volley.newRequestQueue(getContext());

                String log = "http://api.yadavabhi.link/Webservice1.asmx/LoginApi?ee="+em+"&pp="+ps;

                String pro = "http://api.yadavabhi.link/Webservice1.asmx/profileApi?ee="+em;


                stringRequest = new StringRequest(Request.Method.GET, log, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String lo = jsonObject.getString("Logg");

                            if (lo.equals("1")){

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("key",email.getText().toString());
                                editor.commit();

                                Intent in = new Intent(getContext(),MainActivity.class);
                                startActivity(in);
                                getActivity().finish();

                            }else {

                                Toast.makeText(getContext(), "Invalid User Id & Password", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequest);

                stringRequest = new StringRequest(Request.Method.GET, pro, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i=0; i<jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String ID = jsonObject.getString("id");

                                String nm = jsonObject.getString("name");

                                String es = jsonObject.getString("email");

                                String ps = jsonObject.getString("pass");

                                String ad = jsonObject.getString("address");

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(KEY_ID,ID);
                                editor.putString(KEY_NAME,nm);
                                editor.putString(KEY_EMAIL,es);
                                editor.putString(KEY_PASS,ps);
                                editor.putString(KEY_ADD,ad);
                                editor.commit();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

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