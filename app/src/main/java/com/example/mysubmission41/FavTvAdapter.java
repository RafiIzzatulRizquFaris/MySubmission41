package com.example.mysubmission41;

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
import com.example.mysubmission41.pojo.TvShow;

public class FavTvAdapter extends RecyclerView.Adapter<FavTvAdapter.ViewHolder> {

    private Cursor cursor;

    private TvShowFavFragment tvShowFavFragment;

    private final String TAG = "FavoriteTvAdapter";


    public void setListTvs(Cursor listTvs) {
        this.cursor = listTvs;
    }

    public FavTvAdapter(TvShowFavFragment tvShowFavFragment, Cursor list) {
        this.tvShowFavFragment = tvShowFavFragment;
    }


    @NonNull
    @Override
    public FavTvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_tvshow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TvShow tvShow = getItem(position);
        Log.d(TAG, "FavTvAdapter: " + tvShow.getId());
        String imgUrl = ApiConfig.IMAGE_URL + tvShow.getPosterPath();
        Glide.with(tvShowFavFragment).load(imgUrl).override(150, 175).into(holder.tvimgfav);
        holder.tvtitlefav.setText(tvShow.getName());
        holder.tvreleasefav.setText(tvShow.getFirstAirDate());

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
        return  cursor.getCount();
    }

    private TvShow getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new TvShow(cursor);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvtitlefav, tvreleasefav;
        ImageView tvimgfav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvimgfav = itemView.findViewById(R.id.img_item_photo_show);
            tvtitlefav = itemView.findViewById(R.id.tv_item_name_show);
            tvreleasefav = itemView.findViewById(R.id.show_final_date);
        }
    }
}
