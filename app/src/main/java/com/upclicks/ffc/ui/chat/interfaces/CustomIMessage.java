package com.upclicks.ffc.ui.chat.interfaces;

import com.stfalcon.chatkit.commons.models.MessageContentType;
public interface CustomIMessage extends MessageContentType {

    String getImage();
    String getLongitude();
    String getLatitude();
}
