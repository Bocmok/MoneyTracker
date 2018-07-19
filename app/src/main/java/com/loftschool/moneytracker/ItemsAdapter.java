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
//    public ItemsAdapter(){
//        createData();
//    }

    public void setData(List<Item> items){
        this.data=items;
        notifyDataSetChanged();
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

//    private void createData() {
//        data.add(new Item("Молоко",42));
//        data.add(new Item("Хлеб",36));
//        data.add(new Item("Ракета",10));
//        data.add(new Item("Лего",658425125));
//        data.add(new Item("",0));
//        data.add(new Item("Тот самый ужин,который я оплатил за всех потому что платил картой",500000));
//        data.add(new Item("Курсы",50));
//        data.add(new Item("Монитор",630));
//        data.add(new Item("Стол",250));
//        data.add(new Item("Шкаф",5000));
//        data.add(new Item("Еда",200));
//        data.add(new Item("Апельсин",45));
//        data.add(new Item("Шоколад",85));
//        data.add(new Item("Чай",8595));
//        data.add(new Item("Лего",658425125));
//        data.add(new Item("",0));
//        data.add(new Item("Тот самый ужин,который я оплатил за всех потому что платил картой",500000));
//        data.add(new Item("Курсы",50));
//        data.add(new Item("Монитор",630));
//        data.add(new Item("Стол",250));
//        data.add(new Item("Шкаф",5000));
//        data.add(new Item("Еда",200));
//        data.add(new Item("Апельсин",45));
//        data.add(new Item("Шоколад",85));
//        data.add(new Item("Чай",8595));
//    }

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
            priceText=String.valueOf(record.price);
            priceText=priceText+"%1$s";
            finalText= String.format(priceText, rub);
            price.setText(finalText);

        }
    }

}

