package com.casaelida.desktop.controllers.login;

import de.jensd.fx.glyphs.icons525.Icons525View;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.core.concurrent.DataFxTask;
import java.awt.Desktop;
import java.io.IOException;
import java.util.logging.Level;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javax.annotation.PostConstruct;
import com.casaelida.desktop.utils.CEAnimatedFlowContainer;
import com.casaelida.desktop.utils.CEBundledFlow;
import com.casaelida.desktop.utils.CEConstants.CasaElida;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
import com.casaelida.desktop.utils.CEConstants.Meta;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Login;
import com.casaelida.desktop.utils.CEFunctions;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author iqbal
 */

@ViewController(value="/fxml/login/login.fxml")
public class LoginController {
    //DataFX Framework
    @FXMLViewFlowContext private ViewFlowContext appFlowContext;//new context instance for the login flow (includes useremail step, and password)
    @ActionHandler private FlowActionHandler appActionHandler;//Only LoginController must catch the ActionHandler from AppController since it is the first view it is shown from its (AppController's) 'View Flow'
    private FlowHandler loginFlowHandler;//new flow handler instance for the login flow, allowing the steps navigate between them. It is a class field since the handler should be used on different methods inside this class
    //Current View Components
    //@ViewNode("my_id") uses the 'id' attribute in the fxml document, if there is no 'id' then it takes the 'fx:id' value
    @ViewNode(Login.PANE_WRAPPER) private StackPane paneWrapper;
    @ViewNode(Login.PANE) private BorderPane loginPane;
    @ViewNode(Login.TITLE_PANE) private HBox loginTitlePane;
    @ViewNode(Login.LOGO_PANE) private HBox loginLogoPane;
    @ViewNode(Login.LOGO_PANE_CASA) private Text loginLogoPaneCasa;
    @ViewNode(Login.LOGO_PANE_ELIDA) private Text loginLogoPaneElida;
    @ViewNode(Login.APP_LINKS_PANE) private HBox loginAppLinksPane;
    @ViewNode(Login.ANDROID_LINK_ICON) private MaterialDesignIconView androidLinkIcon;
    @ViewNode(Login.WEB_LINK_ICON) private Icons525View webLinkIcon;
    @ViewNode(Login.DESKTOP_LINK_ICON) private MaterialIconView desktopLinkIcon;
    @ViewNode(Login.AUTH_PANE) private StackPane authPane;
    private Label lblToolbar;
    
    @PostConstruct public void start() throws FlowException{
        this.lblToolbar = (Label) this.appFlowContext.getRegisteredObject(App.LBL_TOOLBAR);
        this.appFlowContext.getApplicationContext().register(App.Animations.Flow.NEXT_ANIMATION, App.Animations.LOGIN_NEXT);//loginFlowHandler.start() needs an initial animation
        
        CEBundledFlow authFlow = new CEBundledFlow(Login.Steps.UserEmail.CLASS, Login.Steps.UserEmail.Strings.BUNDLE);
        ViewFlowContext loginFlowContext = new ViewFlowContext();
        this.loginFlowHandler = authFlow.createHandler(loginFlowContext);
        
        loginFlowContext.register(App.Flow.ACTION_HANDLER, this.appActionHandler);
        loginFlowContext.register(Login.AUTH_PANE, this.authPane);
        loginFlowContext.register(Login.Flow.HANDLER, this.loginFlowHandler);
        loginFlowContext.register(Login.PANE, this.loginPane);
        
        StackPane rootAuthPane = this.loginFlowHandler.start(new CEAnimatedFlowContainer());
        this.authPane.getChildren().setAll(rootAuthPane);
        initComponents();
    }
    
    private void initComponents(){
        //Decorations & Animations
        JFXDepthManager.setDepth(this.paneWrapper, 5);
        this.lblToolbar.setText(Login.Strings.TITLE);
        //Fix the white left border that appears at the moment of the swipe animation
        Platform.runLater(()->this.authPane.setClip(new Rectangle(this.authPane.getBoundsInParent().getWidth(), this.authPane.getBoundsInParent().getWidth())));
        //Open the Casa Elida Web App
        this.webLinkIcon.setOnMouseClicked(e -> CasaElida.POOL.submit(new DataFxTask<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Desktop.getDesktop().browse(Meta.Links.WEBAPP_URI);
                } catch (IOException ex) {
                    Login.LOGGER.log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }));
        Tooltip.install(this.androidLinkIcon, CEFunctions.createTooltip(Login.Strings.ANDROID_LINK_ICON_TOOLTIP));
        Tooltip.install(this.webLinkIcon, CEFunctions.createTooltip(Login.Strings.WEB_LINK_ICON_TOOLTIP));
        Tooltip.install(this.desktopLinkIcon, CEFunctions.createTooltip(Login.Strings.DESKTOP_LINK_ICON_TOOLTIP));
    }
}
