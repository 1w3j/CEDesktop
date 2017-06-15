package com.casaelida.desktop.controllers.login;

import de.jensd.fx.glyphs.icons525.Icons525View;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.Flow;
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
import com.casaelida.desktop.utils.CEConstants.App;
import com.casaelida.desktop.utils.CEConstants.Meta;
import com.casaelida.desktop.utils.CEConstants.App.Login;
import com.casaelida.desktop.utils.CEFunctions;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author iqbal
 */

@ViewController(value="/fxml/login/login-main.fxml", title="Casa Elida - Versión de Escritorio")
public class LoginController {
    
    @FXMLViewFlowContext private ViewFlowContext loginFlowContext;//new context instance for the login flow (includes useremail step, and password)
    private FlowHandler loginFlowHandler;//new flow handler instance for login flow, allowing the steps navigate between them
    //@ViewNode("my_id") uses the 'id' attribute in the fxml document if there are no 'id's then it takes the 'fx:id' value
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
    
    @PostConstruct public void start() throws FlowException{
        Flow authFlow = new Flow(LoginUserEmailStepController.class);
        this.loginFlowContext = new ViewFlowContext();
        this.loginFlowContext.register(Login.AUTH_PANE, this.authPane);
        this.loginFlowHandler = authFlow.createHandler(this.loginFlowContext);
        this.loginFlowContext.register(Login.Flow.FLOW_HANDLER, this.loginFlowHandler);
        this.loginFlowContext.register(Login.PANE, this.loginPane);
        
        StackPane rootAuthPane = this.loginFlowHandler.start(new CEAnimatedFlowContainer());
        this.authPane.getChildren().setAll(rootAuthPane);
        initComponents();
    }
    
    private void initComponents(){
        JFXDepthManager.setDepth(this.paneWrapper, 5);
        //Fix the white left border that appears at the moment of the swipe animation
        this.authPane.setClip(new Rectangle(510, 520));
        //Open the Casa Elida Web App
        this.webLinkIcon.setOnMouseClicked(e -> App.POOL.submit(new DataFxTask<Void>() {
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
        Tooltip.install(this.androidLinkIcon, CEFunctions.createTooltip("Próximamente en Dispositivos Móviles..."));
    }
    
}
