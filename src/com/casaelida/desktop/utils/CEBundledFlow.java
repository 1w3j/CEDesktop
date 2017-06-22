package com.casaelida.desktop.utils;

import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import java.util.ResourceBundle;

/**
 *
 * @author iqbal
 */
public class CEBundledFlow extends Flow {

    public CEBundledFlow(Class<?> startViewControllerClass, ResourceBundle bundle) {
        super(startViewControllerClass, CEFunctions.newBundledConfig(bundle));
    }

    @Override
    public FlowHandler createHandler(ViewFlowContext flowContext) {
        return new FlowHandler(this, flowContext, this.getViewConfiguration());
    }

}
