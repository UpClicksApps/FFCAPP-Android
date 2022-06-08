package com.upclicks.ffc.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.upclicks.ffc.R;
import com.upclicks.ffc.databinding.DilaogRemoveChatMessageBinding;
import com.upclicks.ffc.ui.chat.data.model.Message;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

public class RemoveChatMessageDialog extends BottomSheetDialog {
    DilaogRemoveChatMessageBinding binding;

    public RemoveChatMessageDialog(@NonNull Context context, Message message, RemoveMessageDialogActions removeMessageDialogActions) {
        super(context);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dilaog_remove_chat_message, null, false);
        setContentView(binding.getRoot());
        binding.container.setOnClickListener(view -> {
            removeMessageDialogActions.onRemove(view, message);
            dismiss();
        });
        binding.remove.setOnClickListener(view -> {
            binding.container.performClick();
        });
    }

    public interface RemoveMessageDialogActions {
        void onRemove(View view, Message message);
    }
}