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
import com.example.favoriteapp.R;
import com.example.favoriteapp.fragment.TvFragment;
import com.example.favoriteapp.pojo.TvShow;

public class FavTvAdapt extends RecyclerView.Adapter<FavTvAdapt.ViewHolder> {

    Cursor cursor;
    TvFragment tvFragment;

    public void setListTv(Cursor cursor) {
        this.cursor = cursor;
    }

    public FavTvAdapt(TvFragment tvFragment) {
        this.tvFragment = tvFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_favapp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TvShow tvShow = getItem(position);
        String imgUrl = "https://image.tmdb.org/t/p/w500/"+ tvShow.getPosterPath();
        Glide.with(tvFragment).load(imgUrl).override(150, 175).into(holder.imageView);
        holder.textView.setText(tvShow.getName());
        holder.textView2.setText(tvShow.getFirstAirDate());
    }

    private TvShow getItem(int position) {
        if (!cursor.moveToPosition(position)){
            throw new IllegalStateException("Position Invalid");
        }
        return new TvShow(cursor);
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
        if (cursor == null){
            return 0;
        }
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView, textView2;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item_photo_show);
            textView = itemView.findViewById(R.id.tv_item_name_show);
            textView2 = itemView.findViewById(R.id.show_final_date);
        }
    }
}
