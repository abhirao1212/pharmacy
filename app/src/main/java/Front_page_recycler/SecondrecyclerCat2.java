package Front_page_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.system.Model_classs;
import com.example.system.R;
import com.example.system.Recyclerclicklistener;

import java.util.ArrayList;

public class SecondrecyclerCat2 extends RecyclerView.Adapter<SecondrecyclerCat2.Myviewholder> {

    ArrayList<Model_classs> arrayListmiddle;
    Context context ;
    Recyclerclicklistener recyclerclicklistener;

    public SecondrecyclerCat2(ArrayList<Model_classs> arrayListmiddle, Context context) {

        this.arrayListmiddle = arrayListmiddle;
        this.context = context;

    }


    @NonNull
    @Override
    public SecondrecyclerCat2.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.secondrecyclercat2, null, false);
        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull SecondrecyclerCat2.Myviewholder holder, int position) {
        Glide.with(context).load(arrayListmiddle.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(arrayListmiddle.get(position).getName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListmiddle.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView textView;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imagecat2);
            textView = itemView.findViewById(R.id.textcat2);


        }

    }
    public void onitemclicklistner(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;
    }


    }

