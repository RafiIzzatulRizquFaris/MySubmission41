package com.example.mysubmission41.searching;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysubmission41.R;

import java.util.Objects;

import static com.example.mysubmission41.searching.MSearchActivity.EXTRA_QUERY_MOVIE;

public class MovieSearchActivity extends AppCompatActivity {

    private void setActionBarTitle(){
        Objects.requireNonNull(getSupportActionBar()).setTitle("Search Movie");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);
        setActionBarTitle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null){
            SearchView searchView = (SearchView) (menu.findItem(R.id.search).getActionView());
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Intent intent = new Intent(MovieSearchActivity.this, MSearchActivity.class);
                    intent.putExtra(EXTRA_QUERY_MOVIE, query);
                    startActivity(intent);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }
}
