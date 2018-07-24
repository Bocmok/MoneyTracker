package com.loftschool.moneytracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.RecordViewHolder> {

    private List<Item> data= new ArrayList<>();

    public void setData(List<Item> items){
        this.data=items;
        notifyDataSetChanged();
    }

    public void addItem(Item item){
        data.add(item);
        notifyItemInserted(data.size());
    }

    @NonNull
    @Override
    public ItemsAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsAdapter.RecordViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.RecordViewHolder holder, int position) {
        Item item = data.get(position);
        holder.applyData(item);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;


        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
        }
        public void applyData(Item record) {
            String priceText, finalText;
            String rub="\u20BD";
            title.setText(record.name);
            priceText=record.price;
            priceText=priceText+"%1$s";
            finalText= String.format(priceText, rub);
            price.setText(finalText);

        }
    }

}

