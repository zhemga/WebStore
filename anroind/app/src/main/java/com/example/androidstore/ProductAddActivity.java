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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.androidstore.dto.ProductAddDTO;
import com.example.androidstore.network.services.ProductService;
import com.example.androidstore.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.oginotihiro.cropview.CropView;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAddActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    ProductAddActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        activity = this;
    }


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


    public void AddProduct(View view) {
        final TextInputEditText textInputName = findViewById(R.id.textInputName);
        final TextInputEditText textInputPrice = findViewById(R.id.textInputPrice);

        final TextInputLayout textFieldName = findViewById(R.id.textFieldName);
        final TextInputLayout textFieldPrice = findViewById(R.id.textFieldPrice);

        if (textInputName.getText().toString().isEmpty())
            textFieldName.setError("Empty name");
        else
            textFieldName.setError(null);

        if (textInputPrice.getText().toString().isEmpty())
            textFieldPrice.setError("Empty price");
        else if (!isNumeric(textInputPrice.getText().toString()))
            textFieldPrice.setError("Price must be a number.");
        else
            textFieldPrice.setError(null);

        if (textFieldName.getError() == null && textFieldPrice.getError() == null) {
            CropView cropView = (CropView) findViewById(R.id.cropView);
            Bitmap croppedBitmap = cropView.getOutput();

            if (croppedBitmap != null) {
                ProductAddDTO model = new ProductAddDTO();
                Matrix matrix = new Matrix();

                model.setName(textInputName.getText().toString());
                model.setPrice(Double.parseDouble(textInputPrice.getText().toString()));

                matrix.postRotate(cropView.getRotation());
                Bitmap rotatedBitmap = Bitmap.createBitmap(croppedBitmap, 0, 0, croppedBitmap.getWidth(), croppedBitmap.getHeight(), matrix, true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                model.setImage(encoded);

                ProductService.getInstance()
                        .getProductsApi()
                        .add(model)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Intent intent = new Intent(activity, ProductActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

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

    protected static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
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