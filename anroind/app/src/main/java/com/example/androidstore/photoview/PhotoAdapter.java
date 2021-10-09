package com.example.androidstore.photoview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.constans.Urls;
import com.example.androidstore.network.ImageRequester;
import com.example.androidstore.R;
import com.example.androidstore.productview.ProductCardViewHolder;

import java.util.Date;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private List<String> modelList;
    private ImageRequester imageRequester;

    public PhotoAdapter(List<String> modelList) {
        this.modelList = modelList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.photo_card, parent, false);
        return new PhotoViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        if (modelList != null && position < modelList.size()) {
            String model = modelList.get(position);
            int i = (int) (new Date().getTime()/1000);
            String url = Urls.BASE + "/images/" + model + "?=data" + i;
            imageRequester.setImageFromUrl(holder.photoImage, url);
            holder.photoText.setText(model);
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}