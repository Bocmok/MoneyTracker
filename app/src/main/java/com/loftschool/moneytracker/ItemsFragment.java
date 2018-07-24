package com.loftschool.moneytracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemsFragment extends Fragment {
    private RecyclerView recycler;
    private ItemsAdapter adapter;

    private static final String TYPE_KEY="type";
    private String type;
    private Api api;

    private FloatingActionButton fab;
    private SwipeRefreshLayout refresh;

    private static final int ADD_ITEM_REQUEST_CODE=123;

    private static final String TAG = "ItemsFragment";

    public static ItemsFragment createItemsFragment(String type){
        ItemsFragment fragment=new ItemsFragment();

        Bundle bundle=new Bundle();
        bundle.putString(ItemsFragment.TYPE_KEY, type);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter =new ItemsAdapter();
        Bundle bundle=getArguments();
        type=bundle.getString(TYPE_KEY,Item.TYPE_UNKNOWN);
        if (type.equals(Item.TYPE_UNKNOWN)){
            throw new IllegalArgumentException("Unknown type");
        }
        api=((App) getActivity().getApplication()).getApi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_items, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.list);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        fab=view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AddItemActivity.class);
                intent.putExtra(AddItemActivity.TYPE_KEY,type);
                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);

                //неявный Intent
//                Intent intent=new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://pikabu.ru"));
//                startActivity(intent);
            }
        });

        refresh=view.findViewById(R.id.refresh);
        refresh.setColorSchemeColors(Color.GREEN,Color.MAGENTA,Color.CYAN);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
            }
        });

        loadItems();
    }

    private void loadItems(){
        Call<List<Item>> call=api.getItem(type);

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                adapter.setData(response.body());
                refresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==ADD_ITEM_REQUEST_CODE&&resultCode== Activity.RESULT_OK){
            Item item=data.getParcelableExtra("item");
//            String name=data.getStringExtra("name");
//            String price=data.getStringExtra("price");
              Log.i(TAG, "onActivityResult: name = "+item.name+" price = "+item.price);
              adapter.addItem(item);
        }


        super.onActivityResult(requestCode, resultCode, data);

    }

}
