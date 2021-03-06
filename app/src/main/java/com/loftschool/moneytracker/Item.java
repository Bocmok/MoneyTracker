package com.loftschool.moneytracker;

import android.os.Parcel;
import android.os.Parcelable;


public class Item implements Parcelable{
    public String name;
    public String price;
    public String type;
    public int id;

    public static final String TYPE_UNKNOWN="unknown";
    public static final String TYPE_INCOMES="incomes";
    public static final String TYPE_EXPENSES="expenses";

    public Item(String name, String price, String type) {
        this.name = name;
        this.price = price;
        this.type=type;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readString();
        type = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(type);

    }
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
