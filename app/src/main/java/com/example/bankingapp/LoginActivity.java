package com.example.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.Name1);
        password = findViewById(R.id.Password1);
        loginButton = findViewById(R.id.loginButton2);


        loginButton.setOnClickListener(v -> loginUser());
    }


    private void loginUser() {
        String userInput = username.getText().toString().trim();
        String passInput = password.getText().toString().trim();

        if (userInput.isEmpty() || passInput.isEmpty()) {
            Toast.makeText(this, "Enter all fields to proceed", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(i);
    }
}
