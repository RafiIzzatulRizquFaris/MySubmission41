package com.example.mysubmission41.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mysubmission41.activity.DetailShowActivity;
import com.example.mysubmission41.R;
import com.example.mysubmission41.adapter.TvShowAdapter;
import com.example.mysubmission41.click.ItemClickSupport;
import com.example.mysubmission41.pojo.TvShow;
import com.example.mysubmission41.viewmodel.TvShowViewModel;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TvShowViewModel tvShowViewModel;
        recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_category_show);
        progressBar = getActivity().findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvShow().observe(this, getTvShow);
    }

    private Observer<List<TvShow>> getTvShow = new Observer<List<TvShow>>() {
        @Override
        public void onChanged(List<TvShow> tvShowList) {
            TvShowAdapter tvShowAdapter;
            tvShowAdapter = new TvShowAdapter(getContext(), tvShowList);
            recyclerView.setAdapter(tvShowAdapter);
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) -> {
                Intent intent = new Intent(getActivity(), DetailShowActivity.class);
                intent.putExtra(DetailShowActivity.EXTRA_SHOW, tvShowList.get(position));
                startActivity(intent);
            });
            if (tvShowList != null) {
                progressBar.setVisibility(View.GONE);
            }
        }
    };
}
