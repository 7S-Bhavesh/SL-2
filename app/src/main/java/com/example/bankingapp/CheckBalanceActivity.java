package com.example.bankingapp;

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

    // Simulated account balance (Replace with real data retrieval logic)
    private final double accountBalance = 1500.75; // Example balance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_checker);

        // Initialize UI elements
        pinEditText = findViewById(R.id.pinEditText);
        balanceContainer = findViewById(R.id.balanceContainer);
        balanceTextView = findViewById(R.id.balanceTextView);
        checkBalanceButton = findViewById(R.id.checkBalanceButton);

        // Set up button click listener
        checkBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBalance();
            }
        });
    }

    private void checkBalance() {
        String enteredPin = pinEditText.getText().toString().trim();

        if (enteredPin.isEmpty()) {
            Toast.makeText(this, "Please enter your PIN", Toast.LENGTH_SHORT).show();
            return;
        }

        if (validatePin(enteredPin)) {
            balanceContainer.setVisibility(View.VISIBLE);
            balanceTextView.setText("Your Balance: $" + accountBalance);
        } else {
            Toast.makeText(this, "Invalid PIN. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    // Simulated PIN validation (Replace with actual authentication logic)
    private boolean validatePin(String pin) {
        return pin.equals("1234"); // Example PIN for testing
    }
}
