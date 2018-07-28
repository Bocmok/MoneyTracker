package com.loftschool.moneytracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.RecordViewHolder> {

    private List<Item> data= new ArrayList<>();
    private ItemsAdapterListener listener;

    public void setListener(ItemsAdapterListener listener){
        this.listener=listener;
    }

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
        holder.applyData(item,position, listener, selections.get(position,false));

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    private SparseBooleanArray selections =new SparseBooleanArray();

    public void toggleSelection(int position){
        if (selections.get(position,false)){
            selections.delete(position);
        }else{
            selections.put(position,true);
        }
        notifyItemChanged(position);

    }

    void clearSelections(){
        selections.clear();
        notifyDataSetChanged();
    }

    int getSelectedItemCount(){
        return selections.size();
    }

    List<Integer> getSelectedItems(){
        List<Integer> items=new ArrayList<>(selections.size());
        for (int i=0; i<selections.size(); i++){
            items.add(selections.keyAt(i));
        }
        return items;
    }

    Item remove(int pos){
        final Item item=data.remove(pos);
        notifyItemRemoved(pos);
        return item;
    }

    static class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;


        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
        }
        public void applyData(final Item record, final int position, final ItemsAdapterListener listener, boolean selected) {
            String priceText, finalText;
            String rub="\u20BD";
            title.setText(record.name);
            priceText=record.price;
            priceText=priceText+"%1$s";
            finalText= String.format(priceText, rub);
            price.setText(finalText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onItemClick(record, position);
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null){
                        listener.OnItemLongClick(record,position);
                    }
                    return true;
                }
            });

            itemView.setActivated(selected);

        }
    }

}

