package com.ariefzuhri.blu.ui.main.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.blu.utils.FilterFavorite;
import com.ariefzuhri.blu.databinding.FragmentFavoriteBinding;
import com.ariefzuhri.blu.viewmodel.ViewModelFactory;
import com.google.android.material.chip.ChipGroup;

import org.jetbrains.annotations.NotNull;

import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

import static com.ariefzuhri.blu.utils.AppUtils.showToast;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_BOOKMARK_FILTER;

public class FavoriteFragment extends Fragment implements ChipGroup.OnCheckedChangeListener, FilterFavoriteDialog.FavoriteFilterDialogListener, FavoriteAdapter.FavoriteAdapterListener {

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

        binding.rvMedia.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvMedia.setHasFixedSize(true);
        adapter = new FavoriteAdapter();
        binding.rvMedia.setAdapter(adapter);

        editAdapter = new FavoriteAdapter(this);
        editView = false;
        binding.chipAll.setChecked(true);

        if (getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());
            viewModel = new ViewModelProvider(this, factory).get(FavoriteViewModel.class);
            viewModel.getFilter().observe(getViewLifecycleOwner(), filter -> this.filter = filter);
            viewModel.getAllFavoriteWithGenres().observe(getViewLifecycleOwner(), result -> {
                adapter.submitList(result);
                editAdapter.submitList(result);
            });

            binding.fabOption.setMenuListener(new SimpleMenuListenerAdapter() {
                @Override
                public boolean onMenuItemSelected(MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.fab_edit) {
                        if (editView) binding.rvMedia.setAdapter(adapter);
                        else binding.rvMedia.setAdapter(editAdapter);
                        editView = !editView;
                    } else if (itemId == R.id.fab_filter) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(EXTRA_BOOKMARK_FILTER, filter);
                        FilterFavoriteDialog dialog = new FilterFavoriteDialog();
                        dialog.setArguments(bundle);
                        dialog.show(getChildFragmentManager(), dialog.getTag());
                    }
                    return true;
                }
            });

            binding.cgList.setOnCheckedChangeListener(this);
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
    public void onFilterApplied(FilterFavorite filter) {
        if (filter.isShowAllMediaType()) binding.chipAll.setChecked(true);
        else if (filter.isShowMovieOnly()) binding.chipMovie.setChecked(true);
        else if (filter.isShowTVOnly()) binding.chipTv.setChecked(true);
        viewModel.setFilter(filter);
    }

    @Override
    public void onFavoriteRemoved(FavoriteEntity favorite) {
        viewModel.deleteFavorite(favorite);
        showToast(getContext(), "Berhasil hapus");
    }
}