package com.upclicks.ffcapp.ui.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.upclicks.ffcapp.R;
import com.upclicks.ffcapp.databinding.ConfirmDialogLayoutBinding;


import libs.mjn.prettydialog.PrettyDialog;

public class ConfirmDialog extends PrettyDialog {
    ConfirmDialogLayoutBinding binding;
    public ConfirmDialog(@NonNull Context context,String message,ConfirmClickActions confirmClickActions) {
        super(context);
        setAnimationEnabled(true);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.confirm_dialog_layout, null, false);
        setContentView(binding.getRoot());
        binding.setMsg(message);
        binding.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmClickActions.onConfirmed();
                dismiss();
            }
        });

        binding.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public interface ConfirmClickActions{
        void onConfirmed();
    }
}
