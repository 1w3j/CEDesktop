package com.casaelida.desktop.utils;

import com.jfoenix.concurrency.JFXUtilities;
import io.datafx.controller.ViewConfiguration;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.scene.Node;
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

    public static void runAfterDelay(Runnable task, int delay) {
        Timer timer = new Timer(delay, e -> JFXUtilities.runInFX(task));
        timer.setRepeats(false);
        timer.start();
    }

    public static void requestFocus(Node node, int delay) {
        CEFunctions.runAfterDelay(node::requestFocus, delay);
    }

    public static Tooltip createTooltip(String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        return tooltip;
    }

    public static Tooltip createTooltip() {
        return createTooltip("");
    }

    static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    static ViewConfiguration newBundledConfig(ResourceBundle bundle){
        ViewConfiguration viewConfig = new ViewConfiguration();
        viewConfig.setResources(bundle);
        return viewConfig;
    }
}
