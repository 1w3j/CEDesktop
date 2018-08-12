package com.ce.desktop.controllers.main;

import com.ce.desktop.utils.CEController;
import com.ce.desktop.utils.datafx.CEBundledFlow;
import com.ce.desktop.utils.datafx.CEFlowHandler;
import com.ce.desktop.utils.CEConstants;
import com.jfoenix.controls.JFXDrawer;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import javax.annotation.PostConstruct;

/**
 * @author 1w3j
 */
@ViewController (value = "/fxml/main/main.fxml")
public class MainController extends CEController {
    @FXMLViewFlowContext private ViewFlowContext appFlowContext;
    private CEFlowHandler sidemenuFlowHandler;

    private JFXDrawer appDrawer;
    private Label lblToolbar;
    private StackPane btnToolbarOptionsBurger;

    @PostConstruct private void start () throws FlowException {
        this.appDrawer = (JFXDrawer) this.appFlowContext.getApplicationContext().getRegisteredObject(CEConstants.CE.App.DRAWER);
        this.btnToolbarOptionsBurger = (StackPane) this.appFlowContext.getApplicationContext().getRegisteredObject(CEConstants.CE.App.BTN_TOOLBAR_OPTIONS_BURGER);
        this.lblToolbar = (Label) this.appFlowContext.getRegisteredObject(CEConstants.CE.App.LBL_TOOLBAR);

        CEBundledFlow sideMenuFlow = new CEBundledFlow(CEConstants.CE.App.Main.SideMenu.CLASS, CEConstants.CE.App.Main.SideMenu.Strings.BUNDLE);
        this.sidemenuFlowHandler = sideMenuFlow.createHandler();
        StackPane rootSideMenu = this.sidemenuFlowHandler.start();
        this.appDrawer.setSidePane(rootSideMenu);
        initComponents();
    }

    @Override protected void initComponents () {
        this.btnToolbarOptionsBurger.getParent().setVisible(true);
        this.lblToolbar.setText(CEConstants.CE.App.Main.Strings.TITLE);
    }
}
