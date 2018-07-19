package com.loftschool.moneytracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @GET("/items")
    Call<List<Item>> getItem(@Query("type") String type);


    @POST("/items/add")
    Call<Void> addItem();

}
