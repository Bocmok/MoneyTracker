package com.loftschool.moneytracker.Api;

import com.loftschool.moneytracker.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("items/remove")
    Call<RemoveItemResult> removeItem(@Query("id") int id);

    @GET("auth")
    Call<AuthResult> auth(@Query("social_user_id") String userId);

    @GET("items")
    Call<List<Item>> getItem(@Query("type") String type);

    @POST("items/add")
    Call<AddItemResult> addItem(@Query("name") String name, @Query("price") String price, @Query("type") String type);

    @GET("balance")
    Call<BalanceResult>balance();


}
