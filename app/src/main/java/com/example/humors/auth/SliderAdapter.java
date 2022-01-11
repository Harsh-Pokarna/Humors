package com.example.humors.auth;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.humors.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {

    private final List<Drawable> listOfIllustrations;

    public SliderAdapter(List<Drawable> listOfIllustrations) {
        this.listOfIllustrations = listOfIllustrations;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image, null));
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageDrawable(listOfIllustrations.get(position));
    }

    @Override
    public int getCount() {
        return listOfIllustrations.size();
    }

    static class SliderViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView imageView;

        public SliderViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.slider_image_view);
        }
    }
}
