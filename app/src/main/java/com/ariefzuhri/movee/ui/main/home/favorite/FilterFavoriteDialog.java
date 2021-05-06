package com.ariefzuhri.movee.ui.main.home.favorite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ariefzuhri.movee.R;
import com.ariefzuhri.movee.utils.FilterFavorite;
import com.ariefzuhri.movee.databinding.DialogFilterFavoriteBinding;

import static com.ariefzuhri.movee.utils.Constants.EXTRA_BOOKMARK_FILTER;

public class FilterFavoriteDialog extends DialogFragment implements View.OnClickListener {

    private AlertDialog dialog;
    private FavoriteFilterDialogListener listener;
    private FilterFavorite filter;
    private DialogFilterFavoriteBinding binding;

    public FilterFavoriteDialog(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogFilterFavoriteBinding.inflate(getLayoutInflater());

        binding.ibClose.setOnClickListener(this);
        binding.tvReset.setOnClickListener(this);
        binding.btnApply.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);

        // Receive bundle from activity
        Bundle bundle = getArguments();
        if (bundle != null) filter = bundle.getParcelable(EXTRA_BOOKMARK_FILTER);
        else filter = new FilterFavorite();

        setCheckedChip(filter);

        // Create alert dialog instance
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());
        dialog = builder.create();
        return dialog;
    }

    private void setCheckedChip(FilterFavorite filter) {
        binding.chipTitle.setChecked(filter.isSortedByTitle());
        binding.chipRating.setChecked(filter.isSortedByRating());
        binding.chipReleaseDate.setChecked(filter.isSortedByReleaseDate());
        binding.chipAll.setChecked(filter.isShowAllMediaType());
        binding.chipMovie.setChecked(filter.isShowMovieOnly());
        binding.chipTv.setChecked(filter.isShowTVOnly());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_apply){
            filter.setSortedByTitle(binding.chipTitle.isChecked());
            filter.setSortedByRating(binding.chipRating.isChecked());
            filter.setSortedByReleaseDate(binding.chipReleaseDate.isChecked());
            filter.setShowAllMediaType(binding.chipAll.isChecked());
            filter.setShowMovieOnly(binding.chipMovie.isChecked());
            filter.setShowTVOnly(binding.chipTv.isChecked());

            listener.onFilterApplied(filter);
            dialog.dismiss();
        } else if (id == R.id.tv_reset){
            filter = new FilterFavorite();
            setCheckedChip(filter);
        } else if (id == R.id.btn_cancel || id == R.id.ib_close){
            dialog.dismiss();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (FavoriteFilterDialogListener) getParentFragment();
        } catch (ClassCastException e){
            throw new ClassCastException(context.getClass().getSimpleName() +
                    " must implement " + FavoriteFilterDialogListener.class.getSimpleName());
        }
    }

    public interface FavoriteFilterDialogListener{
        void onFilterApplied(FilterFavorite filter);
    }
}
