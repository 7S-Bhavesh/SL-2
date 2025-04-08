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


    private final double accountBalance = 1500.75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_checker);


        pinEditText = findViewById(R.id.pinEditText);
        balanceContainer = findViewById(R.id.balanceContainer);
        balanceTextView = findViewById(R.id.balanceTextView);
        checkBalanceButton = findViewById(R.id.checkBalanceButton);


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


    private boolean validatePin(String pin) {
        return pin.equals("1234"); // Example PIN for testing
    }
}
