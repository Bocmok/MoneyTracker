package com.loftschool.moneytracker;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



public class ItemsActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private ItemsAdapter adapter;
    private static final int VERTICAL_ITEM_SPACE = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        setTitle(R.string.activity_items_list_title);

        adapter =new ItemsAdapter();

        recycler = findViewById(R.id.list);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));

    }





}
