package com.casaelida.desktop.controllers.main;

import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
import com.casaelida.desktop.utils.CEConstants.CasaElida.App.Main;
import com.casaelida.desktop.utils.CEController;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.scene.control.Label;
import javax.annotation.PostConstruct;

/**
 *
 * @author iqbal
 */
@ViewController(value="/fxml/main/main.fxml")
public class MainController extends CEController{
    @FXMLViewFlowContext private ViewFlowContext appFlowContext;
    
    private Label lblToolbar;
    
    @PostConstruct private void start(){
        this.lblToolbar = (Label) this.appFlowContext.getRegisteredObject(App.LBL_TOOLBAR);
        
        initComponents();
    }

    @Override protected void initComponents() {
        this.lblToolbar.setText(Main.Strings.TITLE);
    }
}
