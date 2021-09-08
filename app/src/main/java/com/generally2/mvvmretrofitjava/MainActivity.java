package com.generally2.mvvmretrofitjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.generally2.mvvmretrofitjava.adapter.MovieListAdapter;
import com.generally2.mvvmretrofitjava.model.MovieModel;
import com.generally2.mvvmretrofitjava.viewmodel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView noResult = findViewById(R.id.noResultTv);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieListAdapter(this, movieModelList);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getMovieListObserver().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null){
                    movieModelList = movieModels;
                    adapter.setMovieList(movieModels);
                }
                else {
                    noResult.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.makeApiCall();

    }
}