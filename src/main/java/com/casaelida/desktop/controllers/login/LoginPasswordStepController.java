package com.casaelida.desktop.controllers.login;

import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Animations;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Login;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Login.Steps.Password;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Main;
import com.casaelida.desktop.utils.CEController;
import com.casaelida.desktop.utils.CEFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.validation.RequiredFieldValidator;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.annotation.PostConstruct;
import java.util.logging.Level;

/**
 * @author iqbal
 */
@ViewController ("/fxml/login/password-step.fxml")
public class LoginPasswordStepController extends CEController {
    //Current View Features
    //boolean that is true every time user clicks the 'Login Button', used for styling purposes only
    private boolean startedValidating = false;
    //Taken from LoginController through its context
    private BorderPane loginPane;
    //DataFX Framework
    //instance is taken from LoginController
    @FXMLViewFlowContext private ViewFlowContext loginFlowContext;
    //taken from LoginController through loginFlowContext
    private FlowActionHandler authStepsActionHandler;
    //taken from CasaElidaDesktopApp throught Application Context
    private FlowActionHandler appActionHandler;
    //Current View Components
    @ViewNode (Password.PANE) private GridPane passwordStepPane;
    @ViewNode (Password.PANE_HEADER) private VBox passwordStepPaneHeader;
    @ViewNode (Password.LBL_USEREMAIL) private Label lblUserEmail;
    @ViewNode (Password.BTN_CHANGE_USER) private StackPane btnChangeUser;
    @ViewNode (Password.BTN_LOGIN) @ActionTrigger (Password.Flow.VALIDATE) private JFXButton btnLogin;
    @ViewNode (Password.TXT_PASSWORD) private JFXPasswordField txtPassword;
    @ViewNode (Password.LBL_USERNAME) private Label lblUserName;

    @PostConstruct private void start () {
        //instance was created in LoginUserEmailStepController since it is the first View that appears in the Login's Flow (better called Auth's Flow) and it is used to navigate to MainController
        this.authStepsActionHandler = (FlowActionHandler) this.loginFlowContext.getRegisteredObject(Login.Flow.ACTION_HANDLER);
        //instance was created in AppController and it is used to navigate to MainController
        this.appActionHandler = (FlowActionHandler) this.loginFlowContext.getApplicationContext().getRegisteredObject(App.Flow.ACTION_HANDLER);
        this.loginPane = (BorderPane) this.loginFlowContext.getRegisteredObject(App.Login.PANE);

        initComponents();
    }

    @ActionMethod (Password.Flow.VALIDATE)
    private void validateUserPassword () {
        //Password validation
        this.startedValidating = true;
        boolean isValid = this.txtPassword.validate();
        CEFunctions.unfocusedValidationStyling(this.txtPassword, isValid);
        if (isValid) {
            this.loginPane.setOpacity(0.8d);
            this.passwordStepPane.setDisable(true);
            //Simulate Web-Based login data verification
            CEFunctions.runAfterDelay(() -> {
                this.loginFlowContext.getApplicationContext().register(Animations.Flow.NEXT_ANIMATION, Animations.MAIN_START);
                try {
                    this.appActionHandler.navigate(Main.CLASS);
                    this.loginPane.setOpacity(1d);
                } catch (VetoException | FlowException ex) {
                    Password.LOGGER.log(Level.SEVERE, null, ex);
                }
            }, 500);
        }
    }

    @Override protected void initComponents () {
        //Decorations & Animations
        CEFunctions.requestFocus(this.txtPassword, 325);
        JFXDepthManager.setDepth(this.btnLogin, 1);
        this.btnChangeUser.setOnMouseClicked(e -> {
            try {
                changeUser();
            } catch (FlowException | VetoException ex) {
                Password.LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        //Password validators
        RequiredFieldValidator passwordRequiredValidator = new RequiredFieldValidator();
        passwordRequiredValidator.setMessage(Password.Strings.ERROR_REQUIRED);
        passwordRequiredValidator.setIcon(Password.WARNING_ICON);
        this.txtPassword.setValidators(passwordRequiredValidator);
        CEFunctions.initFocusValidationStyling(this.txtPassword, this.startedValidating);
        //Tooltips
        Tooltip.install(this.btnChangeUser, CEFunctions.createTooltip(Password.Strings.TOOLTIP_CHANGE_USER));
        Tooltip.install(this.btnLogin, CEFunctions.createTooltip(Password.Strings.BTN_LOGIN));
    }

    //User clicks the arrow down button
    private void changeUser () throws FlowException, VetoException {
        this.loginFlowContext.getApplicationContext().register(Animations.Flow.NEXT_ANIMATION, Animations.LOGIN_BACK);
        this.authStepsActionHandler.navigate(Login.Steps.UserEmail.CLASS);
    }

}
