package com.example.bankingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CheckBalanceActivity extends AppCompatActivity {

    private TextInputEditText pinEditText;
    private LinearLayout balanceContainer;
    private TextView balanceTextView;
    private MaterialButton checkBalanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_checker);

        pinEditText = findViewById(R.id.pinEditText);
        balanceContainer = findViewById(R.id.balanceContainer);
        balanceTextView = findViewById(R.id.balanceTextView);
        checkBalanceButton = findViewById(R.id.checkBalanceButton);

        checkBalanceButton.setOnClickListener(v -> checkBalance());
    }

    private void checkBalance() {
        String enteredPin = pinEditText.getText().toString().trim();

        if (enteredPin.isEmpty()) {
            Toast.makeText(this, "Please enter your PIN", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = openOrCreateDatabase("BankingApp.db", MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT bank_account, balance FROM users WHERE password = ?", new String[]{enteredPin});

        if (cursor.moveToFirst()) {
            String account = cursor.getString(0);
            double balance = cursor.getDouble(1);

            balanceContainer.setVisibility(View.VISIBLE);
            balanceTextView.setText("Account: " + account + "\nYour Balance: ₹" + balance);
        } else {
            Toast.makeText(this, "Invalid PIN. Please try again.", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }
}
