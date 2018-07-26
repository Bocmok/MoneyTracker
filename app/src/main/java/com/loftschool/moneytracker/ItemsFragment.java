package com.loftschool.moneytracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


    private SwipeRefreshLayout refresh;

    public static final int ADD_ITEM_REQUEST_CODE=123;


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
        adapter.setListener(new AdapterListener());

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
            if (item.type.equals(type)){
                adapter.addItem(item);
            }
        }


        super.onActivityResult(requestCode, resultCode, data);

    }



    /*   ACTION MODE  */

    private ActionMode actionMode=null;

    private void removeSelectedItems(){
        for (int i = adapter.getSelectedItems().size()-1; i>=0; i--){
            adapter.remove(adapter.getSelectedItems().get(i));
        }
        actionMode.finish();
    }

    private  class AdapterListener implements ItemsAdapterListener{

        @Override
        public void onItemClick(Item item, int position) {
            if (isInActionMode()){
                toggleSelection(position);
            }
        }

        @Override
        public void OnItemLongClick(Item item, int position) {
            if(isInActionMode()) {
                return;
            }

            actionMode=((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
            toggleSelection(position);
        }

        private boolean isInActionMode(){
            return actionMode != null;
        }

        private void toggleSelection(int position){
            adapter.toggleSelection(position);
        }
    }


    private ActionMode.Callback actionModeCallback =new ActionMode.Callback(){

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater=new MenuInflater(getContext());
            inflater.inflate(R.menu.items_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.remove:
                    showDialog();
                    //removeSelectedItems();
                break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adapter.clearSelections();
            actionMode=null;
        }
    };

    private void showDialog(){
        ConfirmationDialog dialog = new ConfirmationDialog();
        dialog.show(getFragmentManager(),"ConfirmationDialog");
    }

}
