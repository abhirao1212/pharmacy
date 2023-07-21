package com.example.system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import Subcat1__with_recycler.SecondRecycler;

public class     addtocartadapter extends RecyclerView.Adapter<addtocartadapter.myviewholder> {

    ArrayList<Model_classs> arrayList;
    Context context;


    public addtocartadapter(ArrayList<Model_classs> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public addtocartadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.addtocart,null,false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull addtocartadapter.myviewholder holder, int position) {


        Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(arrayList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        TextView textView;

        ImageView imageView;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imagecart);
            textView = itemView.findViewById(R.id.textcart);
        }
    }
}
