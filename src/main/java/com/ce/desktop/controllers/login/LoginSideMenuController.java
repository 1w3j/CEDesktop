package com.ce.desktop.controllers.login;

import com.ce.desktop.utils.CEController;
import com.ce.desktop.utils.CEConstants;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import javafx.application.Platform;

import javax.annotation.PostConstruct;

/**
 * @author 1w3j
 * @date 7/9/17
 */
@ViewController (value = "/fxml/login/sidemenu.fxml")
public class LoginSideMenuController extends CEController {
    @ViewNode (CEConstants.CE.App.Login.SideMenu.ITEM_EXIT) @ActionTrigger (CEConstants.CE.App.Login.SideMenu.Flow.EXIT) private JFXButton itemExit;

    @PostConstruct private void start () {
        initComponents();
    }

    @Override protected void initComponents () {

    }

    @ActionMethod (CEConstants.CE.App.Login.SideMenu.Flow.EXIT) private void exit () {
        Platform.exit();
    }
}
