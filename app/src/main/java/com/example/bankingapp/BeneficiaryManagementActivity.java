package com.example.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BeneficiaryManagementActivity extends AppCompatActivity {
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beneficiary);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvAccountNumber = findViewById(R.id.tvAccountNumber);
       btn=findViewById(R.id.money);

      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(BeneficiaryManagementActivity.this,TransferActivity.class);
              startActivity(i);
          }
      });




    }
}
