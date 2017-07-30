package com.casaelida.desktop.controllers.login;

import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Login;
import com.casaelida.desktop.utils.CEController;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import javafx.application.Platform;

import javax.annotation.PostConstruct;

/**
 * @author iqbal
 * @date 7/9/17
 */
@ViewController (value = "/fxml/login/sidemenu.fxml")
public class LoginSideMenuController extends CEController {
    @ViewNode (Login.SideMenu.ITEM_EXIT) @ActionTrigger (Login.SideMenu.Flow.EXIT) private JFXButton itemExit;

    @PostConstruct private void start () {
        initComponents();
    }

    @Override protected void initComponents () {

    }

    @ActionMethod (Login.SideMenu.Flow.EXIT) private void exit () {
        Platform.exit();
    }
}
