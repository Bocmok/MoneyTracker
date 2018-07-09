package com.loftschool.moneytracker;

public class Record {
    private final String title;
    private final int price;
    private String comment;

    public Record(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }
}
