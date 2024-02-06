package com.example.testapp;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.example.testapp.databinding.FragmentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    BottomSheetDialog dialog;
    BottomSheetBehavior<View> bottomSheetBehaviour;
    Button btnUpdate;
    private FragmentBottomSheetBinding binding;
    EditText ediTextBottomSheet;
    private OnTextChangeListener textChangeListener;
//    ImageView btnsave , btnDownload,btnCopyLink,btnBlockUser,btnReport;
//    TextView txtReport ,txtBlockUser ,txtCopyLink,txtSave,txtDownload;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        return dialog;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ediTextBottomSheet = dialog.findViewById(R.id.ediTextBottomSheet);

        bottomSheetBehaviour = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
        CoordinatorLayout layout = dialog.findViewById(R.id.BottomSheetLayout);
        assert layout != null;
        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = ediTextBottomSheet.getText().toString();
                if (textChangeListener != null) {
                    textChangeListener.onTextChange(newText);
                }
                if(bottomSheetBehaviour.getState() != BottomSheetBehavior.STATE_HIDDEN){
                    bottomSheetBehaviour.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });

        View.OnClickListener commonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v ==  binding.btnDownload ||v ==  binding.txtDownload ) {
                    Toast.makeText(getContext(), "Click on Download", Toast.LENGTH_SHORT).show();
                }else  if (v ==  binding.btnsave ||v ==  binding.txtSave ) {
                    Toast.makeText(getContext(), "Click on Save", Toast.LENGTH_SHORT).show();
                }else  if (v ==  binding.btnReport ||v ==  binding.txtReport ) {
                    Toast.makeText(getContext(), "Click on Report", Toast.LENGTH_SHORT).show();
                }else if (v ==  binding.btnCopyLink ||v ==  binding.txtcopyLink ) {
                    Toast.makeText(getContext(), "Click on CopyLink", Toast.LENGTH_SHORT).show();
                }else if (v ==  binding.btnBlockUser ||v ==  binding.txtBlockUser ) {
                    Toast.makeText(getContext(), "Click on Block User", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            textChangeListener = (OnTextChangeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnTextChangeListener");
        }
    }

    public interface OnTextChangeListener {
        void onTextChange(String newText);
    }
}
