package com.example.kinopoiskproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    FirstFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        MainActivity mainActivity = new MainActivity();
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerFuture = view.findViewById(R.id.recycleFuture);

        ApiInterface apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        Call<Root> movieItem = apiInterface.getMoviesTop("TOP_250_BEST_FILMS", Page.numberPageFragment1);

        movieItem.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if(response.isSuccessful()){

                    recyclerFuture.setLayoutManager(new LinearLayoutManager((view.getContext())));
                    recyclerFuture.setHasFixedSize(true);

                    Root listMovie = response.body();
                    recyclerFuture.setAdapter(new PerfectMovieAdapter(listMovie.films));
                }else{ }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
