package com.casaelida.desktop.utils;

import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.ViewFlowContext;

/**
 * @author iqbal
 * @date 7/9/17
 */
public class CEFlowHandler extends FlowHandler{

    CEFlowHandler (Flow flow, ViewFlowContext flowContext) {
        super(flow, flowContext);
    }

    CEFlowHandler (Flow flow, ViewFlowContext flowContext, ViewConfiguration viewConfiguration) {
        super(flow, flowContext, viewConfiguration);
    }

    @Override public <T> void registerInFlowContext(String key, T object) {
        super.getFlowContext().register(key, object);
    }

    @Override public <T> void registerInApplicationContext(String key, T object) {
        super.getFlowContext().getApplicationContext().register(key, object);
    }

}
