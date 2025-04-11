package com.example.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, createPass, confirmPass, bankAccount;
    private CheckBox termsCheckBox;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.Name2);
        email = findViewById(R.id.email1);
        createPass = findViewById(R.id.createPass);
        confirmPass = findViewById(R.id.confirmPass);
        bankAccount = findViewById(R.id.bankAccount);
        termsCheckBox = findViewById(R.id.checkBox);
        registerButton = findViewById(R.id.registerButton2);

        registerButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String nameInput = name.getText().toString().trim();
        String emailInput = email.getText().toString().trim();
        String passInput = createPass.getText().toString().trim();
        String confirmPassInput = confirmPass.getText().toString().trim();
        String bankInput = bankAccount.getText().toString().trim();
        boolean isTermsChecked = termsCheckBox.isChecked();

        if (nameInput.isEmpty() || emailInput.isEmpty() || passInput.isEmpty() ||
                confirmPassInput.isEmpty() || bankInput.isEmpty()) {
            Toast.makeText(this, "Enter all fields to proceed", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passInput.equals(confirmPassInput)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isTermsChecked) {
            Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
            return;
        }

        // Open or create the database and table with an additional 'balance' column
        SQLiteDatabase db = openOrCreateDatabase("BankingApp.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT, " +
                "password TEXT, " +
                "bank_account TEXT, " +
                "balance INTEGER)");

        ContentValues values = new ContentValues();
        values.put("name", nameInput);
        values.put("email", emailInput);
        values.put("password", passInput);
        values.put("bank_account", bankInput);
        values.put("balance", 10000); // 🆕 Initial balance set to 10000

        long result = db.insert("users", null, values);
        db.close();

        if (result != -1) {
            Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}
