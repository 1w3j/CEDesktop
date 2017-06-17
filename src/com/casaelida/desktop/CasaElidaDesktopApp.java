package com.casaelida.desktop;

import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.context.ApplicationContext;
import io.datafx.controller.context.FXMLApplicationContext;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.casaelida.desktop.utils.CEAnimatedFlowContainer;
import com.casaelida.desktop.utils.CEConstants.App;
import com.casaelida.desktop.utils.CEConstants.Meta;
import com.casaelida.desktop.utils.CEConstants.App.Animations;
import com.casaelida.desktop.utils.CEConstants.App.Login;
import com.jfoenix.responsive.JFXResponsiveHandler;

/**
 * @version 0.0.1
 * TODO: implement a omni-root container for all views (or controllers) preferably with an action bar on the top
 * 
 * @author iqbal
 */
public class CasaElidaDesktopApp extends Application{
    //Always register context objects BEFORE calling the flowHandler.start() method
    @FXMLApplicationContext private ApplicationContext casaElidaFlowContext;
    
    private Scene scene;
    private Stage casaElidaStage;
    private JFXDecorator window;
    private StackPane root;

    @Override public void start(Stage casaElidaStage) throws Exception {
        Flow casaElidaFlow = new Flow(Login.CLASS);
        FlowHandler casaElidaFlowHandler = casaElidaFlow.createHandler();
        this.casaElidaFlowContext = ApplicationContext.getInstance();
        this.casaElidaFlowContext.register(App.Flow.FLOW, casaElidaFlow);
        this.casaElidaFlowContext.register(App.Flow.FLOW_HANDLER, casaElidaFlowHandler);
        this.casaElidaFlowContext.register(App.STAGE, casaElidaStage);
        this.casaElidaFlowContext.register(Animations.Flow.NEXT_ANIMATION, Animations.LOGIN_NEXT);
        this.root = casaElidaFlowHandler.start(new CEAnimatedFlowContainer());
        
        this.casaElidaStage = casaElidaStage;
        this.casaElidaStage.setMinWidth(600d);
        this.casaElidaStage.setMinHeight(700d);
        this.casaElidaStage.setTitle("Casa Elida - Versión de Escritorio");
        this.window = new JFXDecorator(casaElidaStage, this.root);
        this.window.setCustomMaximize(true);
        this.scene = new Scene(this.window);
        loadMetadata();

        this.casaElidaStage.setScene(this.scene);
        this.casaElidaStage.setMaximized(true);
        this.casaElidaStage.show();
    }
    
    public static void main(String[] args) {
        CasaElidaDesktopApp.launch(args);
    }

    private void loadMetadata() {
        this.window.getStylesheets().addAll(
                Meta.Stylesheets.VARIABLES_STYLESHEET, 
                Meta.Stylesheets.LOGIN_STYLESHEET,
                Meta.Stylesheets.MAIN_STYLESHEET
        );
        Font.loadFont(Meta.Fonts.ROBOTO_BLACK_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_BOLD_ITALIC_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_BOLD_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_LIGHT_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_MEDIUM_STREAM, 22);
        Font.loadFont(Meta.Fonts.ROBOTO_REGULAR_STREAM, 22);
        this.casaElidaStage.getIcons().addAll(Meta.Icons.ICON256X256, 
                Meta.Icons.ICON128X128, 
                Meta.Icons.ICON64X64, 
                Meta.Icons.ICON56X56, 
                Meta.Icons.ICON28X28, 
                Meta.Icons.ICON16X16
        );
    }
}
