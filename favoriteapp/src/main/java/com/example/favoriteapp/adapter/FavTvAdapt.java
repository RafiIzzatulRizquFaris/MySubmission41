package com.example.favoriteapp.adapter;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favoriteapp.R;
import com.example.favoriteapp.fragment.TvFragment;

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

    }

    @Override
    public int getItemCount() {
        if (cursor === null){
            return 0;
        }
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
