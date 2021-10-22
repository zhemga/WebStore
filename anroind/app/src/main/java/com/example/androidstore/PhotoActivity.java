package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.androidstore.network.services.ProductService;
import com.example.androidstore.photoview.PhotoAdapter;
import com.example.androidstore.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    private PhotoAdapter adapter;
    private RecyclerView recyclerView;
    private TextInputLayout tiProdId;
    private TextInputEditText edProdId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        recyclerView = findViewById(R.id.rcvPhotos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1,
                LinearLayoutManager.VERTICAL, false));

        tiProdId = findViewById(R.id.tiProdId);
        edProdId = findViewById(R.id.edProdId);
        edProdId.setInputType(InputType.TYPE_CLASS_NUMBER);

        loadAllPhotos();
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

    public void loadAllPhotos() {
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

    public void onClickImagesByProdId(View view) {
        if (edProdId.getText().toString().isEmpty()) {
            tiProdId.setError("Empty id!");
            loadAllPhotos();
        }
        else {
            tiProdId.setError(null);

            CommonUtils.showLoading(this);
            ProductService.getInstance()
                    .getProductsApi()
                    .getImagesById(Integer.parseInt(edProdId.getText().toString()))
                    .enqueue(new Callback<List<String>>() {
                        @Override
                        public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                            List<String> list = response.body();

                            if(!list.isEmpty()) {

                                adapter = new PhotoAdapter(list);
                                recyclerView.setAdapter(adapter);
                                CommonUtils.hideLoading();
                            }
                            else
                            {
                                tiProdId.setError("Wrong id or empty list! Loaded all photos.");
                                CommonUtils.hideLoading();
                                loadAllPhotos();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<String>> call, Throwable t) {
                            CommonUtils.hideLoading();
                        }
                    });
        }
    }
}