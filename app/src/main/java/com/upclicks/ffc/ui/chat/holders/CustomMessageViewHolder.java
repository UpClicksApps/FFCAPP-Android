package com.upclicks.ffc.ui.chat.holders;


import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.gson.Gson;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.upclicks.ffc.R;
import com.upclicks.ffc.session.SessionHelper;
import com.upclicks.ffc.ui.chat.ChatActivity;
import com.upclicks.ffc.ui.chat.data.model.MediaFile;
import com.upclicks.ffc.ui.chat.data.model.Message;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class CustomMessageViewHolder extends MessageHolders.BaseIncomingMessageViewHolder<Message> implements View.OnLongClickListener {
    protected ViewGroup bubble;
    protected TextView content;
    public static LongPress onLongPress;

    @Deprecated
    public CustomMessageViewHolder(View itemView) {
        super(itemView);
        initUi(itemView);
    }

    public CustomMessageViewHolder(View itemView, Object payload) {
        super(itemView, payload);
        initUi(itemView);
    }

    Message message;

    @Override
    public void onBind(Message message) {
        super.onBind(message);
        this.message = message;
        setUpTextAndLinkMessage(message);
        handleLongClickAction();
    }

    private void initUi(View itemView) {
        bubble = itemView.findViewById(R.id.bubble);
        //text
        content = itemView.findViewById(R.id.contentText);

    }

    // set up ui clicks for all views on item
    private void handleLongClickAction() {
        itemView.setOnLongClickListener(this::onLongClick);
        content.setOnLongClickListener(this::onLongClick);
        time.setOnLongClickListener(this::onLongClick);
    }


    //set up normal message (text and link preview)
    private void setUpTextAndLinkMessage(Message message) {
        content.setText(message.getText());
    }

    @Override
    public boolean onLongClick(View view) {
        onLongPress.onLongClick(message);
        return true;
    }

    public interface LongPress {
        void onLongClick(Message message);
    }

    public static void initLongPressAction(LongPress longPress) {
        onLongPress = longPress;
    }
}