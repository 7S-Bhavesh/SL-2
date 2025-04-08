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

        Button exitButton = findViewById(R.id.button7);
        Button Beneficiary = findViewById(R.id.button5);
        Button govt = findViewById(R.id.button6);
        Button transfer = findViewById(R.id.button9);

        checkBalanceButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CheckBalanceActivity.class);
            startActivity(intent);
        });


        Beneficiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(HomeActivity.this,BeneficiaryManagementActivity.class);
               startActivity(i);

            }
        });
        govt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,GovtActivity.class);
                startActivity(i);
            }
        });
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,TransferMoneyActivity.class);
                startActivity(i);
            }
        });

        exitButton.setOnClickListener(v -> finishAffinity());
    }
}
