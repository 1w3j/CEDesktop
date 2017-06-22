package com.casaelida.desktop.controllers;

import com.casaelida.desktop.utils.CEAnimatedFlowContainer;
import com.casaelida.desktop.utils.CEBundledFlow;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
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
 */
@ViewController(value="/fxml/app.fxml")
public class AppController {
    //DataFX Framework
    private FlowHandler appFlowHandler;//new flow handler
    //Current View Components
    @ViewNode(App.TOOLBAR) private JFXToolbar toolbar;
    @ViewNode(App.BTN_TOOLBAR_SIDEMENU_BURGER) private StackPane btnToolbarSidemenuBurger;
    @ViewNode(App.LBL_TOOLBAR) private Label lblToolbar;
    @ViewNode(App.BTN_TOOLBAR_OPTIONS_BURGER) private StackPane btnToolbarOptionsBurger;
    @ViewNode(App.DRAWER) private JFXDrawer appDrawer;
    
    @PostConstruct public void start() throws FlowException{
        //creating a new flow
        CEBundledFlow appFlow = new CEBundledFlow(App.Login.CLASS, App.Login.Strings.BUNDLE);
        ViewFlowContext appFlowContext = new ViewFlowContext();
        this.appFlowHandler = appFlow.createHandler(appFlowContext);
        
        appFlowContext.register(App.Animations.Flow.NEXT_ANIMATION, App.Animations.LOGIN_NEXT);
        appFlowContext.register(App.LBL_TOOLBAR, this.lblToolbar);
        
        StackPane rootLoginPane = this.appFlowHandler.start(new CEAnimatedFlowContainer());
        this.appDrawer.setContent(rootLoginPane);
        initComponents();
    }

    private void initComponents() {
        this.toolbar.setMinHeight(App.TOOLBAR_HEIGHT);
        this.toolbar.setPrefHeight(App.TOOLBAR_HEIGHT);
    }
}