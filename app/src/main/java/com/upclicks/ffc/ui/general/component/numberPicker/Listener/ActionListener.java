package com.upclicks.ffc.ui.general.component.numberPicker.Listener;

import android.view.View;
import android.widget.EditText;

import com.upclicks.ffc.ui.general.component.numberPicker.Enums.ActionEnum;
import com.upclicks.ffc.ui.general.component.numberPicker.NumberPicker;


/**
 * Created by travijuu on 26/05/16.
 */
public class ActionListener implements View.OnClickListener {

    NumberPicker layout;
    ActionEnum action;
    EditText display;

    public ActionListener(NumberPicker layout, EditText display, ActionEnum action) {
        this.layout = layout;
        this.action = action;
        this.display = display;
    }

    @Override
    public void onClick(View v) {
        try {
            int newValue = Integer.parseInt(this.display.getText().toString());

            if (!this.layout.valueIsAllowed(newValue)) {
                return;
            }

            this.layout.setValue(newValue);
        } catch (NumberFormatException e) {
            this.layout.refresh();
        }

        switch (this.action) {
            case INCREMENT:
                this.layout.increment();
                break;
            case DECREMENT:
                this.layout.decrement();
                break;
        }
    }
}