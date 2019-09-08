package com.example.mysubmission41.favorite;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.R;
import com.example.mysubmission41.pojo.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.ViewHolder> {
    Context context;
    List<Movie> list;
    private final String TAG = "FavMovieAdapter";


    public void setLismovies(List<Movie> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public FavMovieAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = list.get(position);
        Log.e(TAG, "Movie Id : " + list.size());

        String imgUrl = ApiConfig.IMAGE_URL + movie.getPosterPath();
        Glide.with(context).load(imgUrl).override(150, 175).into(holder.imgfav);
        holder.titlefav.setText(movie.getTitle());
        holder.datefav.setText(movie.getReleaseDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
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
