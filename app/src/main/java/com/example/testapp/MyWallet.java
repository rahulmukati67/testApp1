package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

public class MyWallet extends AppCompatActivity {
    Button ApplyCoupon , Proceed ,TransferApplyCoupon,TransferProceed;
    TextInputEditText amount ,amount1;
    ChipGroup  amountChipGroup,amountChipGroup1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ApplyCoupon = findViewById(R.id.btnApplyCoupon);
        Proceed = findViewById(R.id.btnProceed);
        TransferApplyCoupon = findViewById(R.id.btnTransferApplyCoupon);
        TransferProceed = findViewById(R.id.btnTransferProceed);
        amountChipGroup=findViewById(R.id.amountChipGroup);
        amountChipGroup1=findViewById(R.id.amountChipGroup1);
        amount = findViewById(R.id.amount);
        amount1 = findViewById(R.id.amount1);

        ApplyCouponClickListener(ApplyCoupon);
        ProceedClickListener(Proceed);
        ApplyCouponClickListener(TransferApplyCoupon);
        ProceedClickListener(TransferProceed);
        setAmount(amountChipGroup , amount);
        setAmount(amountChipGroup1, amount1);

    }

    private  void ApplyCouponClickListener(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyWallet.this , "Coupon Applied" , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  void ProceedClickListener(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyWallet.this , "Payment  Proceed" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void setAmount(ChipGroup chipGroup ,TextInputEditText text ){
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        String value = chip.getText().toString();
                        String numericValue = value.replaceAll("[^0-9]", "");
                        text.setText(numericValue);
                    }else{
                        text.setText("");
                    }
                }
            });
        }
    }
}