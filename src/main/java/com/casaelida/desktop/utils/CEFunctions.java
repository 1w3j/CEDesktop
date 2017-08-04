package com.casaelida.desktop.utils;

import com.jfoenix.concurrency.JFXUtilities;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.ViewConfiguration;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * @author iqbal
 */
public final class CEFunctions {

    public static void runAfterDelay (Runnable task, int delay) {
        Timer timer = new Timer(delay, e -> JFXUtilities.runInFX(task));
        timer.setRepeats(false);
        timer.start();
    }

    public static void requestFocus (Node node, int delay) {
        CEFunctions.runAfterDelay(node::requestFocus, delay);
    }

    public static Tooltip createTooltip (String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        return tooltip;
    }

    public static Tooltip createTooltip () {
        return createTooltip("");
    }

    static boolean isEmpty (final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static ViewConfiguration newBundledConfig (ResourceBundle bundle) {
        ViewConfiguration viewConfig = new ViewConfiguration();
        viewConfig.setResources(bundle);
        return viewConfig;
    }

    public static void initDrawer (JFXDrawer drawer, Node sidemenuButton) {
        drawer.setDefaultDrawerSize(CEConstants.CasaElida.App.SIDEMENU_SIZE);
        sidemenuButton.setOnMouseClicked(e -> {
            if (drawer.isHidden() || drawer.isHiding()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });
    }

    public static void toggleDrawer (JFXDrawer drawer) {
        if (drawer.isHidden() || drawer.isHiding()) {
            drawer.open();
        } else {
            drawer.close();
        }
    }

    //Initialize Listener for any time the user clicks on the text field (or TAB...)
    public static void initFocusValidationStyling (TextField textField, boolean startedValidating) {
        boolean isJFXPasswordField = false;
        if(textField instanceof JFXPasswordField) isJFXPasswordField = true;
        boolean finalIsJFXPasswordField = isJFXPasswordField;//make isJFXPasswordField effectively final (?)
        textField.focusedProperty().addListener((ObservableValue<? extends Boolean> obs, Boolean wasFocused, Boolean isFocusedNow) -> {
            if (startedValidating) {
                boolean isValid = false;
                if (!isFocusedNow) isValid = finalIsJFXPasswordField ? ((JFXPasswordField) textField).validate() : ((JFXTextField) textField).validate();
                if (!isValid && !wasFocused)
                    textField.setStyle("-fx-prompt-text-fill: ce-white;-jfx-focus-color: ce-white;-jfx-unfocus-color: ce-white;");
                else {
                    textField.setStyle("-fx-prompt-text-fill: ce-yellow;-jfx-focus-color: ce-yellow-hover;-jfx-unfocus-color: ce-yellow;");
                    textField.lookup(".input-line").setStyle("-fx-background-color:ce-yellow;");
                }
                if (!isValid && !isFocusedNow)
                    textField.lookup(".input-line").setStyle("-fx-background-color:ce-white;");
            }
        });
    }


    //Used when the user clicks the 'next button'
    public static void unfocusedValidationStyling (TextField textField, boolean isValid) {
        if (!isValid && textField.isFocused())
            textField.setStyle("-fx-prompt-text-fill: ce-white;-jfx-focus-color: ce-white;-jfx-unfocus-color: ce-white;");
        else {
            textField.setStyle("-fx-prompt-text-fill: ce-yellow;-jfx-focus-color: ce-yellow-hover;-jfx-unfocus-color: ce-yellow;");
            textField.lookup(".input-line").setStyle("-fx-background-color:ce-yellow;");
        }
        if (!isValid && !textField.isFocused())
            textField.lookup(".input-line").setStyle("-fx-background-color:ce-white;");
    }
}
