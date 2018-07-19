package com.loftschool.moneytracker;

public class Item {
    public String name;
    public int price;
    public String type;
    public int id;

    public static final String TYPE_UNKNOWN="unknown";
    public static final String TYPE_INCOMES="incomes";
    public static final String TYPE_EXPENSES="expenses";

    public Item(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type=type;
    }



}
