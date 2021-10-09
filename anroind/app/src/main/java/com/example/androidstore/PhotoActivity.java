package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidstore.network.services.ProductService;
import com.example.androidstore.photoview.PhotoAdapter;
import com.example.androidstore.utils.CommonUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    private PhotoAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        recyclerView = findViewById(R.id.rcvPhotos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1,
                LinearLayoutManager.VERTICAL, false));

        CommonUtils.showLoading(this);
        ProductService.getInstance()
                .getProductsApi()
                .allImages()
                .enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        List<String> list = response.body();
                        adapter = new PhotoAdapter(list);
                        recyclerView.setAdapter(adapter);
                        CommonUtils.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });
    }
}