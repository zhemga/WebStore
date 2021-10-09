package com.example.androidstore.network;

import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.dto.ProductImageDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApi {
    @GET("/api/products/all")
    public Call<List<ProductDTO>> all();

    @GET("/api/products/allImages")
    public Call<List<String>> allImages();

    @GET("/api/Products/get/{id}")
    public Call<List<ProductImageDTO>> getPostWithID(@Path("id") int id);
}
