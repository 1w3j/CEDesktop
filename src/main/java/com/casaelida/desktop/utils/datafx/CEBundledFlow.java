package com.casaelida.desktop.utils.datafx;

import com.casaelida.desktop.utils.CEFunctions;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.context.ViewFlowContext;

import java.util.ResourceBundle;

/**
 * @author iqbal
 */
public class CEBundledFlow extends Flow {

    public CEBundledFlow (Class<?> startViewControllerClass, ResourceBundle bundle) {
        super(startViewControllerClass, CEFunctions.newBundledConfig(bundle));
    }

    @Override public CEFlowHandler createHandler (ViewFlowContext flowContext) {
        return new CEFlowHandler(this, flowContext, this.getViewConfiguration());
    }

    @Override public CEFlowHandler createHandler () {
        return new CEFlowHandler(this, new ViewFlowContext(), this.getViewConfiguration());
    }

}
