package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.androidstore.application.HomeApplication;
import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.network.services.ProductService;
import com.example.androidstore.productview.ProductAdapter;
import com.example.androidstore.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private RecyclerView recyclerView;
    private ProductActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        activity = this;
        recyclerView = findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1,
                LinearLayoutManager.VERTICAL, false));

        CommonUtils.showLoading(this);
        ProductService.getInstance()
                .getProductsApi()
                .all("Bearer " + HomeApplication.getInstance().getToken())
                .enqueue(new Callback<List<ProductDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                        List<ProductDTO> list = response.body();
                        adapter = new ProductAdapter(list, activity);
                        recyclerView.setAdapter(adapter);
                        CommonUtils.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.mhome:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.mregister:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                return true;
            case R.id.mlogin:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.mproducts:
                intent = new Intent(this, ProductActivity.class);
                startActivity(intent);
                return true;
            case R.id.mproductadd:
                intent = new Intent(this, ProductAddActivity.class);
                startActivity(intent);
                return true;
            case R.id.mphotos:
                intent = new Intent(this, PhotoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}