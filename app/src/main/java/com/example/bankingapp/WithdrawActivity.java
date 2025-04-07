package com.example.bankingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class WithdrawActivity extends AppCompatActivity {

    private EditText amountEditText;
    private MaterialButton withdrawButton;
    private TextView errorTextView;
    private double accountBalance = 1500.75; // Simulated initial balance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        amountEditText = findViewById(R.id.amountEditText);
        withdrawButton = findViewById(R.id.withdrawButton);
        errorTextView = findViewById(R.id.errorTextView);

        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdrawFunds();
            }
        });
    }

    private void withdrawFunds() {
        String amountStr = amountEditText.getText().toString().trim();


        errorTextView.setVisibility(View.GONE);

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double withdrawalAmount = Double.parseDouble(amountStr);

            if (withdrawalAmount <= 0) {
                showError("Enter a valid amount greater than zero.");
            } else if (withdrawalAmount > accountBalance) {
                showError("Insufficient balance.");
            } else {
                accountBalance -= withdrawalAmount; // Update balance
                Toast.makeText(this, "Withdrawal successful! New balance: $" + accountBalance, Toast.LENGTH_LONG).show();
                amountEditText.setText(""); // Clear input field
            }
        } catch (NumberFormatException e) {
            showError("Invalid amount entered");
        }
    }

    private void showError(String message) {
        errorTextView.setText(message);
        errorTextView.setVisibility(View.VISIBLE);
    }
}
