package com.example.dailytask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

private List<String> datalist;
private int SelectedItem;

private Context context;
public CustomAdapter(List<String>lista,Context con){



             this.datalist= lista;
        this.context=con;
    }


    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
    //info
    String data= datalist.get(position);

        holder.textView.setText(data);

        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = 250;
        holder.itemView.setLayoutParams(layoutParams);

       //color
        if (SelectedItem== position){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.bocchi));

        }else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.back));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int prevselect = SelectedItem;
        SelectedItem = position;
        String mensaje = "el elemento seleccionado es:" + datalist.get(position);
        notifyItemChanged(prevselect);
        notifyItemChanged(SelectedItem);

    }
});

    }

    @Override
    public int getItemCount() {

    return datalist.size();

    }


   public class ViewHolder extends RecyclerView.ViewHolder {
public TextView textView;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           textView = itemView.findViewById(R.id.rvdetail);
       }
   }


}
