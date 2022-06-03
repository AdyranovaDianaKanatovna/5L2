package com.example.a5l2;

import android.app.Application;

import com.example.a5l2.network.LoveApi;
import com.example.a5l2.network.RetrofitService;

public class App extends Application {
    public static LoveApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService retrofitService = new RetrofitService();
        api = retrofitService.getLoveApi();
    }
}
