package com.ce.desktop.utils;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 1w3j
 */
public class CEValidEmailValidator extends ValidatorBase {

    @Override
    protected void eval () {
        evalValidEmailAddress();
    }

    private void evalValidEmailAddress () {
        TextInputControl textField = (TextInputControl) super.srcControl.get();
        String emailPatternString = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern emailPattern = Pattern.compile(emailPatternString);
        Matcher emailMatcher = emailPattern.matcher(textField.getText());
        super.hasErrors.set(!emailMatcher.matches());
    }

}
