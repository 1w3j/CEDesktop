package com.ce.desktop;

import com.ce.desktop.utils.CEConstants.CE;
import com.ce.desktop.utils.CEConstants.CE.App;
import com.ce.desktop.utils.CEConstants.Meta;
import com.ce.desktop.utils.CEWindowDecorator;
import com.ce.desktop.utils.datafx.CEAnimatedFlowContainer;
import com.ce.desktop.utils.datafx.CEBundledFlow;
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

import java.nio.file.Paths;
import java.util.Locale;

/**
 * @author 1w3j
 * TODO: Add responsiveness
 * @version 0.0.1
 */
public class CEDesktopApp extends Application {
    //Always register context objects BEFORE calling the flowHandler.start() method
    @FXMLApplicationContext private ApplicationContext ceFlowContext;

    private Scene ceScene;
    private Stage ceStage;
    private JFXDecorator window;
    private StackPane root;

    public static void main (String[] args) {
        CEDesktopApp.launch(args);
    }

    /**
     * Creates and Run CEDesktop environment
     * @param ceStage Root {@link Stage} for the CEDesktop application
     * @throws FlowException Flow API default exception type
     */
    @Override public void start (Stage ceStage) throws FlowException {
        Locale.setDefault(Locale.forLanguageTag("en"));
        this.ceFlowContext = ApplicationContext.getInstance();
        this.ceFlowContext.register(CE.STAGE, ceStage);
        //Initial and main flow in the application
        CEBundledFlow ceFlow = new CEBundledFlow(App.CLASS, App.Strings.BUNDLE);
        //ViewFlowContet here not really used since AppFlowContext covers the whole application's contexts
        FlowHandler ceFlowHandler = ceFlow.createHandler();
        this.root = ceFlowHandler.start(new CEAnimatedFlowContainer());
        this.ceStage = ceStage; //Make the root stage 'global'
        this.ceStage.setMinWidth(CE.MIN_WIDTH); //The same as -fx-max-width for auth-step-pane
        this.ceStage.setMinHeight(CE.MIN_HEIGHT + App.TOOLBAR_HEIGHT); //do not hide logo side of auth-step-pane when resizing
        this.ceStage.setTitle(App.Strings.WINDOW_TITLE);
        this.window = new CEWindowDecorator(ceStage, this.root);
        this.window.setPrefSize(CE.MIN_WIDTH, CE.MIN_HEIGHT); //When user wants to drags or resize the window, preferred behaviour
//        this.window.setMaximized(true); //Start in maximized mode
        this.window.setCustomMaximize(true); //Putting this, fixed unwanted initial minimized state of the app
        this.ceScene = new Scene(this.window);
        this.ceStage.setScene(this.ceScene);
        this.ceFlowContext.register(CE.SCENE, this.ceScene);
        //new JFXResponsiveHandler(this.ceStage, JFXResponsiveHandler.PSEUDO_CLASS_LARGE);
        loadMetadata();
        this.ceStage.show();
        CSSFX.addConverter( uri -> //CSSFX is still a buggy library, when navigating through flow views, ClassNotFound is raised.
                Paths.get(System.getProperty("user.dir") + "/src/main/resources" + Meta.Stylesheets.CE_STYLESHEET)
        ).start();
        org.scenicview.ScenicView.show(this.ceScene);
    }

    /**
     * Loads the Material Design Roboto Font from the resources folder, as well as ce.css to
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
                Meta.Stylesheets.CE_STYLESHEET
        );
        this.ceStage.getIcons().addAll(Meta.Icons.ICON256X256,
                Meta.Icons.ICON128X128,
                Meta.Icons.ICON64X64,
                Meta.Icons.ICON56X56,
                Meta.Icons.ICON28X28,
                Meta.Icons.ICON16X16
        );
    }
}
