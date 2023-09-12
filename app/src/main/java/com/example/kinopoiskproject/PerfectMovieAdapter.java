package com.example.kinopoiskproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PerfectMovieAdapter extends RecyclerView.Adapter<PerfectMovieAdapter.MovieViewHolder>{
    private ArrayList<Film> movie;


    public PerfectMovieAdapter(ArrayList<Film> movie){
        this.movie = movie;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movie.get(position));
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public void clearValues(){
        movie.clear();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
            });
        }

        public void bind(Film movie){
            TextView Name = itemView.findViewById(R.id.textMovie);
            TextView Rating = itemView.findViewById(R.id.ratingView);
            TextView Year = itemView.findViewById(R.id.yearFilm);
            ImageView Iv = itemView.findViewById(R.id.imageMovie);

            Picasso.get().load(movie.posterUrlPreview).into(Iv);

            Year.setText("Год выпуска: " + movie.year);
            Name.setText(movie.nameRu);
            Rating.setText("Рейтинг: " + movie.rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, InfoAboutFilm.class);
                    intent.putExtra("MOVIE", movie);
                    context.startActivity(intent);
                }
            });
        }
    }
}
