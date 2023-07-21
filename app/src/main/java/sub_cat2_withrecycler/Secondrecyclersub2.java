package sub_cat2_withrecycler;

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

import Subcat1__with_recycler.SecondRecycler;

public class Secondrecyclersub2 extends RecyclerView.Adapter<Secondrecyclersub2.Myviewholder> {


    ArrayList<Model_classs> arrayListmiddle;
    Context context ;

    public Secondrecyclersub2(ArrayList<Model_classs> arrayListmiddle, Context context) {
        this.arrayListmiddle = arrayListmiddle;
        this.context = context;
    }



    @NonNull
    @Override
    public Secondrecyclersub2.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.secondrecycler_subcat2,null,false);
        Myviewholder myviewholder=new Myviewholder(view);
        return (myviewholder);
    }



    @Override
    public void onBindViewHolder(@NonNull Secondrecyclersub2.Myviewholder holder, int position) {

        Glide.with(context).load(arrayListmiddle.get(position).getImage()).into(holder.imageView);
        holder.text2.setText(arrayListmiddle.get(position).getName());



    }

    @Override
    public int getItemCount() {
        return arrayListmiddle.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text2;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imagesubcat2);
            text2=itemView.findViewById(R.id.textsubcat2);

        }
    }
}
