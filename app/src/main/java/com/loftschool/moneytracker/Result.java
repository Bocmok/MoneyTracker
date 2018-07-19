package com.loftschool.moneytracker;

import android.text.TextUtils;

public class Result {
    String status;
    public boolean isSuccess() {
        return TextUtils.equals(status, "success");
    }
}
