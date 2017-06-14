package com.casaelida.desktop.utils;

import com.jfoenix.concurrency.JFXUtilities;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javax.swing.Timer;

/**
 *
 * @author iqbal
 */
public final class CEFunctions {
    private static boolean isValid = false;
    private static int focusedTimesCounter = 0;

    public static final void requestFocus(Node node, int delay) {
        Timer passwordTimer = new Timer(delay, e -> {
            JFXUtilities.runInFX(() -> {
                node.requestFocus();
            });
        });
        passwordTimer.setRepeats(false);
        passwordTimer.start();
    }

    public static final Color hex2Rgb(String colorHexString, double opacity) {
        return Color.rgb(
                Integer.valueOf(colorHexString.substring(1, 3), 16),
                Integer.valueOf(colorHexString.substring(3, 5), 16),
                Integer.valueOf(colorHexString.substring(5, 7), 16),
                opacity
        );
    }
    
    public static final Tooltip createTooltip(String message){
        Tooltip tooltip = new Tooltip(message);
        tooltip.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        return tooltip;
    }
    
    public static final void createValidateOnFocusHandler(TextField jfxTextfield, String errorMessage) throws Exception{
        if(jfxTextfield instanceof JFXTextField || jfxTextfield instanceof JFXPasswordField){
            jfxTextfield.setUserData(isValid);
            RequiredFieldValidator emailRequiredValidator = new RequiredFieldValidator();
            emailRequiredValidator.setMessage(errorMessage);
            emailRequiredValidator.setIcon(new MaterialIconView(MaterialIcon.WARNING, "22.5"));
            
            if(jfxTextfield instanceof JFXTextField) ((JFXTextField)jfxTextfield).setValidators(emailRequiredValidator);
            else ((JFXPasswordField)jfxTextfield).setValidators(emailRequiredValidator);
            System.out.println("outSide = " +isValid);
            jfxTextfield.focusedProperty().addListener((ChangeListener<Boolean>) (ObservableValue<? extends Boolean> obs, Boolean wasFocused, Boolean isFocusedNow) -> {
                if(!isFocusedNow) focusedTimesCounter++;
                if(focusedTimesCounter>1){
                    if(!isFocusedNow) isValid = jfxTextfield instanceof JFXTextField ? ((JFXTextField)jfxTextfield).validate() : ((JFXPasswordField)jfxTextfield).validate();
                    System.out.println("outSide = " +isValid);
                    if(!isValid && !wasFocused) {
                        jfxTextfield.setStyle("-fx-prompt-text-fill: ce-white;-jfx-focus-color: ce-white;-jfx-unfocus-color: ce-white;");
                    }
                    else {
                        jfxTextfield.setStyle("-fx-prompt-text-fill: ce-yellow;-jfx-focus-color: ce-yellow-hover;-jfx-unfocus-color: ce-yellow;");
                        jfxTextfield.lookup(".input-line").setStyle("-fx-background-color:ce-yellow;");
                    }
                    if(!isFocusedNow && !isValid) jfxTextfield.lookup(".input-line").setStyle("-fx-background-color:ce-white;");
                    System.out.println("outSide = " +isValid);
                }
            });
            System.out.println("outSide = " +isValid);
            jfxTextfield.setUserData(isValid);
        }else throw new Exception("TextField instance is not a JFXPassword or JFXTextField");
    }
    
}
