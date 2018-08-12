package com.ce.desktop.controllers.login;

import com.ce.desktop.utils.CEController;
import com.ce.desktop.utils.CEFunctions;
import com.ce.desktop.utils.CEConstants;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.annotation.PostConstruct;
import java.util.logging.Level;

/**
 * @author 1w3j
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
    //taken from CEDesktopApp throught Application Context
    private FlowActionHandler appActionHandler;
    //Current View Components
    @ViewNode (CEConstants.CE.App.Login.Steps.Password.PANE) private GridPane passwordStepPane;
    @ViewNode (CEConstants.CE.App.Login.Steps.Password.PANE_HEADER) private VBox passwordStepPaneHeader;
    @ViewNode (CEConstants.CE.App.Login.Steps.Password.LBL_USEREMAIL) private Label lblUserEmail;
    @ViewNode (CEConstants.CE.App.Login.Steps.Password.BTN_CHANGE_USER) private StackPane btnChangeUser;
    @ViewNode (CEConstants.CE.App.Login.Steps.Password.BTN_LOGIN) @ActionTrigger (CEConstants.CE.App.Login.Steps.Password.Flow.VALIDATE) private JFXButton btnLogin;
    @ViewNode (CEConstants.CE.App.Login.Steps.Password.TXT_PASSWORD) private JFXPasswordField txtPassword;
    @ViewNode (CEConstants.CE.App.Login.Steps.Password.LBL_USERNAME) private Label lblUserName;

    @PostConstruct private void start () {
        //instance was created in LoginUserEmailStepController since it is the first View that appears in the Login's Flow (better called Auth's Flow) and it is used to navigate to MainController
        this.authStepsActionHandler = (FlowActionHandler) this.loginFlowContext.getRegisteredObject(CEConstants.CE.App.Login.Flow.ACTION_HANDLER);
        //instance was created in AppController and it is used to navigate to MainController
        this.appActionHandler = (FlowActionHandler) this.loginFlowContext.getApplicationContext().getRegisteredObject(CEConstants.CE.App.Flow.ACTION_HANDLER);
        this.loginPane = (BorderPane) this.loginFlowContext.getRegisteredObject(CEConstants.CE.App.Login.PANE);

        initComponents();
    }

    @ActionMethod (CEConstants.CE.App.Login.Steps.Password.Flow.VALIDATE)
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
                this.loginFlowContext.getApplicationContext().register(CEConstants.CE.App.Animations.Flow.NEXT_ANIMATION, CEConstants.CE.App.Animations.MAIN_START);
                try {
                    this.appActionHandler.navigate(CEConstants.CE.App.Main.CLASS);
                    this.loginPane.setOpacity(1d);
                } catch (VetoException | FlowException ex) {
                    CEConstants.CE.App.Login.Steps.Password.LOGGER.log(Level.SEVERE, null, ex);
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
                CEConstants.CE.App.Login.Steps.Password.LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        //Password validators
        RequiredFieldValidator passwordRequiredValidator = new RequiredFieldValidator();
        passwordRequiredValidator.setMessage(CEConstants.CE.App.Login.Steps.Password.Strings.ERROR_REQUIRED);
        passwordRequiredValidator.setIcon(CEConstants.CE.App.Login.Steps.Password.WARNING_ICON);
        this.txtPassword.setValidators(passwordRequiredValidator);
        CEFunctions.initFocusValidationStyling(this.txtPassword, this.startedValidating);
        //Tooltips
        Tooltip.install(this.btnChangeUser, CEFunctions.createTooltip(CEConstants.CE.App.Login.Steps.Password.Strings.TOOLTIP_CHANGE_USER));
        Tooltip.install(this.btnLogin, CEFunctions.createTooltip(CEConstants.CE.App.Login.Steps.Password.Strings.BTN_LOGIN));
    }

    //User clicks the arrow down button
    private void changeUser () throws FlowException, VetoException {
        this.loginFlowContext.getApplicationContext().register(CEConstants.CE.App.Animations.Flow.NEXT_ANIMATION, CEConstants.CE.App.Animations.LOGIN_BACK);
        this.authStepsActionHandler.navigate(CEConstants.CE.App.Login.Steps.UserEmail.CLASS);
    }

}
