package com.ariefzuhri.blu.ui.detail;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ariefzuhri.blu.databinding.ItemGenreBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private final ArrayList<String> genreList = new ArrayList<>();
    private final int textSize;

    public GenreAdapter(int textSize){
        this.textSize = textSize;
    }

    public void setData(String[] genreList){
        this.genreList.clear();
        this.genreList.addAll(Arrays.asList(genreList));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenreBinding binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.ViewHolder holder, int position) {
        String genre = genreList.get(position);
        holder.bind(genre);
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemGenreBinding binding;

        public ViewHolder(@NonNull ItemGenreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String genre) {
            binding.tvName.setText(genre);
                binding.tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, itemView.getContext()
                    .getResources().getDimension(textSize));
        }
    }
}
