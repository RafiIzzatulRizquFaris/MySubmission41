package com.example.mysubmission41;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static com.example.mysubmission41.DatabaseContract.CONTENT_URI_TV;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavFragment extends Fragment {

    private FavTvAdapter favTvAdapter;
    private Cursor list;
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

        new loadData().execute();
        showListData();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            new loadData().execute();
            showListData();
            swipeRefreshLayout.setRefreshing(false);
        });

        return view;
    }

    private void showListData() {
        favTvAdapter = new FavTvAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(favTvAdapter);
        favTvAdapter.setListTvs(list);
        recyclerView.setHasFixedSize(true);
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

            list = cursor;
            favTvAdapter.setListTvs(list);
            favTvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
