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
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.validation.RequiredFieldValidator;
import java.util.logging.Level;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author iqbal
 */
@ViewController("/fxml/login/password-step.fxml")
public class LoginPasswordStepController {
    
    private boolean startedValidating = false;
    
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
    private BorderPane loginPane;
    
    @PostConstruct private void start() throws Exception {
        this.authStepsActionHandler = (FlowActionHandler)this.loginFlowContext.getRegisteredObject(Steps.Flow.ACTION_HANDLER);
        this.casaElidaFlowHandler = (FlowHandler)this.loginFlowContext.getApplicationContext().getRegisteredObject(App.Flow.FLOW_HANDLER);
        loginPane = (BorderPane) this.loginFlowContext.getRegisteredObject(App.Login.PANE);
        initComponents();
    }
    
    @ActionMethod(Password.Flow.VALIDATE) private void validateUserPassword() throws VetoException, FlowException{
        //Password validation -- actual validation
        this.startedValidating = true;
        boolean isValid = this.txtPassword.validate();
        initUnfocusValidationStyling(isValid);
        if(isValid){
            this.loginPane.setOpacity(0.8d);
            this.passwordStepPane.setDisable(true);
            //Simulate Web-Based login data checking
            CEFunctions.runAfterDelay(()->{
                this.loginFlowContext.getApplicationContext().register(Animations.Flow.NEXT_ANIMATION, Animations.MAIN_START);
                try {
                    this.casaElidaFlowHandler.navigateTo(Main.CLASS);
                    this.loginPane.setOpacity(1d);
                } catch (VetoException | FlowException ex) {
                    Password.LOGGER.log(Level.SEVERE, null, ex);
                }
            }, 500);
        }
    }
    
    private void changeUser() throws FlowException, VetoException{
        this.loginFlowContext.getApplicationContext().register(Animations.Flow.NEXT_ANIMATION, Animations.LOGIN_BACK);
        this.authStepsActionHandler.navigate(LoginUserEmailStepController.class);
    }

    private void initComponents() throws Exception {
        CEFunctions.requestFocus(txtPassword, 400);
        JFXDepthManager.setDepth(this.btnLogin, 1);
        this.btnChangeUser.setOnMouseClicked(e->{
            try {
                changeUser();
            } catch (FlowException | VetoException ex) {
                Password.LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        //Password validation -- styling purposes only
        RequiredFieldValidator passwordRequiredValidator = new RequiredFieldValidator();
        passwordRequiredValidator.setMessage(Password.REQUIRED_PASSWORD_MESSAGE);
        passwordRequiredValidator.setIcon(Password.WARNING_ICON);
        this.txtPassword.setValidators(passwordRequiredValidator);
        initFocusValidationStyling();
    }
    
    private void initFocusValidationStyling(){
        this.txtPassword.focusedProperty().addListener((ChangeListener<Boolean>) (ObservableValue<? extends Boolean> obs, Boolean wasFocused, Boolean isFocusedNow) -> {
            if(this.startedValidating){
                boolean isValid = false;
                if(!isFocusedNow) isValid = this.txtPassword.validate();
                if(!isValid && !wasFocused)  this.txtPassword.setStyle("-fx-prompt-text-fill: ce-white;-jfx-focus-color: ce-white;-jfx-unfocus-color: ce-white;");
                else {
                    this.txtPassword.setStyle("-fx-prompt-text-fill: ce-yellow;-jfx-focus-color: ce-yellow-hover;-jfx-unfocus-color: ce-yellow;");
                    this.txtPassword.lookup(".input-line").setStyle("-fx-background-color:ce-yellow;");
                }
                if(!isValid && !isFocusedNow) this.txtPassword.lookup(".input-line").setStyle("-fx-background-color:ce-white;");
            }
        });
    }
    
    private void initUnfocusValidationStyling(boolean isValid){
        if(!isValid && this.txtPassword.isFocused()) this.txtPassword.setStyle("-fx-prompt-text-fill: ce-white;-jfx-focus-color: ce-white;-jfx-unfocus-color: ce-white;");
        else {
            this.txtPassword.setStyle("-fx-prompt-text-fill: ce-yellow;-jfx-focus-color: ce-yellow-hover;-jfx-unfocus-color: ce-yellow;");
            this.txtPassword.lookup(".input-line").setStyle("-fx-background-color:ce-yellow;");
        }
        if(!isValid && !this.txtPassword.isFocused()) this.txtPassword.lookup(".input-line").setStyle("-fx-background-color:ce-white;");
    }
}
