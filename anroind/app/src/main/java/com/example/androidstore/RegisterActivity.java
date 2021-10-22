package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidstore.dto.RegisterDTO;
import com.example.androidstore.dto.RegisterResultDTO;
import com.example.androidstore.network.services.AccountService;
import com.example.androidstore.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

    public void OnClickButtonRegister(View view) {
        //layouts
        final TextInputLayout emailLayout = findViewById(R.id.tiEmail);
        final TextInputLayout passwordLayout = findViewById(R.id.tiPassword);
//        final TextInputLayout nameLayout = findViewById(R.id.tiName);
//        final TextInputLayout surnameLayout = findViewById(R.id.tiSurname);
//        final TextInputLayout middleNameLayout = findViewById(R.id.tiMiddleName);
//        final TextInputLayout phoneLayout = findViewById(R.id.tiPhone);
        //text inputs
        final TextInputEditText email = findViewById(R.id.edEmail);
        final TextInputEditText password = findViewById(R.id.edPassword);
//        final TextInputEditText name = findViewById(R.id.edName);
//        final TextInputEditText surname = findViewById(R.id.edSurname);
//        final TextInputEditText middleName = findViewById(R.id.edMiddleName);
//        final TextInputEditText phone = findViewById(R.id.edPhone);

        if (email.getText().toString().isEmpty())
            emailLayout.setError("Empty email");
        else
            emailLayout.setError(null);

        if (password.getText().toString().isEmpty())
            passwordLayout.setError("Empty password");
        else
            passwordLayout.setError(null);

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail(email.getText().toString());
        registerDTO.setPassword(password.getText().toString());
        RegisterActivity myActivity = this;
        AccountService.getInstance()
                .getJSONApi()
                .Registration(registerDTO)
                .enqueue(new Callback<RegisterResultDTO>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<RegisterResultDTO> call, Response<RegisterResultDTO> response) {
                        if (!response.isSuccessful()) {
                            String message = response.errorBody().string();
                            String res = "sdfsdf";
                        }
                        String token = response.body().getToken();
                    }

                    @Override
                    public void onFailure(Call<RegisterResultDTO> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });
    }

    public void OnClickProductsActivity(View view) {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }

    public void OnClickPhotoActivity(View view) {
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent);
    }
}