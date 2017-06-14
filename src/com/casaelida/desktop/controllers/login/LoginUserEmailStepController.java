package com.casaelida.desktop.controllers.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import com.casaelida.desktop.utils.CEConstants.App.Login.Steps;
import com.casaelida.desktop.utils.CEConstants.App.Login.Steps.UserEmail;
import com.jfoenix.effects.JFXDepthManager;

/**
 *
 * @author iqbal
 */
@ViewController(value="/fxml/login/useremail-step.fxml")
public class LoginUserEmailStepController{
    
    @FXMLViewFlowContext private ViewFlowContext loginFlowContext;//taken from LoginController
    @ActionHandler private FlowActionHandler authStepsActionHandler;//taken from 
    
    @ViewNode(UserEmail.PANE) private GridPane userEmailStepPane;
    @ViewNode(UserEmail.PANE_HEADER) private VBox userEmailStepPaneHeader;
    @ViewNode(UserEmail.TXT_USEREMAIL) private JFXTextField txtUserEmail;
    @ViewNode(UserEmail.BTN_NEXT) @ActionTrigger(UserEmail.Flow.VALIDATE) private JFXButton btnNext;
    
    @PostConstruct private void start() throws Exception{
        this.loginFlowContext.register(Steps.Flow.ACTION_HANDLER, this.authStepsActionHandler);
        
        initComponents();
    }
    
    @ActionMethod(UserEmail.Flow.VALIDATE) private void validateUserEmail() throws FlowException, VetoException{
        System.out.println(txtUserEmail.getUserData());
        if((boolean)txtUserEmail.getUserData()){
            this.loginFlowContext.getApplicationContext().register(Animations.Flow.NEXT_ANIMATION, Animations.LOGIN_NEXT);
            this.authStepsActionHandler.navigate(LoginPasswordStepController.class);
        }
    }

    private void initComponents() throws Exception {
        CEFunctions.requestFocus(this.txtUserEmail, 550);
        JFXDepthManager.setDepth(this.btnNext, 1);
        CEFunctions.createValidateOnFocusHandler(txtUserEmail, "Ingrese un correo electrónico");
    }
}
