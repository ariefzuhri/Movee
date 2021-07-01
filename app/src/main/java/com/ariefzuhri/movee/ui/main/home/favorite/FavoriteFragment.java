package com.ariefzuhri.movee.ui.main.home.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ariefzuhri.movee.R;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.utils.FabMenuHelper;
import com.ariefzuhri.movee.utils.FilterFavorite;
import com.ariefzuhri.movee.databinding.FragmentFavoriteBinding;
import com.ariefzuhri.movee.viewmodel.ViewModelFactory;
import com.google.android.material.chip.ChipGroup;

import org.jetbrains.annotations.NotNull;

import static com.ariefzuhri.movee.utils.AppUtils.showToast;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_BOOKMARK_FILTER;

public class FavoriteFragment extends Fragment implements View.OnClickListener, ChipGroup.OnCheckedChangeListener, FilterFavoriteDialog.FavoriteFilterDialogListener, FavoriteAdapter.FavoriteAdapterListener {

    private FilterFavorite filter;
    private FavoriteViewModel viewModel;
    private FragmentFavoriteBinding binding;
    private FavoriteAdapter adapter;
    private FavoriteAdapter editAdapter;

    private boolean editView;

    public FavoriteFragment() {}

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerView.setHasFixedSize(false);
        adapter = new FavoriteAdapter();
        binding.recyclerView.setAdapter(adapter);

        editAdapter = new FavoriteAdapter(this);
        editView = false;
        binding.chipAll.setChecked(true);

        if (getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());
            viewModel = new ViewModelProvider(this, factory).get(FavoriteViewModel.class);
            viewModel.getFilter().observe(getViewLifecycleOwner(), filter -> this.filter = filter);
            viewModel.getFavorites().observe(getViewLifecycleOwner(), result -> {
                adapter.submitList(result);
                editAdapter.submitList(result);

                if (result.isEmpty()) binding.layoutEmpty.setVisibility(View.VISIBLE);
                else binding.layoutEmpty.setVisibility(View.INVISIBLE);
            });

            new FabMenuHelper(getContext(), binding.fabOption, binding.fabEdit, binding.fabFilter);
            binding.fabEdit.setOnClickListener(this);
            binding.fabFilter.setOnClickListener(this);

            binding.cgCategory.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(ChipGroup group, int checkedId) {
        if (filter == null) return;
        if (checkedId == R.id.chip_all) {
            filter.setShowAllMediaType(true);
            filter.setShowMovieOnly(false);
            filter.setShowTVOnly(false);
        } else if (checkedId == R.id.chip_movie) {
            filter.setShowAllMediaType(false);
            filter.setShowMovieOnly(true);
            filter.setShowTVOnly(false);
        } else if (checkedId == R.id.chip_tv) {
            filter.setShowAllMediaType(false);
            filter.setShowMovieOnly(false);
            filter.setShowTVOnly(true);
        }
        viewModel.setFilter(filter);
    }

    @Override
    public void onFilterApplied(@NotNull FilterFavorite filter) {
        if (filter.isShowAllMediaType()) binding.chipAll.setChecked(true);
        else if (filter.isShowMovieOnly()) binding.chipMovie.setChecked(true);
        else if (filter.isShowTVOnly()) binding.chipTv.setChecked(true);
        viewModel.setFilter(filter);
    }

    @Override
    public void onFavoriteRemoved(FavoriteEntity favorite) {
        viewModel.deleteFavorite(favorite);
        showToast(getContext(), getString(R.string.success_remove));
    }

    @Override
    public void onClick(@NotNull View view) {
        int itemId = view.getId();
        if (itemId == R.id.fab_edit) {
            if (editView) binding.recyclerView.setAdapter(adapter);
            else binding.recyclerView.setAdapter(editAdapter);
            editView = !editView;
        } else if (itemId == R.id.fab_filter) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_BOOKMARK_FILTER, filter);
            FilterFavoriteDialog dialog = new FilterFavoriteDialog();
            dialog.setArguments(bundle);
            dialog.show(getChildFragmentManager(), dialog.getTag());
        }
    }
}