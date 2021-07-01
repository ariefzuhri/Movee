package com.ariefzuhri.movee.ui.main.home;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.databinding.ItemMediaHorizBinding;
import com.ariefzuhri.movee.databinding.ItemMediaVertBinding;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.ui.detail.DetailMediaActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.movee.utils.AppUtils.loadImage;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_MEDIA_ID;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_MEDIA_TYPE;
import static com.ariefzuhri.movee.utils.Constants.IMAGE_SIZE_NORMAL;
import static com.ariefzuhri.movee.utils.Constants.ORIENTATION_TYPE_VERTICAL;
import static com.ariefzuhri.movee.utils.DateUtils.getYearOfDate;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    private final ArrayList<MediaEntity> mediaList = new ArrayList<>();
    private final ArrayList<GenreEntity> genreList = new ArrayList<>();
    private final int ORIENTATION_TYPE;

    public MediaAdapter(int orientationType){
        ORIENTATION_TYPE = orientationType;
    }

    public void submitList(List<MediaEntity> mediaList){
        this.mediaList.clear();
        this.mediaList.addAll(mediaList);
        notifyDataSetChanged();
    }

    public void submitGenreList(List<GenreEntity> genreList){
        this.genreList.clear();
        this.genreList.addAll(genreList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (ORIENTATION_TYPE == ORIENTATION_TYPE_VERTICAL){
            ItemMediaVertBinding binding = ItemMediaVertBinding.inflate(inflater, parent, false);
            return new ViewHolder(binding);
        } else {
            ItemMediaHorizBinding binding = ItemMediaHorizBinding.inflate(inflater, parent, false);
            return new ViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MediaEntity media = mediaList.get(position);
        holder.bind(media);
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvScore, tvReleaseYear, tvTitle, tvGenre;
        private final ImageView imgPoster;

        public ViewHolder(@NonNull ItemMediaVertBinding binding) {
            super(binding.getRoot());
            tvScore = binding.tvScore;
            tvReleaseYear = binding.tvReleaseYear;
            tvTitle = binding.tvTitle;
            imgPoster = binding.imgPoster;
            tvGenre = binding.tvGenre;
        }

        public ViewHolder(@NonNull ItemMediaHorizBinding binding) {
            super(binding.getRoot());
            tvScore = binding.tvScore;
            tvReleaseYear = binding.tvReleaseYear;
            tvTitle = binding.tvTitle;
            imgPoster = binding.imgPoster;
            tvGenre = binding.tvGenre;
        }

        public void bind(@NotNull MediaEntity media) {
            loadImage(itemView.getContext(), IMAGE_SIZE_NORMAL, media.getPoster(), imgPoster);
            tvScore.setText(String.valueOf((double) Math.round(media.getScoreAverage() * 10) / 10));
            tvReleaseYear.setText(getYearOfDate(media.getAiredDate().getStartDate()));
            tvTitle.setText(media.getTitle());
            tvGenre.setText(TextUtils.join(", ", getGenreList(media)));

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), DetailMediaActivity.class);
                intent.putExtra(EXTRA_MEDIA_TYPE, media.getType());
                intent.putExtra(EXTRA_MEDIA_ID, media.getId());
                view.getContext().startActivity(intent);
            });
        }

        @NotNull
        private List<String> getGenreList(@NotNull MediaEntity media){
            List<String> thisMediaGenreList = new ArrayList<>();
            for (int thisMediaGenreId : media.getGenreIds()){
                for (GenreEntity genre : genreList){
                    if (thisMediaGenreId == genre.getId()){
                        thisMediaGenreList.add(genre.getName());
                        break;
                    }
                }
            }
            return thisMediaGenreList;
        }
    }
}
