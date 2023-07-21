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

public class thirdrecyclercat3 extends RecyclerView.Adapter<thirdrecyclercat3.Myviewholder> {

    ArrayList<Model_classs> arrayListthird;
    Context context ;
    Recyclerclicklistener recyclerclicklistener;

    public thirdrecyclercat3(ArrayList<Model_classs> arrayListthird, Context context) {

        this.arrayListthird = arrayListthird;
        this.context = context;

    }

    @NonNull
    @Override
    public thirdrecyclercat3.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.thirdrecyclercat3,null,false);
        Myviewholder myviewholder= new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull thirdrecyclercat3.Myviewholder holder, int position) {

        Glide.with(context).load(arrayListthird.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(arrayListthird.get(position).getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListthird.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView textView;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.imagerec3);
            textView = itemView.findViewById(R.id.textrec3);

        }
    }

    public void onitemclicklistner(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;
    }

}
