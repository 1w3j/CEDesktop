package com.casaelida.desktop.controllers.login;

import com.casaelida.desktop.utils.CEController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.validation.RequiredFieldValidator;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javax.annotation.PostConstruct;
import com.casaelida.desktop.utils.CEFunctions;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Animations;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Login;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Login.Steps.UserEmail;
import com.casaelida.desktop.utils.CEValidEmailValidator;
import java.util.logging.Level;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

/**
 *
 * @author iqbal
 */
@ViewController(value="/fxml/login/useremail-step.fxml")//defines a view
public class LoginUserEmailStepController extends CEController{
    //Current View Features
    //boolean that is true every time user clicks the 'Login Button', used for styling purposes only
    private boolean startedValidating = false;
    //Taken from LoginController through its context
    private BorderPane loginPane;
    //DataFX Framework
    //instance is taken from LoginController
    @FXMLViewFlowContext private ViewFlowContext loginFlowContext;
    //taken from the injection of this view, created to be used anywhere 'in the steps'
    @ActionHandler private FlowActionHandler authStepsActionHandler;
    //Current View Components
    @ViewNode(UserEmail.PANE) private GridPane userEmailStepPane;
    @ViewNode(UserEmail.PANE_HEADER) private VBox userEmailStepPaneHeader;
    @ViewNode(UserEmail.TXT_USEREMAIL) private JFXTextField txtUserEmail;
    @ViewNode(UserEmail.BTN_NEXT) @ActionTrigger(UserEmail.Flow.VALIDATE) private JFXButton btnNext;

    @PostConstruct private void start() {
        this.loginPane = (BorderPane) this.loginFlowContext.getRegisteredObject(Login.PANE);

        this.loginFlowContext.register(Login.Flow.ACTION_HANDLER, this.authStepsActionHandler);

        initComponents();
    }
    
    @ActionMethod(UserEmail.Flow.VALIDATE) private void validateUserEmail(){
        //Email validation
        this.startedValidating = true;
        boolean isValid = this.txtUserEmail.validate();
        unfocusedValidationStyling(isValid);
        if(isValid){
            this.loginPane.setOpacity(0.8d);
            this.userEmailStepPane.setDisable(true);
            //Simulate Web-Based login data verification
            CEFunctions.runAfterDelay(()->{
                this.loginFlowContext.getApplicationContext().register(Animations.Flow.NEXT_ANIMATION, Animations.LOGIN_NEXT);
                try {
                    this.authStepsActionHandler.navigate(LoginPasswordStepController.class);
                    this.loginPane.setOpacity(1d);
                } catch (VetoException | FlowException ex) {
                    UserEmail.LOGGER.log(Level.SEVERE, null, ex);
                }
            }, 500);
        }
    }

    @Override protected void initComponents () {
        //Decorations & Animations
        CEFunctions.requestFocus(this.txtUserEmail, 450);
        JFXDepthManager.setDepth(this.btnNext, 1);
        //Email validators
        RequiredFieldValidator emailRequiredValidator = new RequiredFieldValidator();
        emailRequiredValidator.setMessage(UserEmail.Strings.REQUIRED_EMAIL_MESSAGE);
        emailRequiredValidator.setIcon(UserEmail.WARNING_ICON);
        CEValidEmailValidator emailValidValidator = new CEValidEmailValidator();
        emailValidValidator.setMessage(UserEmail.Strings.INVALID_EMAIL_MESSAGE);
        emailValidValidator.setIcon(UserEmail.WARNING_ICON);
        this.txtUserEmail.setValidators(emailRequiredValidator, emailValidValidator);
        initFocusValidationStyling();
        //Tooltips
        Tooltip userEmailTooltip = CEFunctions.createTooltip();
        userEmailTooltip.textProperty().bindBidirectional(this.txtUserEmail.textProperty(), new StringConverter<String>() {
            @Override public String toString(String objectProvided) {
                return objectProvided.isEmpty() ? UserEmail.Strings.USEREMAIL_TOOLTIP_EMPTY : UserEmail.Strings.USEREMAIL_TOOLTIP_NONEMPTY + objectProvided;
            }
            @Override public String fromString(String stringProvided) {
                return stringProvided;
            }
        });
        Tooltip.install(this.btnNext, userEmailTooltip);
    }
    //Initialize Listener for any time the user clicks on the text field (or TAB...)
    private void initFocusValidationStyling(){
        this.txtUserEmail.focusedProperty().addListener((ObservableValue<? extends Boolean> obs, Boolean wasFocused, Boolean isFocusedNow) -> {
            if(this.startedValidating){
                boolean isValid = false;
                if(!isFocusedNow) isValid = this.txtUserEmail.validate();
                if(!isValid && !wasFocused)  this.txtUserEmail.setStyle("-fx-prompt-text-fill: ce-white;-jfx-focus-color: ce-white;-jfx-unfocus-color: ce-white;");
                else {
                    this.txtUserEmail.setStyle("-fx-prompt-text-fill: ce-yellow;-jfx-focus-color: ce-yellow-hover;-jfx-unfocus-color: ce-yellow;");
                    this.txtUserEmail.lookup(".input-line").setStyle("-fx-background-color:ce-yellow;");
                }
                if(!isValid && !isFocusedNow) this.txtUserEmail.lookup(".input-line").setStyle("-fx-background-color:ce-white;");
            }
        });
    }
    //Used when the user clicks the 'next button'
    private void unfocusedValidationStyling(boolean isValid){
        if(!isValid && this.txtUserEmail.isFocused()) this.txtUserEmail.setStyle("-fx-prompt-text-fill: ce-white;-jfx-focus-color: ce-white;-jfx-unfocus-color: ce-white;");
        else {
            this.txtUserEmail.setStyle("-fx-prompt-text-fill: ce-yellow;-jfx-focus-color: ce-yellow-hover;-jfx-unfocus-color: ce-yellow;");
            this.txtUserEmail.lookup(".input-line").setStyle("-fx-background-color:ce-yellow;");
        }
        if(!isValid && !this.txtUserEmail.isFocused()) this.txtUserEmail.lookup(".input-line").setStyle("-fx-background-color:ce-white;");
    }
}
