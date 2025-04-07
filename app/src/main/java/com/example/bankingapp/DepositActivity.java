package com.example.bankingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class DepositActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private MaterialButton buttonDeposit;
    private double accountBalance = 1500.75; // Simulated initial balance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        editTextAmount = findViewById(R.id.editTextAmount);
        buttonDeposit = findViewById(R.id.buttonDeposit);

        // Set click listener for deposit button
        buttonDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depositFunds();
            }
        });
    }

    private void depositFunds() {
        String amountStr = editTextAmount.getText().toString().trim();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double depositAmount = Double.parseDouble(amountStr);

            if (depositAmount <= 0) {
                Toast.makeText(this, "Enter a valid amount greater than zero", Toast.LENGTH_SHORT).show();
            } else {
                accountBalance += depositAmount; // Update balance
                Toast.makeText(this, "Deposit successful! New balance: $" + accountBalance, Toast.LENGTH_LONG).show();
                editTextAmount.setText(""); // Clear input field after deposit
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
        }
    }
}
