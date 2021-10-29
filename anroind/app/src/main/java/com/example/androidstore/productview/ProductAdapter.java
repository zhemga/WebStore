package com.example.androidstore.productview;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidstore.MainActivity;
import com.example.androidstore.ProductActivity;
import com.example.androidstore.ProductAddActivity;
import com.example.androidstore.ProductEditActivity;
import com.example.androidstore.application.HomeApplication;
import com.example.androidstore.constans.Urls;
import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.network.ImageRequester;
import com.example.androidstore.R;
import com.example.androidstore.network.services.ProductService;
import com.example.androidstore.utils.CommonUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private List<ProductDTO> modelList;
    private ProductAdapter adapter;
    private ProductActivity activity;

    public ProductAdapter(List<ProductDTO> modelList, ProductActivity activity) {
        this.modelList = modelList;
        adapter = this;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (modelList != null && position < modelList.size()) {
            ProductDTO model = modelList.get(position);
            holder.prodId.setText(String.valueOf(model.getId()));
            holder.prodName.setText(model.getName());
            holder.prodPrice.setText(String.valueOf(model.getPrice()));

            int i = (int) (new Date().getTime() / 1000);
            String url = Urls.BASE + "/images/" + model.getImage() + "?=data" + i;
            Glide.with(HomeApplication.getAppContext())
                    .load(url)
                    .circleCrop()
                    .apply(new RequestOptions().override(300, 300))
                    .into(holder.prodImage);

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductService.getInstance()
                            .getProductsApi()
                            .delete(model.getId())
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {

                                    for (ProductDTO item : modelList) {
                                        if (item.getId() == model.getId()) {
                                            modelList.remove(item);
                                            break;
                                        }
                                    }

                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                }
            });

            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, ProductEditActivity.class);
                    intent.putExtra("id", model.getId());
                    intent.putExtra("name", model.getName());
                    intent.putExtra("price", model.getPrice());
                    intent.putExtra("image", model.getImage());
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return modelList != null ? modelList.size() : 0;
    }
}
