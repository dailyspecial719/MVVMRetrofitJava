package com.generally2.mvvmretrofitjava.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.generally2.mvvmretrofitjava.model.MovieModel;
import com.generally2.mvvmretrofitjava.network.APIService;
import com.generally2.mvvmretrofitjava.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> moviesList;
    public MovieListViewModel() {
        moviesList= new MutableLiveData<>();
    }


    public MutableLiveData<List<MovieModel>> getMovieListObserver(){
        return moviesList;
    }
    public void makeApiCall(){
        APIService apiService = RetroInstance. getRetrofitClient().create(APIService.class);
        Call<List<MovieModel>> call = apiService.getMovieList();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                moviesList.postValue(response.body());

            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                moviesList.postValue(null);

            }
        });
    }

}
