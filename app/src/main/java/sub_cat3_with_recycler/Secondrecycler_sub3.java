package sub_cat3_with_recycler;

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

import java.util.ArrayList;

public class Secondrecycler_sub3 extends RecyclerView.Adapter<Secondrecycler_sub3.Myviewholder> {


    ArrayList<Model_classs> arrayListthird;
    Context context ;

    public Secondrecycler_sub3(ArrayList<Model_classs> arrayListthird, Context context) {
        this.arrayListthird = arrayListthird;
        this.context = context;
    }

    @NonNull
    @Override
    public Secondrecycler_sub3.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_sub_cat3,null,false);
        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Secondrecycler_sub3.Myviewholder holder, int position) {


        Glide.with(context).load(arrayListthird.get(position).getImage()).into(holder.imageView);
        holder.text2.setText(arrayListthird.get(position).getName());
        // holder.text.setText(arrayListmiddle.get(position).getValue());


    }

    @Override
    public int getItemCount() {
        return arrayListthird.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView text2;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.imagesub3);
            text2=itemView.findViewById(R.id.textsub3);
        }
    }
}
