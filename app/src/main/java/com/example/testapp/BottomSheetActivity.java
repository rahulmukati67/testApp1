package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.databinding.ActivityBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;



public class BottomSheetActivity extends AppCompatActivity implements BottomSheetFragment.OnTextChangeListener {
        private Button btnBottomSheet , btnBottomSheetFragment;
        private TextView txtBottomSheet;
        private ActivityBottomSheetBinding  binding;
      ImageView btnsave , btnDownload,btnCopyLink,btnBlockUser,btnReport;
      TextView txtReport ,txtBlockUser ,txtCopyLink,txtSave,txtDownload;
    private BottomSheetBehavior<FrameLayout> bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomSheetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btnBottomSheet = findViewById(R.id.btnBottomSheet);
        txtBottomSheet = findViewById(R.id.txtBottomSheet);
        btnBottomSheetFragment = findViewById(R.id.btnBottomSheetFragment);
        FrameLayout sheet = findViewById(R.id.sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(sheet);

        View rootView = findViewById(R.id.BottomSheetActivity);
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = BottomSheetBehavior.from(sheet);

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(bottomSheetBehavior.getState() ==BottomSheetBehavior.STATE_HALF_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                return false;
            }
        });

        btnBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetBehavior.getState() ==BottomSheetBehavior.STATE_HALF_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                }
            }
        });

        bottomSheetBehavior.setPeekHeight(30);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        btnBottomSheetFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HALF_EXPANDED
                        || bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        buttonClicks();
    }
    @Override
    public void onTextChange(String newText) {
        txtBottomSheet.setText(newText);
    }

    private  void buttonClicks() {
        View.OnClickListener commonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == binding.btnDownload || v == binding.txtDownload) {
                    Toast.makeText(BottomSheetActivity.this, "Click on Download", Toast.LENGTH_SHORT).show();
                } else if (v == binding.btnsave || v == binding.txtSave) {
                    Toast.makeText(BottomSheetActivity.this, "Click on Save", Toast.LENGTH_SHORT).show();
                } else if (v == binding.btnReport || v == binding.txtReport) {
                    Toast.makeText(BottomSheetActivity.this, "Click on Report", Toast.LENGTH_SHORT).show();
                } else if (v == binding.btnCopyLink || v == binding.txtcopyLink) {
                    Toast.makeText(BottomSheetActivity.this, "Click on CopyLink", Toast.LENGTH_SHORT).show();
                } else if (v == binding.btnBlockUser || v == binding.txtBlockUser) {
                    Toast.makeText(BottomSheetActivity.this, "Click on Block User", Toast.LENGTH_SHORT).show();
                }
            }
        };
        binding.btnDownload.setOnClickListener(commonClickListener);
        binding.txtDownload.setOnClickListener(commonClickListener);
        binding.btnsave.setOnClickListener(commonClickListener);
        binding.txtSave.setOnClickListener(commonClickListener);
        binding.btnReport.setOnClickListener(commonClickListener);
        binding.txtReport.setOnClickListener(commonClickListener);
        binding.btnCopyLink.setOnClickListener(commonClickListener);
        binding.txtcopyLink.setOnClickListener(commonClickListener);
        binding.btnBlockUser.setOnClickListener(commonClickListener);
        binding.txtBlockUser.setOnClickListener(commonClickListener);
    }
}
