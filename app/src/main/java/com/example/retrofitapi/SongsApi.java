package com.example.retrofitapi;

import com.example.retrofitapi.model.SearchResultModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongsApi {

    @GET("search")
    Call<SearchResultModel> getSearchResults(@Query("term") CharSequence searchTerm);

    @GET("search?term=jack+johnson")
    Call<SearchResultModel> getJack();

}
