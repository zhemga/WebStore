package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.androidstore.application.HomeApplication;
import com.example.androidstore.dto.AuthResultDTO;
import com.example.androidstore.dto.LoginDTO;
import com.example.androidstore.network.services.AccountService;
import com.example.androidstore.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    LoginActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = this;
    }

    public void OnClickButtonLogin(View view) {
        //layouts
        final TextInputLayout emailLayout = findViewById(R.id.tiEmail);
        final TextInputLayout passwordLayout = findViewById(R.id.tiPassword);
        //text inputs
        final TextInputEditText email = findViewById(R.id.edEmail);
        final TextInputEditText password = findViewById(R.id.edPassword);

        Boolean isNotValid = false;

        if (isNotValid = email.getText().toString().isEmpty())
            emailLayout.setError("Empty email");
        else
            emailLayout.setError(null);

        if (isNotValid = password.getText().toString().isEmpty())
            passwordLayout.setError("Empty password");
        else
            passwordLayout.setError(null);

        if (!isNotValid) {
            LoginDTO loginDTO = new LoginDTO();

            loginDTO.setEmail(email.getText().toString());
            loginDTO.setPassword(password.getText().toString());

            AccountService.getInstance()
                    .getJSONApi()
                    .Login(loginDTO)
                    .enqueue(new Callback<AuthResultDTO>() {
                        @SneakyThrows
                        @Override
                        public void onResponse(Call<AuthResultDTO> call, Response<AuthResultDTO> response) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            if (response.isSuccessful()) {
                                HomeApplication.getInstance().saveJwtToken(response.body().getToken());
                                Intent intent = new Intent(activity, ProductActivity.class);
                                startActivity(intent);
                            } else {
                                Gson gson = new Gson();
                                AuthErrorMessage message = gson.fromJson(response.errorBody().charStream(), AuthErrorMessage.class);
                                String err = "";
                                if (message.errors != null) {
                                    if (message.errors.getEmail() != null)
                                        for (String i : message.errors.getEmail()) {
                                            err += i + " ";

                                        }

                                    if (message.errors.getPhone() != null)
                                        for (String i : message.errors.getPhone()) {
                                            err += i + " ";
                                        }

                                    if (message.errors.getPassword() != null)
                                        for (String i : message.errors.getPassword()) {
                                            err += i + " ";
                                        }
                                }
                                builder.setMessage(err);
                                builder.setNeutralButton("Ok", null);
                                AlertDialog alertRes = builder.create();
                                alertRes.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AuthResultDTO> call, Throwable t) {
                            CommonUtils.hideLoading();
                        }
                    });
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Choose an image.");
            builder.setNeutralButton("Ok", null);

            AlertDialog alertNoImage = builder.create();
            alertNoImage.show();
        }
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