package com.ariefzuhri.blu.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.blu.databinding.ActivitySearchBinding;
import com.ariefzuhri.blu.ui.main.home.MediaAdapter;
import com.ariefzuhri.blu.utils.ShimmerHelper;
import com.ariefzuhri.blu.viewmodel.ViewModelFactory;

import java.util.List;

import static com.ariefzuhri.blu.utils.Constants.EXTRA_MEDIA_ID;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_QUERY;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_QUERY_TYPE;
import static com.ariefzuhri.blu.utils.Constants.ORIENTATION_TYPE_VERTICAL;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MULTI_SEARCH;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ActivitySearchBinding binding;
    private MediaAdapter adapter;
    private SearchViewModel viewModel;
    private ShimmerHelper shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fabBack.setOnClickListener(view -> onBackPressed());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        adapter = new MediaAdapter(ORIENTATION_TYPE_VERTICAL);
        binding.recyclerView.setAdapter(adapter);

        shimmer = new ShimmerHelper(this, binding.shimmer, binding.recyclerView);
        shimmer.show();

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(SearchViewModel.class);
        //viewModel.setPage(1);

        viewModel.getHeader().observe(this, header -> binding.tvHeader.setText(header));
        viewModel.getGenres().observe(this, result -> {
            if (result != null) {
                switch (result.status) {
                    case LOADING: break;
                    case SUCCESS: adapter.setGenreList(result.data); break;
                    case ERROR: break;
                }
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_QUERY)){
            String query = intent.getStringExtra(EXTRA_QUERY);
            binding.searchView.setQuery(query, true);
            performQuery(query);
        } else if (intent.hasExtra(EXTRA_QUERY_TYPE)){
            int type = intent.getIntExtra(EXTRA_QUERY_TYPE, 0);
            viewModel.setQueryType(type);
            if (intent.hasExtra(EXTRA_MEDIA_ID)){ // Untuk rekomendasi
                int mediaId = intent.getIntExtra(EXTRA_MEDIA_ID, 0);
                viewModel.setMediaId(mediaId);
            }
            viewModel.getSearchResult().observe(this, observer);
        }

        binding.searchView.setOnQueryTextListener(this);
        binding.swipeRefreshLayout.setOnRefreshListener(() ->
                viewModel.getSearchResult().observe(this, observer));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //boolean hasSubmittedQuery = binding.tvHeader.getText().toString().equals(getString(R.string.search));
        binding.searchView.clearFocus();
        if (adapter.getItemCount() == 0){
            binding.scrollView.scrollTo(0, 0);
            binding.appBar.setExpanded(true);
            performQuery(query);
        } else {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra(EXTRA_QUERY, query);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void performQuery(String query){
        binding.tvHeader.setText(R.string.search);
        viewModel.setQueryType(QUERY_TYPE_MULTI_SEARCH);
        viewModel.setQuery(query);
        viewModel.getSearchResult().observe(this, observer);
    }

    private final Observer<List<MediaEntity>> observer = new Observer<List<MediaEntity>>() {
        @Override
        public void onChanged(List<MediaEntity> result) {
            adapter.setData(result);
            shimmer.hide();
            binding.swipeRefreshLayout.setRefreshing(false);
        }
    };
}