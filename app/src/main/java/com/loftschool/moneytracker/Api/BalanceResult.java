package com.loftschool.moneytracker.Api;

import com.google.gson.annotations.SerializedName;

public class BalanceResult {

    public String status;
    @SerializedName("total_expenses")
    public int expense;
    @SerializedName("total_income")
    public int income;

}
