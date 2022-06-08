package com.upclicks.ffc.ui.chat.interfaces;

import androidx.annotation.Nullable;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.MessageContentType;

public interface CustomMessageContentType extends MessageContentType {


    interface Location extends IMessage {
        @Nullable
        String getImageUrl();
    }

}
