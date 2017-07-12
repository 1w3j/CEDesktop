package com.casaelida.desktop.controllers.main;

import com.casaelida.desktop.utils.CEController;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Login;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;

import javax.annotation.PostConstruct;

/**
 * @author iqbal
 * @date 7/11/17
 */
@ViewController(value = "/fxml/main/sidemenu.fxml")
public class MainSideMenuController extends CEController{
    @FXMLViewFlowContext private ViewFlowContext appFlowContext;
    private FlowActionHandler appActionHandler;//taken from LoginController (which is injected from the 'App' Flow, see LoginController.java or docs)

    @ViewNode (Main.SideMenu.ITEM_LOG_OUT) @ActionTrigger (Main.SideMenu.Flow.LOG_OUT) private JFXButton itemLogOut;
    private JFXDrawer appDrawer;

    @PostConstruct private void start(){
        this.appActionHandler = (FlowActionHandler) this.appFlowContext.getApplicationContext().getRegisteredObject(App.Flow.ACTION_HANDLER);
        this.appDrawer = (JFXDrawer) this.appFlowContext.getApplicationContext().getRegisteredObject(App.DRAWER);

        initComponents();
    }

    @Override protected void initComponents () {

    }

    @ActionMethod (Main.SideMenu.Flow.LOG_OUT) private void logout() throws VetoException, FlowException {
        this.appFlowContext.getApplicationContext().register(App.Animations.Flow.NEXT_ANIMATION, App.Animations.LOG_OUT);
        this.appDrawer.close();
        this.appActionHandler.navigate(Login.CLASS);
    }
}
