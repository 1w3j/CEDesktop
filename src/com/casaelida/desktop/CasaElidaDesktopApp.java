package com.casaelida.desktop;

import com.casaelida.desktop.utils.CEConstants.CasaElida;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
import com.casaelida.desktop.utils.CEConstants.Meta;
import com.casaelida.desktop.utils.CEWindowDecorator;
import com.casaelida.desktop.utils.datafx.CEAnimatedFlowContainer;
import com.casaelida.desktop.utils.datafx.CEBundledFlow;
import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.context.ApplicationContext;
import io.datafx.controller.context.FXMLApplicationContext;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.fxmisc.cssfx.CSSFX;

import java.util.Locale;

/**
 * @author iqbal
 * TODO: Add responsiveness
 * @version 0.0.1
 */
public class CasaElidaDesktopApp extends Application {
    //Always register context objects BEFORE calling the flowHandler.start() method
    @FXMLApplicationContext private ApplicationContext casaElidaFlowContext;

    private Scene scene;
    private Stage casaElidaStage;
    private JFXDecorator window;
    private StackPane root;

    public static void main (String[] args) {
        CasaElidaDesktopApp.launch(args);
    }

    @Override public void start (Stage casaElidaStage) throws FlowException {
        Locale.setDefault(Locale.forLanguageTag("en"));
        this.casaElidaFlowContext = ApplicationContext.getInstance();
        this.casaElidaFlowContext.register(CasaElida.STAGE, casaElidaStage);
        //Initial and main flow in the application
        CEBundledFlow casaElidaFlow = new CEBundledFlow(App.CLASS, App.Strings.BUNDLE);
        //ViewFlowContext here not really used
        FlowHandler casaElidaFlowHandler = casaElidaFlow.createHandler(new ViewFlowContext());
        this.root = casaElidaFlowHandler.start(new CEAnimatedFlowContainer());
        this.casaElidaStage = casaElidaStage;
        this.casaElidaStage.setMinWidth(CasaElida.MIN_WIDTH);
        this.casaElidaStage.setMinHeight(CasaElida.MIN_HEIGHT + App.TOOLBAR_HEIGHT);
        this.casaElidaStage.setTitle(App.Strings.WINDOW_TITLE);
        this.window = new CEWindowDecorator(casaElidaStage, this.root);
        this.window.setMaximized(true);
        this.window.setPrefSize(CasaElida.MIN_WIDTH, CasaElida.MIN_HEIGHT);
        this.scene = new Scene(this.window);
        this.casaElidaStage.setScene(this.scene);
        this.casaElidaFlowContext.register(CasaElida.SCENE, this.scene);
        //new JFXResponsiveHandler(this.casaElidaStage, JFXResponsiveHandler.PSEUDO_CLASS_LARGE);
        loadMetadata();
        this.casaElidaStage.show();
        //org.scenicview.ScenicView.show(this.scene);
        //CSSFX.start();
    }

    private void loadMetadata () {
        Font.loadFont(Meta.Fonts.ROBOTO_BLACK_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_BOLD_ITALIC_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_BOLD_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_LIGHT_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_MEDIUM_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_REGULAR_STREAM, 22);
        this.window.getStylesheets().addAll(
                Meta.Stylesheets.VARIABLES_STYLESHEET,
                Meta.Stylesheets.APP_STYLESHEET,
                Meta.Stylesheets.SIDEMENU_STYLESHEET,
                Meta.Stylesheets.LOGIN_STYLESHEET,
                Meta.Stylesheets.MAIN_STYLESHEET
        );
        this.casaElidaStage.getIcons().addAll(Meta.Icons.ICON256X256,
                Meta.Icons.ICON128X128,
                Meta.Icons.ICON64X64,
                Meta.Icons.ICON56X56,
                Meta.Icons.ICON28X28,
                Meta.Icons.ICON16X16
        );
    }
}
