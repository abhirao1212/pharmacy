package com.example.system;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

import Front_page_recycler.firstRecycler;

public class AddtocartFragment extends Fragment {


    RecyclerView cart_rvd;
    StringRequest stringRequest;
    RequestQueue queue;
    addtocartadapter dhjb;
    Model_classs model_classs;
    SharedPreferences sharedPreferences;
    private static final String KEY_ID = "id";
    ArrayList<Model_classs> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_addtocart, container, false);

        cart_rvd = root.findViewById(R.id.cart_rv);
        arrayList = new ArrayList<>();

        sharedPreferences= getContext().getSharedPreferences("session", Context.MODE_PRIVATE);

        queue = Volley.newRequestQueue(getContext());

        String id = sharedPreferences.getString(KEY_ID,null);



        String display = "http://api.yadavabhi.link/webservice1.asmx/Displaycart?id="+id;

        stringRequest = new StringRequest(Request.Method.GET, display, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray jsonArray = new JSONArray(response);


                    for (int i=0; i<jsonArray.length(); i++) {

                        model_classs = new Model_classs();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String itname = jsonObject.getString("Itemname");
                        model_classs.setName(itname);

                        String itemimg = jsonObject.getString("Itemimg");
                        model_classs.setImage(itemimg);

                        arrayList.add(model_classs);


                         dhjb = new addtocartadapter(arrayList,getContext());
                      //  dhjb = new firstRecycler(arrayListtop, getContext());
//                        cart_rvd.setLayoutManager(new GridLayoutManager(getContext(), 4));
//                        cart_rvd.setAdapter(dhjb);
                        cart_rvd.setLayoutManager(new LinearLayoutManager(getContext()));
                        cart_rvd.setAdapter(dhjb);


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

        return root;
    }
}