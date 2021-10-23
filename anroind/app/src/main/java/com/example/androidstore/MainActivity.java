package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.example.androidstore.constans.Urls;
import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.dto.ProductImageDTO;
import com.example.androidstore.network.ImageRequester;
import com.example.androidstore.network.services.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private TextView txtinfo;
    private ImageView imageView;
    private ImageRequester imageRequester;
    private NetworkImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = Urls.BASE + "/images/manul.jpg";
        imageRequester = ImageRequester.getInstance();
        myImage = findViewById(R.id.myimg);
        imageRequester.setImageFromUrl(myImage, url);

        imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load(url)
                .into(imageView);

        txtinfo = findViewById(R.id.txtinfo);

//        ProductService.getInstance()
//                .getProductsApi()
//                .all()
//                .enqueue(new Callback<List<ProductDTO>>() {
//                    @Override
//                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
//                        List<ProductDTO> list = response.body();
//                        String str="";
//                        for (ProductDTO item : list) {
//                            str+=item.getName()+"\n";
//                        }
//                        txtinfo.setText(str);
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
//
//                    }
//                });

        ProductService.getInstance()
                .getProductsApi()
                .getPostWithID(1)
                .enqueue(new Callback<List<ProductImageDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductImageDTO>> call, Response<List<ProductImageDTO>> response) {
                        List<ProductImageDTO> list = response.body();
                        String str = "";
                        for (ProductImageDTO item : list) {
                            str += item.getPath() + "\n";
                        }
                        txtinfo.setText(str);
                    }

                    @Override
                    public void onFailure(Call<List<ProductImageDTO>> call, Throwable t) {

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