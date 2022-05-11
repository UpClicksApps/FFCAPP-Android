package com.upclicks.ffc.ui.general.component.numberPicker.Interface;


import com.upclicks.ffc.ui.general.component.numberPicker.Enums.ActionEnum;

/**
 * Created by travijuu on 19/12/16.
 */

public interface ValueChangedListener {

    void valueChanged(int value, ActionEnum action);
}
