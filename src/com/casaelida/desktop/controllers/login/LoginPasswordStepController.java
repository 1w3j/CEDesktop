package com.casaelida.desktop.controllers.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import java.util.logging.Level;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javax.annotation.PostConstruct;
import com.casaelida.desktop.utils.CEFunctions;
import com.casaelida.desktop.utils.CEConstants.App;
import com.casaelida.desktop.utils.CEConstants.App.Main;
import com.casaelida.desktop.utils.CEConstants.App.Animations;
import com.casaelida.desktop.utils.CEConstants.App.Login.Steps;
import com.casaelida.desktop.utils.CEConstants.App.Login.Steps.Password;

/**
 *
 * @author iqbal
 */
@ViewController("/fxml/login/password-step.fxml")
public class LoginPasswordStepController {
    
    @FXMLViewFlowContext private ViewFlowContext loginFlowContext;//taken from LoginController
    private FlowActionHandler authStepsActionHandler;//taken from LoginController through loginFlowContext
    private FlowHandler casaElidaFlowHandler;//taken from CasaElidaDesktopApp throught Application Context
    
    @ViewNode(Password.PANE) private GridPane passwordStepPane;
    @ViewNode(Password.PANE_HEADER) private VBox passwordStepPaneHeader;
    @ViewNode(Password.LBL_USEREMAIL) private Label lblUserEmail;
    @ViewNode(Password.BTN_CHANGE_USER) private StackPane btnChangeUser;
    @ViewNode(Password.BTN_LOGIN) @ActionTrigger(Password.Flow.VALIDATE) private JFXButton btnLogin;
    @ViewNode(Password.TXT_PASSWORD) private JFXPasswordField txtPassword;
    @ViewNode(Password.LBL_USERNAME) private Label lblUserName;
    
    @PostConstruct private void start() {
        this.authStepsActionHandler = (FlowActionHandler)this.loginFlowContext.getRegisteredObject(Steps.Flow.ACTION_HANDLER);
        this.casaElidaFlowHandler = (FlowHandler)this.loginFlowContext.getApplicationContext().getRegisteredObject(App.Flow.FLOW_HANDLER);
        CEFunctions.requestFocus(txtPassword, 400);
        this.btnChangeUser.setOnMouseClicked(e->{
            try {
                this.loginFlowContext.getApplicationContext().register(Animations.Flow.NEXT_ANIMATION, Animations.LOGIN_BACK);
                this.authStepsActionHandler.navigate(LoginUserEmailStepController.class);
            } catch (VetoException | FlowException ex) {
                Password.LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @ActionMethod(Password.Flow.VALIDATE) private void validateUserPassword() throws VetoException, FlowException{
        this.loginFlowContext.getApplicationContext().register(Animations.Flow.NEXT_ANIMATION, Animations.MAIN_START);
        this.casaElidaFlowHandler.navigateTo(Main.CLASS);
    }
}
