package com.example.bankingapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TransferActivity extends AppCompatActivity {
    private EditText amountEditText;
    private Button sendButton;

    private String receiverName;
    private String senderPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer);

        amountEditText = findViewById(R.id.editTextAmount);
        sendButton = findViewById(R.id.sending);

        // Get sender & receiver info from intent
        receiverName = getIntent().getStringExtra("receiverName");
        senderPassword = getIntent().getStringExtra("senderPassword");

        sendButton.setOnClickListener(v -> sendMoney());
    }

    private void sendMoney() {
        String amountStr = amountEditText.getText().toString().trim();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        if (amount <= 0) {
            Toast.makeText(this, "Amount must be greater than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = openOrCreateDatabase("BankingApp.db", MODE_PRIVATE, null);

        // 1. Fetch sender
        Cursor senderCursor = db.rawQuery("SELECT balance FROM users WHERE password = ?", new String[]{senderPassword});
        if (!senderCursor.moveToFirst()) {
            Toast.makeText(this, "Sender not found", Toast.LENGTH_SHORT).show();
            senderCursor.close();
            db.close();
            return;
        }

        double senderBalance = senderCursor.getDouble(0);
        senderCursor.close();

        if (senderBalance < amount) {
            Toast.makeText(this, "Insufficient balance", Toast.LENGTH_SHORT).show();
            db.close();
            return;
        }

        // 2. Deduct from sender
        db.execSQL("UPDATE users SET balance = balance - ? WHERE password = ?", new Object[]{amount, senderPassword});

        // 3. Add to receiver by name
        Cursor receiverCursor = db.rawQuery("SELECT balance FROM users WHERE name = ?", new String[]{receiverName});
        if (receiverCursor.moveToFirst()) {
            db.execSQL("UPDATE users SET balance = balance + ? WHERE name = ?", new Object[]{amount, receiverName});
        } else {
            Toast.makeText(this, "Receiver not found", Toast.LENGTH_SHORT).show();
        }

        receiverCursor.close();
        db.close();

        Toast.makeText(this, "Money transferred to " + receiverName, Toast.LENGTH_SHORT).show();
        finish();
    }
}
