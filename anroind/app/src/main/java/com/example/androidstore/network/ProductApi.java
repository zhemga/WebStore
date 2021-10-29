package com.example.androidstore.network;

import com.example.androidstore.dto.ProductAddDTO;
import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.dto.ProductEditDTO;
import com.example.androidstore.dto.ProductImageDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductApi {
    @GET("/api/products/all")
    public Call<List<ProductDTO>> all(@Header("Authorization") String authHeader);

    @GET("/api/products/allImages")
    public Call<List<String>> allImages();

    @GET("/api/products/images/{id}")
    public Call<List<String>> getImagesById(@Path("id") int id);

    @GET("/api/Products/get/{id}")
    public Call<List<ProductImageDTO>> getPostWithID(@Path("id") int id);

    @POST("/api/Products/add")
    public Call<Void> add(@Body ProductAddDTO product);

    @DELETE("/api/Products/delete/{id}")
    public Call<Void> delete(@Path("id") int id);

    @PUT("/api/Products/edit")
    public Call<Void> edit(@Body ProductEditDTO product);
}
