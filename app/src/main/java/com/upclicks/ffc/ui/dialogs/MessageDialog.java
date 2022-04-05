package com.upclicks.ffc.ui.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.upclicks.ffc.R;
import com.upclicks.ffc.databinding.MessageDialogLayoutBinding;

import libs.mjn.prettydialog.PrettyDialog;


public class MessageDialog extends PrettyDialog {
    MessageDialogLayoutBinding binding;
   public MessageDialog(Context context,String msg) {
        super(context);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.message_dialog_layout, null, false);
        setContentView(binding.getRoot());
        binding.setMsg(msg);
        binding.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
