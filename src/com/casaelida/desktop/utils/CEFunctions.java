package com.casaelida.desktop.utils;

import com.jfoenix.concurrency.JFXUtilities;
import java.util.Collection;
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

    public static final void runAfterDelay(Runnable task, int delay) {
        Timer timer = new Timer(delay, e -> {
            JFXUtilities.runInFX(task);
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static final void requestFocus(Node node, int delay) {
        CEFunctions.runAfterDelay(() -> node.requestFocus(), delay);
    }

    public static final Color hex2Rgb(String colorHexString, double opacity) {
        return Color.rgb(
                Integer.valueOf(colorHexString.substring(1, 3), 16),
                Integer.valueOf(colorHexString.substring(3, 5), 16),
                Integer.valueOf(colorHexString.substring(5, 7), 16),
                opacity
        );
    }

    public static final Tooltip createTooltip(String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        return tooltip;
    }

    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }
}
