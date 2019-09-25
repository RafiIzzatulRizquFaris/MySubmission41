package com.example.favoriteapp;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavMovieAdapt extends RecyclerView.Adapter<FavMovieAdapt.ViewHolder> {

    Cursor cursor;
    private MovieFragment movieFragment;

    public setListMovie(Cursor cursor) {
        this.cursor = cursor;
    }

    public FavMovieAdapt(MovieFragment movieFragment) {
        this.movieFragment = movieFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_favapp, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView, textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item_movie);
            textView = itemView.findViewById(R.id.tv_item_name_movie);
            textView2 = itemView.findViewById(R.id.movie_final_date_fav);
        }
    }
}
