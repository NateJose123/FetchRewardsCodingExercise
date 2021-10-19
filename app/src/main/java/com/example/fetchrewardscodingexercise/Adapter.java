package com.example.fetchrewardscodingexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<HiringDataItem> hiringDataItemList;

    public Adapter(Context ctx, List<HiringDataItem> hiringDataItemList){
        this.inflater = LayoutInflater.from(ctx);
        this.hiringDataItemList = hiringDataItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.listId.setText(Integer.toString(hiringDataItemList.get(position).getListID())); //convert to string to work with textView
        holder.itemName.setText(hiringDataItemList.get(position).getItemName());

    }

    @Override
    public int getItemCount() {
        return hiringDataItemList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView listId, itemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listId = itemView.findViewById(R.id.listId);
            itemName = itemView.findViewById(R.id.itemName);

            // handle onClick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Nothing to do yet.
                }
            });
        }
    }
}
