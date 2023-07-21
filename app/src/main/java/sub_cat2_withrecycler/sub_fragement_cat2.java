package sub_cat2_withrecycler;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Subcat1__with_recycler.SecondRecycler;

public class sub_fragement_cat2 extends Fragment {



    TextView textView1,textView2,txt3;
    RecyclerView recyclerViewsubcat;
    Secondrecyclersub2 secondrecyclersub2;


      Button btn;

    RequestQueue queue;
    ArrayList<Model_classs> arrayListmiddle;
    String imgurl = "http://api.yadavabhi.link/upload/";

    Model_classs model_class=new Model_classs();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_sub_fragement_cat2, container, false);

        queue = Volley.newRequestQueue(getContext());

        //Bundle bundle = this.getArguments();
        String cid =getArguments().getString("key");

        String url = "http://api.yadavabhi.link/Webservice1.asmx/SubCat2?id="+cid;

       // textView2=root.findViewById(R.id.textsubcat2);
     //   imageView=root.findViewById(R.id.imagesubcat2);
        recyclerViewsubcat= root.findViewById(R.id.recyclersubcat2);
        btn=root.findViewById(R.id.btnsub2);
        arrayListmiddle = new ArrayList<>();


        StringRequest stringRequestmiddle= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                      Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String img = imgurl + jsonObject.getString("subimage");
                        model_class = new Model_classs();
                        model_class.setImage(img);

                        String name = jsonObject.getString("subname");
                        model_class.setName(name);

                        String ID = jsonObject.getString("id");
                        model_class.setId(ID);

                        arrayListmiddle.add(model_class);

                        secondrecyclersub2 = new Secondrecyclersub2(arrayListmiddle, getContext());
                        recyclerViewsubcat.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recyclerViewsubcat.setAdapter(secondrecyclersub2);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });

        queue.add(stringRequestmiddle);

        //} else if (cid.equals("1")) {



        //
        // }

        return root;

    }
}


