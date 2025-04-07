package com.example.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button checkBalanceButton = findViewById(R.id.button);
        Button withdrawalButton = findViewById(R.id.button2);
        Button depositButton = findViewById(R.id.button3);
        Button exitButton = findViewById(R.id.button4);

        checkBalanceButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CheckBalanceActivity.class);
            startActivity(intent);
        });

        withdrawalButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, WithdrawActivity.class);
            startActivity(intent);
        });

        depositButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DepositActivity.class);
            startActivity(intent);
        });

        exitButton.setOnClickListener(v -> finishAffinity());
    }
}
