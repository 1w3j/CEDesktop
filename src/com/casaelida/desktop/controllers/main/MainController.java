package com.casaelida.desktop.controllers.main;

import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Main;
import com.casaelida.desktop.utils.CEController;
import com.casaelida.desktop.utils.datafx.CEBundledFlow;
import com.casaelida.desktop.utils.datafx.CEFlowHandler;
import com.jfoenix.controls.JFXDrawer;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import javax.annotation.PostConstruct;

/**
 * @author iqbal
 */
@ViewController (value = "/fxml/main/main.fxml")
public class MainController extends CEController {
    @FXMLViewFlowContext private ViewFlowContext appFlowContext;
    private CEFlowHandler sidemenuFlowHandler;

    private JFXDrawer appDrawer;
    private Label lblToolbar;
    private StackPane btnToolbarOptionsBurger;

    @PostConstruct private void start () throws FlowException {
        this.appDrawer = (JFXDrawer) this.appFlowContext.getApplicationContext().getRegisteredObject(App.DRAWER);
        this.btnToolbarOptionsBurger = (StackPane) this.appFlowContext.getApplicationContext().getRegisteredObject(App.BTN_TOOLBAR_OPTIONS_BURGER);
        this.lblToolbar = (Label) this.appFlowContext.getRegisteredObject(App.LBL_TOOLBAR);

        CEBundledFlow sideMenuFlow = new CEBundledFlow(Main.SideMenu.CLASS, Main.SideMenu.Strings.BUNDLE);
        this.sidemenuFlowHandler = sideMenuFlow.createHandler();
        StackPane rootSideMenu = this.sidemenuFlowHandler.start();
        this.appDrawer.setSidePane(rootSideMenu);
        initComponents();
    }

    @Override protected void initComponents () {
        this.btnToolbarOptionsBurger.getParent().setVisible(true);
        this.lblToolbar.setText(Main.Strings.TITLE);
    }
}
