package com.example.mysubmission41.favorite.fragmentfav;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mysubmission41.R;
import com.example.mysubmission41.activity.DetailShowActivity;
import com.example.mysubmission41.adapter.TvShowAdapter;
import com.example.mysubmission41.click.ItemClickSupport;
import com.example.mysubmission41.pojo.TvShow;

import java.util.ArrayList;
import java.util.List;

import static com.example.mysubmission41.favorite.DatabaseContract.CONTENT_URI_TV;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;


    public TvShowFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show_fav, container, false);
        swipeRefreshLayout = view.findViewById(R.id.sr_favorite_tvshow);
        recyclerView = view.findViewById(R.id.rv_favorite_tvshow);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new loadData().execute();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            new loadData().execute();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private class loadData extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContext().getContentResolver().query(
                    CONTENT_URI_TV,
                    null,
                    null,
                    null,
                    null
            );
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            List<TvShow> list = new ArrayList<>();
            while (cursor.moveToNext()){
                TvShow data = new TvShow(cursor);
                list.add(data);
            }
            TvShowAdapter tvShowAdapter = new TvShowAdapter(getContext(), list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(tvShowAdapter);
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) -> {
                Intent intent = new Intent(getActivity(), DetailShowActivity.class);
                intent.putExtra(DetailShowActivity.EXTRA_SHOW, list.get(position));
                startActivity(intent);
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
