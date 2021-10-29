package com.example.androidstore.productview;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.androidstore.R;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    private View view;
    public ImageView prodImage;
    public TextView prodName;
    public TextView prodId;
    public TextView prodPrice;
    public TextView prodDescription;
    public Button btnEdit;
    public Button btnDelete;

    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        prodImage = itemView.findViewById(R.id.prodimg);
        prodId = itemView.findViewById(R.id.prodid);
        prodName = itemView.findViewById(R.id.prodname);
        prodPrice = itemView.findViewById(R.id.prodprice);
        prodDescription = itemView.findViewById(R.id.proddescription);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }

    public View getView() {
        return view;
    }
}
