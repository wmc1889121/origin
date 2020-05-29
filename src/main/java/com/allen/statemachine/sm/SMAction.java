package com.allen.statemachine.sm;

/**
 * @author Allen Wan
 * @version 1.0
 */
public interface SMAction<T> {

    void accept(SMEvent<T> event);
}
