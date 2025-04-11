package com.example.bankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

        SQLiteDatabase db = openOrCreateDatabase("BankingApp.db", MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{userInput, passInput});

        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            i.putExtra("senderPassword", passInput);  // 👈 pass the password
            startActivity(i);

            finish();
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
        }
    }
}
