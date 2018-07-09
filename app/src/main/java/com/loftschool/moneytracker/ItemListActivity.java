package com.loftschool.moneytracker;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private List<Record> mData= new ArrayList<>();
    private ItemsListAdapter mAdapter;
    private static final int VERTICAL_ITEM_SPACE = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        setTitle(R.string.activity_items_list_title);
        createData();
        mRecycleView = findViewById(R.id.list);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new ItemsListAdapter();
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));

    }

    private void createData() {
        mData.add(new Record("Молоко",42));
        mData.add(new Record("Хлеб",36));
        mData.add(new Record("Ракета",10));
        mData.add(new Record("Лего",658425125));
        mData.add(new Record("",0));
        mData.add(new Record("Тот самый ужин,который я оплатил за всех потому что платил картой",500000));
        mData.add(new Record("Курсы",50));
        mData.add(new Record("Монитор",630));
        mData.add(new Record("Стол",250));
        mData.add(new Record("Шкаф",5000));
        mData.add(new Record("Еда",200));
        mData.add(new Record("Апельсин",45));
        mData.add(new Record("Шоколад",85));
        mData.add(new Record("Чай",8595));
        mData.add(new Record("Лего",658425125));
        mData.add(new Record("",0));
        mData.add(new Record("Тот самый ужин,который я оплатил за всех потому что платил картой",500000));
        mData.add(new Record("Курсы",50));
        mData.add(new Record("Монитор",630));
        mData.add(new Record("Стол",250));
        mData.add(new Record("Шкаф",5000));
        mData.add(new Record("Еда",200));
        mData.add(new Record("Апельсин",45));
        mData.add(new Record("Шоколад",85));
        mData.add(new Record("Чай",8595));
    }

    private class ItemsListAdapter extends RecyclerView.Adapter<RecordViewHolder> {

        @NonNull
        @Override
        public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecordViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_record, parent, false));
        }

        @Override
        public void onBindViewHolder(RecordViewHolder holder, int position) {
            Record record = mData.get(position);
            holder.applyData(record);

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;


        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
        }
        public void applyData(Record record) {
            String priceText, finalText;
            String rub="\u20BD";
            title.setText(record.getTitle());
            priceText=String.valueOf(record.getPrice());
            priceText=priceText+"%1$s";
            finalText= String.format(priceText, rub);
            price.setText(finalText);

        }
    }
    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = verticalSpaceHeight;
            }
        }
    }


}
