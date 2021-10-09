package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        menu.add("menu1");
        menu.add("menu2");
        menu.add("menu3");
        menu.add("menu4");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    public void OnClickButtonRegister(View view) {
        final TextInputEditText email = findViewById(R.id.edEmail);
        final TextInputEditText password = findViewById(R.id.edPassword);
        final TextInputLayout emailLayout = findViewById(R.id.tiEmail);
        final TextInputLayout passwordLayout = findViewById(R.id.tiPassword);

        if (email.getText().toString().isEmpty())
            emailLayout.setError("Empty email");
        else
            emailLayout.setError(null);

        if (password.getText().toString().isEmpty())
            passwordLayout.setError("Empty password");
        else
            passwordLayout.setError(null);

        Log.d("btnRegInfo", "Mu nazhalu knopku reg" + email.getText() + " " + password.getText());

        Intent intent = new Intent(this ,MainActivity.class);
        startActivity(intent);
    }

    public void OnClickProductsActivity(View view) {
        Intent intent = new Intent(this ,ProductActivity.class);
        startActivity(intent);
    }

    public void OnClickPhotoActivity(View view) {
        Intent intent = new Intent(this ,PhotoActivity.class);
        startActivity(intent);
    }
}