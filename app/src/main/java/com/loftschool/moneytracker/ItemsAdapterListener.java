package com.loftschool.moneytracker;

public interface ItemsAdapterListener {

    void onItemClick(Item item, int position);
    void OnItemLongClick(Item item, int position);
}
