package com.ce.desktop.controllers.main;

import com.ce.desktop.utils.CEController;
import com.ce.desktop.utils.CEConstants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXScrollPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

import javax.annotation.PostConstruct;

/**
 * @author 1w3j
 * @date 7/11/17
 */
@ViewController (value = "/fxml/main/sidemenu.fxml")
public class MainSideMenuController extends CEController {
    @FXMLViewFlowContext private ViewFlowContext appFlowContext;
    private FlowActionHandler appActionHandler;//taken from LoginController (which is injected from the 'App' Flow, see LoginController.java or docs)

    @FXML private JFXButton itemLogOut;
    @FXML private JFXScrollPane mainSideMenuPane;
    @ViewNode(CEConstants.CE.App.Main.SideMenu.ITEM_HOME) private JFXButton itemHome;
    private JFXDrawer appDrawer;

    @PostConstruct private void start () {
        this.appActionHandler = (FlowActionHandler) this.appFlowContext.getApplicationContext().getRegisteredObject(CEConstants.CE.App.Flow.ACTION_HANDLER);
        this.appDrawer = (JFXDrawer) this.appFlowContext.getApplicationContext().getRegisteredObject(CEConstants.CE.App.DRAWER);
        initComponents();
    }

    @Override protected void initComponents () {
        JFXScrollPane.smoothScrolling((ScrollPane) this.mainSideMenuPane.getChildren().get(0));
        this.itemLogOut.setOnMouseClicked(e->{
            try {
                this.appFlowContext.getApplicationContext().register(CEConstants.CE.App.Animations.Flow.NEXT_ANIMATION, CEConstants.CE.App.Animations.LOG_OUT);
                this.appActionHandler.navigate(CEConstants.CE.App.Login.CLASS);
                this.appDrawer.close();
            } catch (VetoException | FlowException e1) {
                e1.printStackTrace();
            }
        });
    }
}
