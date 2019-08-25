package com.example.mysubmission41;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysubmission41.pojo.Movie;

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.ViewHolder> {

    Cursor cursor;

    private MovieFavFragment movieFavFragment;
    private final String TAG = "FavMovieAdapter";


    public void setLismovies( Cursor lismovies) {
        this.cursor = lismovies;
    }

    public FavMovieAdapter(MovieFavFragment movieFavFragment, Cursor list) {
        this.movieFavFragment = movieFavFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = getItem(position);
        String imgUrl = ApiConfig.IMAGE_URL + movie.getPosterPath();
        Glide.with(movieFavFragment).load(imgUrl).override(150, 175).into(holder.imgfav);
        holder.titlefav.setText(movie.getTitle());
        holder.datefav.setText(movie.getReleaseDate());

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

    private Movie getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Movie(cursor);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgfav;
        TextView titlefav, datefav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgfav = itemView.findViewById(R.id.img_item_photo_movie);
            titlefav = itemView.findViewById(R.id.tv_item_name_movie);
            datefav = itemView.findViewById(R.id.movie_final_date);
        }
    }
}
