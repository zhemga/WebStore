package com.example.androidstore;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
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
import com.google.gson.Gson;
import com.oginotihiro.cropview.CropView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    RegisterActivity activity;

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();

                        //CommonUtils.showLoading(this);
                        Uri selectedImage = data.getData();
                        CropView cropView = (CropView) findViewById(R.id.cropView);
                        cropView.of(selectedImage)
                                //.withAspect(x, y)
                                .withOutputSize(100, 100)
                                .initialize(activity);
                        //CommonUtils.hideLoading();
                    }

                }
            });

    public void openSomeActivityForResult(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //Intent intent = new Intent(this, SomeActivity.class);
        someActivityResultLauncher.launch(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        activity = this;
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
        final TextInputLayout nameLayout = findViewById(R.id.tiName);
        final TextInputLayout surnameLayout = findViewById(R.id.tiSurname);
        final TextInputLayout middleNameLayout = findViewById(R.id.tiMiddleName);
        final TextInputLayout phoneLayout = findViewById(R.id.tiPhone);
        //text inputs
        final TextInputEditText email = findViewById(R.id.edEmail);
        final TextInputEditText password = findViewById(R.id.edPassword);
        final TextInputEditText name = findViewById(R.id.edName);
        final TextInputEditText surname = findViewById(R.id.edSurname);
        final TextInputEditText middleName = findViewById(R.id.edMiddleName);
        final TextInputEditText phone = findViewById(R.id.edPhone);

        Boolean isNotValid = false;

        if (isNotValid = email.getText().toString().isEmpty())
            emailLayout.setError("Empty email");
        else
            emailLayout.setError(null);

        if (isNotValid = password.getText().toString().isEmpty())
            passwordLayout.setError("Empty password");
        else
            passwordLayout.setError(null);

        if (isNotValid = name.getText().toString().isEmpty())
            nameLayout.setError("Empty name");
        else
            nameLayout.setError(null);

        if (isNotValid = surname.getText().toString().isEmpty())
            surnameLayout.setError("Empty surname");
        else
            surnameLayout.setError(null);

        if (isNotValid = middleName.getText().toString().isEmpty())
            middleNameLayout.setError("Empty middle name");
        else
            middleNameLayout.setError(null);

        if (isNotValid = phone.getText().toString().isEmpty())
            phoneLayout.setError("Empty phone");
        else
            phoneLayout.setError(null);

        if (!isNotValid) {
            CropView cropView = (CropView) findViewById(R.id.cropView);
            Bitmap croppedBitmap = cropView.getOutput();

            if (croppedBitmap != null) {
                RegisterDTO registerDTO = new RegisterDTO();
                Matrix matrix = new Matrix();

                registerDTO.setEmail(email.getText().toString());
                registerDTO.setPassword(password.getText().toString());
                registerDTO.setName(name.getText().toString());
                registerDTO.setSurname(surname.getText().toString());
                registerDTO.setMiddleName(middleName.getText().toString());
                registerDTO.setPhone(phone.getText().toString());

                matrix.postRotate(cropView.getRotation());
                Bitmap rotatedBitmap = Bitmap.createBitmap(croppedBitmap, 0, 0, croppedBitmap.getWidth(), croppedBitmap.getHeight(), matrix, true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                registerDTO.setImage(encoded);

                AccountService.getInstance()
                        .getJSONApi()
                        .Registration(registerDTO)
                        .enqueue(new Callback<RegisterResultDTO>() {
                            @SneakyThrows
                            @Override
                            public void onResponse(Call<RegisterResultDTO> call, Response<RegisterResultDTO> response) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                if (response.isSuccessful()) {
                                    builder.setMessage(response.body().getToken());
                                } else {
                                    Gson gson = new Gson();
                                    RegisterErrorMessage message = gson.fromJson(response.errorBody().charStream(), RegisterErrorMessage.class);
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
                                    }
                                    builder.setMessage(err);
                                }
                                builder.setNeutralButton("Ok", null);
                                AlertDialog alertRes = builder.create();
                                alertRes.show();
                            }

                            @Override
                            public void onFailure(Call<RegisterResultDTO> call, Throwable t) {
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
    }
}