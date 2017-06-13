package com.casaelida.desktop.utils;

import com.jfoenix.concurrency.JFXUtilities;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javax.swing.Timer;

/**
 *
 * @author iqbal
 */
public final class CEFunctions {

    public static final void requestFocus(Node node, int delay) {
        Timer passwordTimer = new Timer(delay, e -> {
            JFXUtilities.runInFX(() -> {
                node.requestFocus();
            });
        });
        passwordTimer.setRepeats(false);
        passwordTimer.start();
    }

    public static final Color hex2Rgb(String colorStr, double opacity) {
        return Color.rgb(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16),
                opacity
        );
    }
}
