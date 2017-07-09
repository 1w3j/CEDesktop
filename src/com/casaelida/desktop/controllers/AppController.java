package com.casaelida.desktop.controllers;

import com.casaelida.desktop.utils.CEAnimatedFlowContainer;
import com.casaelida.desktop.utils.CEBundledFlow;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
import com.casaelida.desktop.utils.CEController;
import com.casaelida.desktop.utils.CEFlowHandler;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXToolbar;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javax.annotation.PostConstruct;

/**
 *
 * @author iqbal
 * App contains Login
 */
@ViewController(value="/fxml/app.fxml")
public class AppController extends CEController {
    //DataFX Framework
    private CEFlowHandler appFlowHandler;//new flow handler
    //Current View Components
    @ViewNode(App.TOOLBAR) private JFXToolbar toolbar;
    @ViewNode(App.BTN_TOOLBAR_SIDEMENU_BURGER) private StackPane btnToolbarSidemenuBurger;
    @ViewNode(App.LBL_TOOLBAR) private Label lblToolbar;
    @ViewNode(App.BTN_TOOLBAR_OPTIONS_BURGER) private StackPane btnToolbarOptionsBurger;
    @ViewNode(App.DRAWER) private JFXDrawer appDrawer;
    
    @PostConstruct public void start() throws FlowException{
        //creating a new flow
        CEBundledFlow appFlow = new CEBundledFlow(App.Login.CLASS, App.Login.Strings.BUNDLE);
        this.appFlowHandler = appFlow.createHandler();
        this.appFlowHandler.registerInApplicationContext(App.BTN_TOOLBAR_OPTIONS_BURGER, this.btnToolbarOptionsBurger);
        this.appFlowHandler.registerInApplicationContext(App.BTN_TOOLBAR_SIDEMENU_BURGER, this.btnToolbarSidemenuBurger);
        this.appFlowHandler.registerInFlowContext(App.Animations.Flow.NEXT_ANIMATION, App.Animations.LOGIN_NEXT);
        this.appFlowHandler.registerInFlowContext(App.LBL_TOOLBAR, this.lblToolbar);

        StackPane rootLoginPane = this.appFlowHandler.start(new CEAnimatedFlowContainer());
        this.appDrawer.setContent(rootLoginPane);
        initComponents();
    }

    @Override protected void initComponents () {
        this.toolbar.setMinHeight(App.TOOLBAR_HEIGHT);
        this.toolbar.setPrefHeight(App.TOOLBAR_HEIGHT);
    }
}