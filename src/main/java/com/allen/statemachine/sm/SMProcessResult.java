package com.allen.statemachine.sm;

import lombok.Data;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Data
public class SMProcessResult {
    private final String newState;

    private final SMEvent event;

    public SMProcessResult(String newState) {
        this.newState = newState;
        this.event = null;
    }

    public SMProcessResult(String newState, SMEvent event) {
        this.newState = newState;
        this.event = event;
    }
}
