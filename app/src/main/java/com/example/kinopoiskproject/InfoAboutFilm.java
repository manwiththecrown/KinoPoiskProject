package com.example.kinopoiskproject;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoAboutFilm extends AppCompatActivity {
    String Vidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_info);

        Intent intent = getIntent();
        Film movie = (Film) intent.getSerializableExtra("MOVIE");

        ApiInterface apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        Call<Trailers> trailerItem = apiInterface.getTrailer(movie.filmId);

        trailerItem.enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                if(response.isSuccessful()){
                    Trailers listTrailer = response.body();
                    ArrayList<TrailerItem> trailer = listTrailer.items;
                    Vidos = "RYl8C1rJDNc";
                    for (int i = 0; i < trailer.size(); i++) {
                        String site = trailer.get(i).site;
                        if(Objects.equals(site, "YOUTUBE")){
                            String tempUrl = trailer.get(i).url;
                            String[] temp = tempUrl.split("/");
                            if(!Objects.equals(temp[3], "v")) {
                                String[] ytIdVid = temp[3].split("=");
                                if (ytIdVid.length == 2) {
                                    Vidos = ytIdVid[1];
                                } else {
                                    Vidos = ytIdVid[0];
                                }
                            }
                            else{
                                String[] ytIdVid = temp[4].split("=");
                                if (ytIdVid.length == 2) {
                                    Vidos = ytIdVid[1];
                                } else {
                                    Vidos = ytIdVid[0];
                                }
                            }
                            break;
                        }
                    }

                }else{ }
            }

            @Override
            public void onFailure(Call<Trailers> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        YouTubePlayerView YTView = findViewById(R.id.trailer);
        getLifecycle().addObserver(YTView);

        YTView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(Vidos, 0);
            }
        });

        TextView name = findViewById(R.id.name);
        TextView date = findViewById(R.id.date);
        TextView ratingCount = findViewById(R.id.about);
        TextView rating = findViewById(R.id.rating);
        ImageView image = findViewById(R.id.image);
        TextView tr = findViewById(R.id.trail);


        name.setText(movie.nameRu);
        date.setText("Год выпуска: " + movie.year);
        ratingCount.setText("Колличество отзывов: " + movie.ratingVoteCount);
        rating.setText("Рейтинг: " + movie.rating);
        tr.setText("Трейлер:");


        Picasso.get().load(movie.posterUrlPreview).into(image);
}
}
