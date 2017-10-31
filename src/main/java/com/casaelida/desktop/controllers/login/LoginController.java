package com.casaelida.desktop.controllers.login;

import com.casaelida.desktop.utils.CEConstants.CasaElida;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Login;
import com.casaelida.desktop.utils.CEConstants.Meta;
import com.casaelida.desktop.utils.CEController;
import com.casaelida.desktop.utils.CEFunctions;
import com.casaelida.desktop.utils.datafx.CEAnimatedFlowContainer;
import com.casaelida.desktop.utils.datafx.CEBundledFlow;
import com.casaelida.desktop.utils.datafx.CEFlowHandler;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.icons525.Icons525View;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.core.concurrent.DataFxTask;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author iqbal
 */

@ViewController (value = "/fxml/login/login.fxml")
public class LoginController extends CEController {
    /**
     * Begin: DataFX Framework instances
     *
     * - The {@link ViewFlowContext} is taken from {@link com.casaelida.desktop.controllers.AppController}
     * - Only {@link LoginController} DO must catch an {@link ActionHandler} from {@link com.casaelida.desktop.controllers.AppController}
     *    since it is the first visible view from {@link com.casaelida.desktop.controllers.AppController}'s {@link io.datafx.controller.flow.FlowView}
     */
    @FXMLViewFlowContext private ViewFlowContext appFlowContext;
    @ActionHandler private FlowActionHandler appActionHandler;
    //new flow handler instance for the login flow, allowing the steps navigate between them. It is a class field since the handler should be used on different methods inside this class
    private CEFlowHandler loginFlowHandler;
    private CEFlowHandler sidemenuFlowHandler;
    //Current View Components
    //@ViewNode("my_id") uses the 'id' attribute in the fxml document, if there is no 'id' then it takes the 'fx:id' value
    @ViewNode (Login.PANE_WRAPPER) private StackPane paneWrapper;
    @ViewNode (Login.PANE) private BorderPane loginPane;
    @ViewNode (Login.TITLE_PANE) private HBox loginTitlePane;
    @ViewNode (Login.LOGO_PANE) private HBox loginLogoPane;
    @ViewNode (Login.LOGO_TEXT_CASA) private Text loginLogoPaneCasa;
    @ViewNode (Login.LOGO_TEXT_ELIDA) private Text loginLogoPaneElida;
    @ViewNode (Login.APP_LINKS_PANE) private HBox loginAppLinksPane;
    @ViewNode (Login.ANDROID_LINK_ICON) private MaterialDesignIconView androidLinkIcon;
    @ViewNode (Login.WEB_LINK_ICON) private Icons525View webLinkIcon;
    @ViewNode (Login.DESKTOP_LINK_ICON) private MaterialIconView desktopLinkIcon;
    @ViewNode (Login.AUTH_PANE) private StackPane authPane;
    private Label lblToolbar;
    private JFXDrawer appDrawer;
    private StackPane btnToolbarSidemenuBurger;
    private StackPane btnToolbarOptionsBurger;

    @PostConstruct public void start () throws FlowException {
        this.appDrawer = (JFXDrawer) this.appFlowContext.getApplicationContext().getRegisteredObject(App.DRAWER);
        this.btnToolbarOptionsBurger = (StackPane) this.appFlowContext.getApplicationContext().getRegisteredObject(App.BTN_TOOLBAR_OPTIONS_BURGER);
        this.btnToolbarSidemenuBurger = (StackPane) this.appFlowContext.getApplicationContext().getRegisteredObject(App.BTN_TOOLBAR_SIDEMENU_BURGER);
        this.lblToolbar = (Label) this.appFlowContext.getRegisteredObject(App.LBL_TOOLBAR);

        CEBundledFlow authFlow = new CEBundledFlow(Login.Steps.UserEmail.CLASS, Login.Steps.UserEmail.Strings.BUNDLE);
        //createHandler() creates a FlowContext, its instantiation is not needed
        this.loginFlowHandler = authFlow.createHandler();

        this.loginFlowHandler.registerInApplicationContext(App.Flow.ACTION_HANDLER, this.appActionHandler);
        this.loginFlowHandler.registerInFlowContext(Login.AUTH_PANE, this.authPane);
        this.loginFlowHandler.registerInFlowContext(Login.Flow.HANDLER, this.loginFlowHandler);
        this.loginFlowHandler.registerInFlowContext(Login.PANE, this.loginPane);

        StackPane rootAuthPane = this.loginFlowHandler.start(new CEAnimatedFlowContainer());
        this.authPane.getChildren().setAll(rootAuthPane);

        CEBundledFlow sideMenuFlow = new CEBundledFlow(Login.SideMenu.CLASS, Login.SideMenu.Strings.BUNDLE);
        this.sidemenuFlowHandler = sideMenuFlow.createHandler();
        StackPane rootSideMenu = this.sidemenuFlowHandler.start();
        this.appDrawer.setSidePane(rootSideMenu);

        initComponents();
    }

    @Override protected void initComponents () {
        //Decorations & Animations
        this.btnToolbarOptionsBurger.getParent().setVisible(false);
        CEFunctions.initDrawer(this.appDrawer, this.btnToolbarSidemenuBurger);
        JFXDepthManager.setDepth(this.paneWrapper, 5);
        this.lblToolbar.setText(Login.Strings.TITLE);
        //Fix the white left border that appears at the moment of the swipe animation
        Platform.runLater(() -> this.authPane.setClip(new Rectangle(this.authPane.getBoundsInParent().getWidth(), this.authPane.getBoundsInParent().getWidth())));
        //Open the Casa Elida Web App on the respective icon
        this.webLinkIcon.setOnMouseClicked(e -> CasaElida.POOL.submit(new DataFxTask<Void>() {
            @Override
            protected Void call () throws Exception {
                try {
                    Desktop.getDesktop().browse(Meta.Links.WEBAPP_URI);
                } catch (IOException ex) {
                    Login.LOGGER.log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }));
        Tooltip.install(this.androidLinkIcon, CEFunctions.createTooltip(Login.Strings.ICON_TOOLTIP_ANDROID_LINK));
        Tooltip.install(this.webLinkIcon, CEFunctions.createTooltip(Login.Strings.ICON_TOOLTIP_WEB_LINK));
        Tooltip.install(this.desktopLinkIcon, CEFunctions.createTooltip(Login.Strings.ICON_TOOLTIP_DESKTOP_LINK));
    }
}
