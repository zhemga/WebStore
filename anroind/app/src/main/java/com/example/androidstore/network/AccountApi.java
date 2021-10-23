package com.example.androidstore.network;

import com.example.androidstore.dto.LoginDTO;
import com.example.androidstore.dto.RegisterDTO;
import com.example.androidstore.dto.AuthResultDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("/api/account/register")
    public Call<AuthResultDTO> Registration(@Body RegisterDTO registerDTO);
    @POST("/api/account/login")
    public Call<AuthResultDTO> Login(@Body LoginDTO registerDTO);
}
