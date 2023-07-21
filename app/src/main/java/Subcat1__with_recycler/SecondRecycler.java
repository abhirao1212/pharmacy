package Subcat1__with_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.system.Model_classs;
import com.example.system.R;
import com.example.system.Recyclerclicklistener;

import java.util.ArrayList;

public class SecondRecycler extends RecyclerView.Adapter<SecondRecycler.Myviewholder> {

    ArrayList<Model_classs> arrayListtop;
    Context context;

    Recyclerclicklistener recyclerclicklistener;

    public SecondRecycler(ArrayList<Model_classs> arrayListtop, Context context) {
        this.arrayListtop = arrayListtop;
        this.context = context;
    }

    @NonNull
    @Override
    public SecondRecycler.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcat1, null, false);
        Myviewholder myviewholder = new Myviewholder(view);
        return (myviewholder);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondRecycler.Myviewholder holder, int position) {

        Glide.with(context).load(arrayListtop.get(position).getImage()).into(holder.imageView);
        holder.text1.setText(arrayListtop.get(position).getName());
        holder.text2.setText(arrayListtop.get(position).getAddto());
        holder.text3.setText(arrayListtop.get(position).getDis());
        holder.text4.setText(arrayListtop.get(position).getRating());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });
        // holder.text.setText(arrayListmiddle.get(position).getValue());


    }

    @Override
    public int getItemCount() {
        return arrayListtop.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView text1,text2,text3,text4;
        Button btn;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.imagesub1);
            text1 = itemView.findViewById(R.id.textsub1);
            text2 = itemView.findViewById(R.id.textsub2);
            text3 = itemView.findViewById(R.id.textsubb3);
            text4 = itemView.findViewById(R.id.textsubb4);
            btn = itemView.findViewById(R.id.btnsub1);


        }
        }


    public void onitemclicklistner(Recyclerclicklistener recyclerclicklistener) {
        this.recyclerclicklistener = recyclerclicklistener;
    }
}

