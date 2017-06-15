package com.casaelida.desktop.utils;

import com.jfoenix.validation.base.ValidatorBase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author iqbal
 */
public class ValidEmailValidator extends ValidatorBase {

    private final String emailPatternString = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    
    @Override
    protected void eval() {
        evalValidEmailAddress();
    }

    private void evalValidEmailAddress() {
        TextInputControl textField = (TextInputControl) super.srcControl.get();
        Pattern emailPattern = Pattern.compile(emailPatternString);
        Matcher emailMatcher = emailPattern.matcher(textField.getText());
        if (!emailMatcher.matches()) {
            super.hasErrors.set(true);
        } else {
            super.hasErrors.set(false);
        }
    }
    
}
