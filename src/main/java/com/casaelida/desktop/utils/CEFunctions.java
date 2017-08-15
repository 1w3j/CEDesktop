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

import javax.swing.Timer;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * @author iqbal
 */
public final class CEFunctions {

    /**
     * Makes a {@link Timer} to start the action implemented by <code>task</code> after a period of time.
     * The task will be executed in the JavaFX Application Thread.
     * @param task action to be executed
     * @param delay period of time in milliseconds to wait for the execution
     */
    public static void runAfterDelay (Runnable task, int delay) {
        Timer timer = new Timer(delay, e -> JFXUtilities.runInFX(task));
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Runs {@link CEFunctions}<code>.runAfterDelay</code> to force a {@link Node} object
     * gain focus after a specific period of time.
     * @param node scene component to be manipulated
     * @param delay period of time in milliseconds to wait for the execution
     */
    public static void requestFocus (Node node, int delay) {
        CEFunctions.runAfterDelay(node::requestFocus, delay);
    }

    /**
     * @param message default text for the {@link Tooltip}
     * @return a {@link Tooltip} with a default message
     */
    public static Tooltip createTooltip (String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        return tooltip;
    }

    /**
     * @return a {@link Tooltip} with an empty message
     */
    public static Tooltip createTooltip () {
        return createTooltip("");
    }

    /**
     * Checks whether a {@link Collection} is empty or not
     *
     * @param coll Iterable object to be tested
     * @return true if the <code>coll</code> is not empty
     */
    static boolean isEmpty (final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * Use the specified {@link ResourceBundle} object to create a new {@link ViewConfiguration}
     * using its default constructor before setting the bundle with <code>setResources</code>
     *
     * This method is necessary in order to create "bundled flows" ({@link io.datafx.controller.flow.Flow})
     * The reason why I created was not to write boilerplate instantiations of {@link ViewConfiguration} in each {@link CEController}
     * @param bundle the {@link ResourceBundle} object used for internationalization
     * @return a new {@link ViewConfiguration} with a bundle set as resources.
     */
    public static ViewConfiguration newBundledConfig (ResourceBundle bundle) {
        ViewConfiguration viewConfig = new ViewConfiguration();
        viewConfig.setResources(bundle);
        return viewConfig;
    }

    /**
     * Initializes the Application's drawer with the default size and a open/close
     * toggle functionality set on the specified {@link Node}.
     * @param drawer the drawer to be used
     * @param sidemenuButton the button which is used to toggle opening and closing of the drawer's side menu
     */
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

    /**
     * Makes the specified drawer to open if closed, and close if opened.
     * @param drawer the drawer to be used
     */
    public static void toggleDrawer (JFXDrawer drawer) {
        if (drawer.isHidden() || drawer.isHiding()) {
            drawer.open();
        } else {
            drawer.close();
        }
    }

    /**
     * Adds a listener for any time the user clicks or TABs on the text field in order to style
     * IOW, the {@link TextField} prompt, text, and line colors.
     * Similar to Google's new account verification page
     * @param textField {@link TextField} to be manipulated
     * @param startedValidating boolean indicating if used already clicked the "login button" or pressed ENTER
     */
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

    /**
     * Used when the user clicks the 'next button'.
     * Because when you click a button, anything else but the button loses focus, this method was created
     * to style the specified {@link TextField} with colors according to the new Google's account verification
     * page behaviour (similar to and the contrary of <code>initFocusValidationStyling</code>)
     * @param textField {@link TextField} to be used
     * @param isValid boolean indicating if the {@link TextField} has the right input data or not
     */
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
