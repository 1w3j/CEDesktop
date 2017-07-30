package com.casaelida.desktop.utils;

import com.jfoenix.concurrency.JFXUtilities;
import com.jfoenix.controls.JFXDrawer;
import io.datafx.controller.ViewConfiguration;
import javafx.scene.Node;
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
}
