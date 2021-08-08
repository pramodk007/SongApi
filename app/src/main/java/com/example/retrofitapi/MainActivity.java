package com.example.retrofitapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitapi.databinding.ActivityMainBinding;
import com.example.retrofitapi.model.ResultModel;
import com.example.retrofitapi.model.SearchResultModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyAdapter adapter;
    String search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.rvSongsList.setLayoutManager(new GridLayoutManager(this, 2));
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSongs();
            }
        });

    }

    private void getSongs() {
        search = binding.edtSearchSong.getText().toString();
        if(search.isEmpty()){
            Toast.makeText(getApplicationContext(), "enter the artist/song name",Toast.LENGTH_SHORT).show();
        }
        char ch = '+';
        search = search.replace(' ', ch);
        RetrofitSingleton.getInstance().getJSONApi().getSearchResults(search).enqueue(new Callback<SearchResultModel>() {
            @Override
            public void onResponse(Call<SearchResultModel> call, Response<SearchResultModel> response) {
                if(response.isSuccessful() && response.body() != null){

                    List<ResultModel> resultModelList = response.body().getResultModels();
                    if (resultModelList.size() > 0) {
                        adapter = new MyAdapter(resultModelList, getApplicationContext());
                        binding.rvSongsList.setAdapter(adapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "no result to show",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<SearchResultModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed to fetch the songs",Toast.LENGTH_SHORT).show();
            }
        });
    }
}