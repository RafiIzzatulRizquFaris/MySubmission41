package com.example.favoriteapp.adapter;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.favoriteapp.Movie;
import com.example.favoriteapp.R;
import com.example.favoriteapp.fragment.MovieFragment;

public class FavMovieAdapt extends RecyclerView.Adapter<FavMovieAdapt.ViewHolder> {

    private Cursor cursor;
    private MovieFragment movieFragment;

    public void setListMovie(Cursor cursor) {
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
        final Movie movie = getItem(position);
        String imgUrl = "https://image.tmdb.org/t/p/w500/"+ movie.getPosterPath();
        Glide.with(movieFragment).load(imgUrl).override(150, 175).into(holder.imageView);
        holder.textView.setText(movie.getTitle());
        holder.textView2.setText(movie.getReleaseDate());
    }

    private Movie getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Movie(cursor);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView, textView2;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item_movie);
            textView = itemView.findViewById(R.id.tv_item_name_movie);
            textView2 = itemView.findViewById(R.id.movie_final_date_fav);
        }
    }
}
