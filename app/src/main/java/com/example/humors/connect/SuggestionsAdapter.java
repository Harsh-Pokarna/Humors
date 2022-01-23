package com.example.humors.connect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.humors.R;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.SuggestionsViewHolder> {

    @NonNull
    @Override
    public SuggestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SuggestionsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggestion, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionsViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class SuggestionsViewHolder extends RecyclerView.ViewHolder {

        TextView suggestionName;
        ImageButton nextButton;
        ImageView suggestionIcon;

        public SuggestionsViewHolder(@NonNull View itemView) {
            super(itemView);

            suggestionName = itemView.findViewById(R.id.suggestion_name);
            nextButton = itemView.findViewById(R.id.next_button_suggestion);
            suggestionIcon = itemView.findViewById(R.id.suggestion_icon);

        }

        public void bind() {

        }
    }
}
