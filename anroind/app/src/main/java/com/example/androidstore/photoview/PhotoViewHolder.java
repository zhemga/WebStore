package com.example.androidstore.photoview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.androidstore.R;

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private View view;
    public NetworkImageView photoImage;
    public TextView photoText;

    public PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        photoImage = itemView.findViewById(R.id.photoimg);
        photoText = itemView.findViewById(R.id.phototext);
    }

    public View getView() {
        return view;
    }
}

