package com.example.system;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Front_page_recycler.SecondrecyclerCat2;
import Front_page_recycler.firstRecycler;
import Front_page_recycler.thirdrecyclercat3;
import Subcat1__with_recycler.SecondFragment;
import sub_cat2_withrecycler.Secondrecyclersub2;
import sub_cat2_withrecycler.sub_fragement_cat2;
import sub_cat3_with_recycler.Sub_fragment_cat3;


public class MainFragment extends Fragment {


    /*------------------Adapter recycler----------------*/
    Front_page_recycler.firstRecycler firstRecycler;
    SecondrecyclerCat2 secondrecyclercat2;
    Front_page_recycler.thirdrecyclercat3 thirdrecyclercat3;

    RecyclerView recyclerView, recyclerView2,recyclerView3;
    ArrayList<Model_classs> arrayListtop, arrayListmiddle,arrayListthird;

    RequestQueue queue;

    ImageSlider imageSlider;



    String imgurl = "http://api.yadavabhi.link/upload/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.category_item, container, false);
        queue = Volley.newRequestQueue(getContext());
        recyclerView = root.findViewById(R.id.recycler_first);
        recyclerView2 = root.findViewById(R.id.recycler2);
        recyclerView3 = root.findViewById(R.id.recycler3);
        imageSlider = root.findViewById(R.id.imageslider);
        arrayListtop = new ArrayList<>();
        arrayListmiddle = new ArrayList<>();
        arrayListthird = new ArrayList<>();

        ArrayList<SlideModel>slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://www.netmeds.com/images/cms/offers/1615906096_Mobile-home-nms69.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.netmeds.com/images/cms/offers/1631021021_Mobile-home-consultaion-50.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.doconline.com/assets/image/blog/telemedicine-india.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://blog.grabon.in/wp-content/uploads/2022/06/Best-Online-Doctor-Consultation-Apps.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.netmeds.com/images/cms/offers/1582871207_m.Home--netmeds35.jpg", ScaleTypes.FIT));


        imageSlider.setImageList(slideModels,ScaleTypes.FIT);


        StringRequest stringRequesttop = new StringRequest(Request.Method.GET, "http://api.yadavabhi.link/Webservice1.asmx/CategoryApi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Model_classs model_class = new Model_classs();
                        String name = jsonObject.getString("Cname");
                        model_class.setName(name);


                        String ID = jsonObject.getString("id");
                        model_class.setId(ID);


                        String img = imgurl + jsonObject.getString("Cimg");
                        model_class.setImage(img);


                        arrayListtop.add(model_class);

                        firstRecycler = new firstRecycler(arrayListtop, getContext());
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
                        recyclerView.setAdapter(firstRecycler);

                        firstRecycler.onitemclicklistner(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {

                                // Toast.makeText(getContext(), ""+arrayListtop.get(pos).getId(), Toast.LENGTH_SHORT).show();

                                SecondFragment secondFragment = new SecondFragment();
                                Bundle bundle = new Bundle();

                                bundle.putString("key", "" + arrayListtop.get(pos).getId());
                                secondFragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.container, secondFragment).addToBackStack(null).commit();


                             /*   FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.container1, secondFragment);
                                transaction.commit();*/

                            }
                        });
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

        queue.add(stringRequesttop);


        //-----------------------------------------------Second Fragment------------------------------------------


        StringRequest stringRequestmiddle = new StringRequest(Request.Method.GET, "http://api.yadavabhi.link/Webservice1.asmx/Category3Api", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Model_classs model_class = new Model_classs();

                        String name = jsonObject.getString("name");
                        model_class.setName(name);


                        String ID = jsonObject.getString("id");
                        model_class.setId(ID);


                        String img = imgurl + jsonObject.getString("image");
                        model_class.setImage(img);

                        arrayListmiddle.add(model_class);


                        secondrecyclercat2 = new SecondrecyclerCat2(arrayListmiddle, getContext());
                        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        recyclerView2.setAdapter(secondrecyclercat2);

                        secondrecyclercat2.onitemclicklistner(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {



                                sub_fragement_cat2 sub_fragement_cat2 = new sub_fragement_cat2();
                                Bundle bundle = new Bundle();

                                bundle.putString("key", ""+arrayListmiddle.get(pos).getId());
                                sub_fragement_cat2.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.container, sub_fragement_cat2).addToBackStack(null).commit();



                            }
                        });



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


//--------------------------------------third---------------------------------------------------


        StringRequest stringRequestthird = new StringRequest(Request.Method.GET, "http://api.yadavabhi.link/Webservice1.asmx/Category3Api", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Model_classs model_class = new Model_classs();
                        String name = jsonObject.getString("name");
                        model_class.setName(name);

                        String ID = jsonObject.getString("id");
                        model_class.setId(ID);


                        String img = imgurl + jsonObject.getString("image");
                        model_class.setImage(img);

                        arrayListthird.add(model_class);

                        thirdrecyclercat3 = new thirdrecyclercat3(arrayListthird, getContext());
                        recyclerView3.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recyclerView3.setAdapter(thirdrecyclercat3);


                        thirdrecyclercat3.onitemclicklistner(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {

                                Sub_fragment_cat3 sub_fragment_cat3 = new Sub_fragment_cat3();
                                Bundle bundle = new Bundle();

                                bundle.putString("key", "" + arrayListthird.get(pos).getId());
                                sub_fragment_cat3.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.container, sub_fragment_cat3).addToBackStack(null).commit();


                            }
                        });
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

        queue.add(stringRequestthird);


        return root;

    }
}



