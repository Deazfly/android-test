package com.example.android_test;


import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NetworkService {


    OkHttpClient okHttpClient = new OkHttpClient();

    public void sendRequest(Callback callback) {
        Request request = new Request.Builder()
                .url("https://www.mirea.ru/")
                .build();

        okHttpClient.newCall(request).enqueue(callback);
    }

    public void cancerRequest() {
        okHttpClient.dispatcher().cancelAll();
    }

}
