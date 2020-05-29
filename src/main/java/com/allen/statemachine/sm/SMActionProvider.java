package com.allen.statemachine.sm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Component
public class SMActionProvider {

    @Autowired
    private Map<String, SMAction> actions;

    public SMAction<SMEvent> getAction(String action) {
        return actions.get(action == null ? "default" : action);
    }
}
