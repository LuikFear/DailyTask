package com.example.dailytask;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<ListItem > itemList;
    private int selectedItem = RecyclerView.NO_POSITION;
    private  DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    public CustomAdapter(List<ListItem> itemList) {
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem item = itemList.get(position);

        holder.textViewTitle.setText(item.getTitle());
        holder.textViewDescription.setText(item.getDesctiption());

        if (position == selectedItem) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.greenD));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int prevSelected = selectedItem;
                selectedItem = position;
                notifyItemChanged(prevSelected);
                notifyItemChanged(selectedItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
