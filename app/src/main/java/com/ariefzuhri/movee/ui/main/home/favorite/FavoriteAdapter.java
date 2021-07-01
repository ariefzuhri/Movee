package com.ariefzuhri.movee.ui.main.home.favorite;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ariefzuhri.movee.R;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.databinding.ItemMediaGridBinding;
import com.ariefzuhri.movee.ui.detail.DetailMediaActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.movee.utils.AppUtils.loadImage;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_MEDIA_ID;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_MEDIA_TYPE;
import static com.ariefzuhri.movee.utils.Constants.IMAGE_SIZE_NORMAL;
import static com.ariefzuhri.movee.utils.DateUtils.getYearOfDate;

public class FavoriteAdapter extends PagedListAdapter<FavoriteWithGenres, FavoriteAdapter.ViewHolder> {

    private FavoriteAdapterListener listener;

    public FavoriteAdapter(){
        super(DIFF_CALLBACK);
    }

    public FavoriteAdapter(FavoriteAdapterListener listener){
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<FavoriteWithGenres> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<FavoriteWithGenres>() {
                @Override
                public boolean areItemsTheSame(@NonNull FavoriteWithGenres oldItem, @NonNull FavoriteWithGenres newItem) {
                    return oldItem.favorite.getId().equals(newItem.favorite.getId()) && oldItem.favorite.getType().equals(newItem.favorite.getType());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull FavoriteWithGenres oldItem, @NonNull FavoriteWithGenres newItem) {
                    return oldItem.favorite.equals(newItem.favorite);
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMediaGridBinding binding = ItemMediaGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteWithGenres favoriteWithGenres = getItem(position);
        if (favoriteWithGenres != null) {
            holder.bind(favoriteWithGenres);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMediaGridBinding binding;

        public ViewHolder(@NonNull ItemMediaGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NotNull FavoriteWithGenres favoriteWithGenres) {
            FavoriteEntity favorite = favoriteWithGenres.favorite;

            loadImage(itemView.getContext(), IMAGE_SIZE_NORMAL, favorite.getPoster(), binding.imgPoster);
            binding.tvScore.setText(String.valueOf(favorite.getScoreAverage()));
            binding.tvReleaseYear.setText(getYearOfDate(favorite.getStartDate()));
            binding.tvTitle.setText(favorite.getTitle());

            List<String> genres = new ArrayList<>();
            for (GenreEntity genre : favoriteWithGenres.genres) genres.add(genre.getName());
            binding.tvGenre.setText(TextUtils.join(", ", genres));

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), DetailMediaActivity.class);
                intent.putExtra(EXTRA_MEDIA_TYPE, favorite.getType());
                intent.putExtra(EXTRA_MEDIA_ID, favorite.getId());
                view.getContext().startActivity(intent);
            });

            boolean editView = listener != null;
            if (editView) {
                binding.ibRemove.setVisibility(View.VISIBLE);
                binding.ibRemove.setOnClickListener(view ->
                        new AlertDialog.Builder(view.getContext())
                                .setTitle(R.string.remove_favorite)
                                .setMessage(view.getResources().getString(
                                        R.string.remove_favorite_confirmation,
                                        favorite.getTitle()))
                                .setNeutralButton(R.string.cancel, null)
                                .setNegativeButton(R.string.no, null)
                                .setPositiveButton(R.string.yes, (dialogInterface, i) ->
                                        listener.onFavoriteRemoved(favorite)).create().show());
            }
        }
    }

    interface FavoriteAdapterListener{
        void onFavoriteRemoved(FavoriteEntity favorite);
    }
}
