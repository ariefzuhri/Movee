package com.ariefzuhri.movee.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.ariefzuhri.movee.R;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.databinding.ActivitySearchBinding;
import com.ariefzuhri.movee.ui.main.home.MediaAdapter;
import com.ariefzuhri.movee.utils.ShimmerHelper;
import com.ariefzuhri.movee.viewmodel.ViewModelFactory;
import com.ariefzuhri.movee.vo.Resource;

import java.util.List;

import static com.ariefzuhri.movee.utils.AppUtils.showToast;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_MEDIA_ID;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_QUERY;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_QUERY_TYPE;
import static com.ariefzuhri.movee.utils.Constants.ORIENTATION_TYPE_VERTICAL;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MULTI_SEARCH;

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

        shimmer = new ShimmerHelper(this, binding.shimmer, binding.recyclerView, binding.layoutEmpty);

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(SearchViewModel.class);
        viewModel.setPage(1);

        viewModel.getSearchResult().observe(this, observer);
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
            if (intent.hasExtra(EXTRA_MEDIA_ID)){ // Untuk rekomendasi
                int mediaId = intent.getIntExtra(EXTRA_MEDIA_ID, 0);
                viewModel.setMediaId(mediaId);
            }
            int type = intent.getIntExtra(EXTRA_QUERY_TYPE, 0);
            viewModel.setQueryType(type);
        }

        binding.searchView.setOnQueryTextListener(this);
        binding.swipeRefreshLayout.setOnRefreshListener(() ->
                binding.swipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        binding.searchView.clearFocus();
        boolean hasSubmittedQuery = getIntent().hasExtra(EXTRA_QUERY) &&
                !query.equals(getIntent().getStringExtra(EXTRA_QUERY));
        if (adapter.getItemCount() == 0 && !hasSubmittedQuery){
            binding.scrollView.scrollTo(0, 0);
            binding.appBar.setExpanded(true);
            performQuery(query);
        } else { // Jika sudah ada data di adapter, kita buka activity baru
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
        viewModel.setQuery(query);
        viewModel.setQueryType(QUERY_TYPE_MULTI_SEARCH);
    }

    private final Observer<Resource<List<MediaEntity>>> observer = new Observer<Resource<List<MediaEntity>>>() {
        @Override
        public void onChanged(Resource<List<MediaEntity>> result) {
            if (result != null){
                switch (result.status) {
                    case SUCCESS:
                        adapter.setData(result.data);
                        shimmer.hide(false);
                        binding.swipeRefreshLayout.setRefreshing(false);
                        break;
                    case EMPTY:
                        shimmer.hide(true);
                        break;
                    case ERROR:
                        showToast(getApplicationContext(), "Wkwk");
                        break;
                    case LOADING:
                        shimmer.show();
                        break;
                }
            }
        }
    };
}