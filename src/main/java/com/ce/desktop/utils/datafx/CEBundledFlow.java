package com.ce.desktop.utils.datafx;

import com.ce.desktop.utils.CEFunctions;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.context.ViewFlowContext;

import java.util.ResourceBundle;

/**
 * Overrides the constructor that takes a {@link io.datafx.controller.ViewConfiguration} as the 2nd
 * parameter in order to eliminate boilerplate code (see {@link CEFunctions}'s <code>newBundledConfig</code> method).
 *
 * Finally, {@link Flow}'s <code>createHandler</code> method was changed to return a {@link CEFlowHandler} object
 *
 * @see CEFunctions
 * @see Flow
 * @see CEFlowHandler
 * @see io.datafx.controller.flow.FlowHandler
 * @see ResourceBundle
 * @see io.datafx.controller.ViewConfiguration
 * @author 1w3j
 */
public class CEBundledFlow extends Flow {

    public CEBundledFlow (Class<?> startViewControllerClass, ResourceBundle bundle) {
        super(startViewControllerClass, CEFunctions.newBundledConfig(bundle));
    }

    @Override public CEFlowHandler createHandler () {
        return new CEFlowHandler(this, new ViewFlowContext(), this.getViewConfiguration());
    }

}
