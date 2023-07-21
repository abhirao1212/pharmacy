package Subcat1__with_recycler;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.system.Model_classs;
import com.example.system.R;
import com.example.system.Recyclerclicklistener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SecondFragment extends Fragment {


    Button btn1, btn2;
    TextView textView1,textView2,textView3,textView4;
    RecyclerView recyclerView;
    SecondRecycler secondRecycler;
    int i = 0;

    private static final String KEY_ID = "id";

    SharedPreferences sharedPreferences;


    ImageView imageView;

    RequestQueue queue;
    ArrayList<Model_classs> arrayListtop;
    String imgurl = "http://api.yadavabhi.link/upload/";

    Model_classs model_class=new Model_classs();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.subcat_1, container, false);

        queue = Volley.newRequestQueue(getContext());

        sharedPreferences= getContext().getSharedPreferences("session", Context.MODE_PRIVATE);



        //Bundle bundle = new Bundle();
        String cid =getArguments().getString("key");
        String url = "http://api.yadavabhi.link/Webservice1.asmx/SubCatApi?id="+cid;

      //  btn1=root.findViewById(R.id.decrease_button);
      //  btn2=root.findViewById(R.id.increase_button);
       // Toast.makeText(getContext(), ""+cid, Toast.LENGTH_SHORT).show();

        textView1=root.findViewById(R.id.textsub1);
        textView2=root.findViewById(R.id.textsub2);
        textView3=root.findViewById(R.id.textsub3);
        textView4=root.findViewById(R.id.textsubb4);


        imageView=root.findViewById(R.id.imagesub1);
        btn1=root.findViewById(R.id.btnsub1);
        recyclerView = root.findViewById(R.id.recycle_sub1);
        arrayListtop = new ArrayList<>();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String img = imgurl + jsonObject.getString("subimage");
                        model_class=new Model_classs();
                        model_class.setImage(img);

                        String name = jsonObject.getString("subname");
                        model_class.setName(name);

                        String rating = jsonObject.getString("rating");
                        model_class.setRating(rating);

                        String dis = jsonObject.getString("dis");
                        model_class.setAddto(dis);

                        String addto = jsonObject.getString("addto");
                        model_class.setDis(addto);


                        arrayListtop.add(model_class);

                        secondRecycler = new SecondRecycler(arrayListtop, getContext());
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recyclerView.setAdapter(secondRecycler);

                        String id = sharedPreferences.getString(KEY_ID,null);

                        secondRecycler.onitemclicklistner(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {

                                String cart = "http://api.yadavabhi.link/webservice1.asmx/AddtocartApi?n="+arrayListtop.get(pos).getName()+"&i="+arrayListtop.get(pos).getImage()+"&r="+arrayListtop.get(pos).getRating()+"&d="+arrayListtop.get(pos).getDis()+"&a="+arrayListtop.get(pos).getDis()+"&id="+id;

                                StringRequest stringRequest1 = new StringRequest(Request.Method.GET, cart, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Toast.makeText(getContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });

                                queue.add(stringRequest1);

                            }
                        });

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



