package com.example.retrofitapi;

import com.example.retrofitapi.constants.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static RetrofitSingleton mInstance;
    private Retrofit mRetrofit;

    //retrofitObject
    private RetrofitSingleton() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //for creating instance/object for retrofit
    public static RetrofitSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitSingleton();
        }
        return mInstance;
    }
    //for interface
    public SongsApi getJSONApi() {
        return mRetrofit.create(SongsApi.class);
    }
}
