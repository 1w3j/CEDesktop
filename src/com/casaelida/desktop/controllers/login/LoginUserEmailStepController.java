package com.casaelida.desktop.controllers.login;

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
import com.casaelida.desktop.utils.CEConstants.App.Animations;
import com.casaelida.desktop.utils.CEConstants.App.Login;
import com.casaelida.desktop.utils.CEConstants.App.Login.Steps;
import com.casaelida.desktop.utils.CEConstants.App.Login.Steps.UserEmail;
import com.casaelida.desktop.utils.ValidEmailValidator;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import java.util.logging.Level;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author iqbal
 */
@ViewController(value="/fxml/login/useremail-step.fxml")
public class LoginUserEmailStepController{
    
    private final String REQUIRED_EMAIL_MESSAGE = "Ingrese su Correo Electrónico";
    private final String INVALID_EMAIL_MESSAGE = "Ingrese un Correo Electrónico Válido";
    private final MaterialIconView warningIcon = new MaterialIconView(MaterialIcon.WARNING, "22.5");
    private boolean startedValidating = false;
    
    @FXMLViewFlowContext private ViewFlowContext loginFlowContext;//taken from LoginController
    @ActionHandler private FlowActionHandler authStepsActionHandler;//taken from 
    
    @ViewNode(UserEmail.PANE) private GridPane userEmailStepPane;
    @ViewNode(UserEmail.PANE_HEADER) private VBox userEmailStepPaneHeader;
    @ViewNode(UserEmail.TXT_USEREMAIL) private JFXTextField txtUserEmail;
    @ViewNode(UserEmail.BTN_NEXT) @ActionTrigger(UserEmail.Flow.VALIDATE) private JFXButton btnNext;
    private BorderPane loginPane;
    
    
    @PostConstruct private void start() throws Exception{
        this.loginFlowContext.register(Steps.Flow.ACTION_HANDLER, this.authStepsActionHandler);
        loginPane = (BorderPane)this.loginFlowContext.getRegisteredObject(Login.PANE);
        initComponents();
    }
    
    @ActionMethod(UserEmail.Flow.VALIDATE) private void validateUserEmail(){
        this.startedValidating = true;
        boolean isValid = this.txtUserEmail.validate();
        if(!isValid && this.txtUserEmail.isFocused()) this.txtUserEmail.setStyle("-fx-prompt-text-fill: ce-white;-jfx-focus-color: ce-white;-jfx-unfocus-color: ce-white;");
        else {
            this.txtUserEmail.setStyle("-fx-prompt-text-fill: ce-yellow;-jfx-focus-color: ce-yellow-hover;-jfx-unfocus-color: ce-yellow;");
            this.txtUserEmail.lookup(".input-line").setStyle("-fx-background-color:ce-yellow;");
        }
        if(!isValid && !this.txtUserEmail.isFocused()) this.txtUserEmail.lookup(".input-line").setStyle("-fx-background-color:ce-white;");
        if(isValid){
            this.loginPane.setOpacity(0.8d);
            this.userEmailStepPane.setDisable(true);
            CEFunctions.runAfterDelay(()->{
                this.loginFlowContext.getApplicationContext().register(Animations.Flow.NEXT_ANIMATION, Animations.LOGIN_NEXT);
                try {
                    this.authStepsActionHandler.navigate(LoginPasswordStepController.class);
                    this.loginPane.setOpacity(1d);
                } catch (VetoException | FlowException ex) {
                    UserEmail.LOGGER.log(Level.SEVERE, null, ex);
                }
            }, 5000);
        }
    }

    private void initComponents() throws Exception {
        CEFunctions.requestFocus(this.txtUserEmail, 550);
        JFXDepthManager.setDepth(this.btnNext, 1);
        //Email validation
        RequiredFieldValidator emailRequiredValidator = new RequiredFieldValidator();
        emailRequiredValidator.setMessage(this.REQUIRED_EMAIL_MESSAGE);
        emailRequiredValidator.setIcon(this.warningIcon);
        ValidEmailValidator emailValidValidator = new ValidEmailValidator();
        emailValidValidator.setMessage(this.INVALID_EMAIL_MESSAGE);
        emailValidValidator.setIcon(this.warningIcon);
        this.txtUserEmail.setValidators(emailRequiredValidator, emailValidValidator);
        
        this.txtUserEmail.focusedProperty().addListener((ChangeListener<Boolean>) (ObservableValue<? extends Boolean> obs, Boolean wasFocused, Boolean isFocusedNow) -> {
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
}
