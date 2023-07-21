package sub_cat3_with_recycler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Sub_fragment_cat3 extends Fragment {

    TextView textView2;
    RecyclerView recyclerViewsub3;

    Secondrecycler_sub3 secondrecycler_sub3 ;


    ImageView imageView;

    RequestQueue queue;
    ArrayList<Model_classs> arrayListthird;
    String imgurl = "http://api.yadavabhi.link/upload/";

    Model_classs model_class=new Model_classs();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_sub_cat3, container, false);

        queue = Volley.newRequestQueue(getContext());


        String cid =getArguments().getString("key");
        String url = "http://api.yadavabhi.link/Webservice1.asmx?op=SubCat3="+cid;

        textView2=root.findViewById(R.id.textrec3);
        imageView=root.findViewById(R.id.imagesub3);
        recyclerViewsub3 = root.findViewById(R.id.recyclersubcat2);
        arrayListthird = new ArrayList<>();


        StringRequest stringRequesthird = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //    Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    //  Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String img = imgurl + jsonObject.getString("subimage");
                        model_class = new Model_classs();
                        model_class.setImage(img);

                        String name = jsonObject.getString("subname");
                        model_class.setName(name);


                        String ID = jsonObject.getString("id");
                        model_class.setId(ID);

                        arrayListthird.add(model_class);
                        secondrecycler_sub3 = new Secondrecycler_sub3(arrayListthird,getContext());
                        recyclerViewsub3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        recyclerViewsub3.setAdapter(secondrecycler_sub3);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        queue.add(stringRequesthird);

        //} else if (cid.equals("1")) {

        Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();


        //
        // }

        return root;

    }
}


