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
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.fxmisc.cssfx.CSSFX;
import org.fxmisc.cssfx.api.URIToPathConverter;

import java.nio.file.Paths;
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

    /**
     * Creates and Run CEDesktop environment
     * @param casaElidaStage Root {@link Stage} for the CEDesktop application
     * @throws FlowException Flow API default exception type
     */
    @Override public void start (Stage casaElidaStage) throws FlowException {
        Locale.setDefault(Locale.forLanguageTag("en"));
        this.casaElidaFlowContext = ApplicationContext.getInstance();
        this.casaElidaFlowContext.register(CasaElida.STAGE, casaElidaStage);
        //Initial and main flow in the application
        CEBundledFlow casaElidaFlow = new CEBundledFlow(App.CLASS, App.Strings.BUNDLE);
        //ViewFlowContet here not really used since AppFlowContext covers the whole application's contexts
        FlowHandler casaElidaFlowHandler = casaElidaFlow.createHandler();
        this.root = casaElidaFlowHandler.start(new CEAnimatedFlowContainer());
        this.casaElidaStage = casaElidaStage; //Make the root stage 'global'
        this.casaElidaStage.setMinWidth(CasaElida.MIN_WIDTH); //The same as -fx-max-width for auth-step-pane
        this.casaElidaStage.setMinHeight(CasaElida.MIN_HEIGHT + App.TOOLBAR_HEIGHT); //do not hide logo side of auth-step-pane when resizing
        this.casaElidaStage.setTitle(App.Strings.WINDOW_TITLE);
        this.window = new CEWindowDecorator(casaElidaStage, this.root);
        this.window.setPrefSize(CasaElida.MIN_WIDTH, CasaElida.MIN_HEIGHT); //When user wants to drags or resize the window, preferred behaviour
        this.window.setMaximized(true); //Start in maximized mode
        this.window.setCustomMaximize(true); //Putting this, fixed unwanted initial minimized state of the app
        this.scene = new Scene(this.window);
        this.casaElidaStage.setScene(this.scene);
        this.casaElidaFlowContext.register(CasaElida.SCENE, this.scene);
        //new JFXResponsiveHandler(this.casaElidaStage, JFXResponsiveHandler.PSEUDO_CLASS_LARGE);
        loadMetadata();
        this.casaElidaStage.show();
        CSSFX.addConverter( uri -> //CSSFX is still a buggy library, when navigating through flow views, ClassNotFound is raised.
                Paths.get(System.getProperty("user.dir") + "/src/main/resources" + Meta.Stylesheets.CASAELIDA_STYLESHEET)
        ).start();
        //org.scenicview.ScenicView.show(this.scene);
    }

    /**
     * Loads the Material Design Roboto Font from the resources folder, as well as casaelida.css to
     * the JFXDecorator window stylesheets list and finally sets default logo icon for the root stage.
     */
    private void loadMetadata () {
        Font.loadFont(Meta.Fonts.ROBOTO_BLACK_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_BOLD_ITALIC_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_BOLD_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_LIGHT_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_MEDIUM_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_REGULAR_STREAM, 22);
        this.window.getStylesheets().addAll(
                Meta.Stylesheets.CASAELIDA_STYLESHEET
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
