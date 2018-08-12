package com.ce.desktop.controllers;

import com.ce.desktop.utils.CEController;
import com.ce.desktop.utils.CEFunctions;
import com.ce.desktop.utils.datafx.CEAnimatedFlowContainer;
import com.ce.desktop.utils.datafx.CEBundledFlow;
import com.ce.desktop.utils.datafx.CEFlowHandler;
import com.ce.desktop.utils.CEConstants;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXToolbar;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;

import javax.annotation.PostConstruct;

/**
 * @author 1w3j
 * App contains Login
 */
@ViewController (value = "/fxml/app.fxml")
public class AppController extends CEController {
    //DataFX Framework
    //new flow handler
    private CEFlowHandler appFlowHandler;
    //Current View Components
    @ViewNode (CEConstants.CE.App.TOOLBAR) private JFXToolbar toolbar;
    @ViewNode (CEConstants.CE.App.BTN_TOOLBAR_SIDEMENU_BURGER) private StackPane btnToolbarSidemenuBurger;
    @ViewNode (CEConstants.CE.App.LBL_TOOLBAR) private Label lblToolbar;
    @ViewNode (CEConstants.CE.App.BTN_TOOLBAR_OPTIONS_BURGER) private StackPane btnToolbarOptionsBurger;
    @ViewNode (CEConstants.CE.App.DRAWER) private JFXDrawer appDrawer;
    @ViewNode (CEConstants.CE.App.ROOT) private StackPane appRoot;

    @PostConstruct
    public void start () throws FlowException {
        //creating the "main/global" flow
        CEBundledFlow appFlow = new CEBundledFlow(CEConstants.CE.App.Login.CLASS, CEConstants.CE.App.Login.Strings.BUNDLE);
        this.appFlowHandler = appFlow.createHandler();
        this.appFlowHandler.registerInApplicationContext(CEConstants.CE.App.BTN_TOOLBAR_OPTIONS_BURGER, this.btnToolbarOptionsBurger);
        this.appFlowHandler.registerInApplicationContext(CEConstants.CE.App.BTN_TOOLBAR_SIDEMENU_BURGER, this.btnToolbarSidemenuBurger);
        this.appFlowHandler.registerInApplicationContext(CEConstants.CE.App.DRAWER, this.appDrawer);
        this.appFlowHandler.registerInApplicationContext(CEConstants.CE.App.Animations.Flow.NEXT_ANIMATION, CEConstants.CE.App.Animations.LOGIN_NEXT);
        this.appFlowHandler.registerInFlowContext(CEConstants.CE.App.LBL_TOOLBAR, this.lblToolbar);

        StackPane rootLoginPane = this.appFlowHandler.start(new CEAnimatedFlowContainer());

        this.appRoot.getChildren().setAll(rootLoginPane);
        initComponents();
    }

    @Override
    protected void initComponents () {
        this.toolbar.setMinHeight(CEConstants.CE.App.TOOLBAR_HEIGHT);
        this.toolbar.setPrefHeight(CEConstants.CE.App.TOOLBAR_HEIGHT);
        Platform.runLater(this::initKeyShortcuts);
    }

    private void initKeyShortcuts(){
        Scene scene = (Scene) this.appFlowHandler.getFlowContext().getApplicationContext().getRegisteredObject(CEConstants.CE.SCENE);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.SPACE, KeyCombination.SHORTCUT_DOWN), ()-> CEFunctions.toggleDrawer(this.appDrawer));
    }
}