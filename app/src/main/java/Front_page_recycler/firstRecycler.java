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

public class firstRecycler extends RecyclerView.Adapter<firstRecycler.Myviewholder> {

    ArrayList<Model_classs> arrayListtop;
    Context context ;
    Recyclerclicklistener recyclerclicklistener;

    public firstRecycler(ArrayList<Model_classs> arrayListtop, Context context) {

        this.arrayListtop = arrayListtop;
        this.context = context;

    }
    @NonNull
    @Override
    public firstRecycler.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.firstrecycler,null,false);
        Myviewholder myviewholder= new Myviewholder(view);
        return myviewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull firstRecycler.Myviewholder holder, int position) {

        Glide.with(context).load(arrayListtop.get(position).getImage()).into(holder.imageView);
        holder.text.setText(arrayListtop.get(position).getName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {

        return arrayListtop.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView text;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.imagerec1);
            text=itemView.findViewById(R.id.textrec1);

        }
    }
    public void onitemclicklistner(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;
    }
}
