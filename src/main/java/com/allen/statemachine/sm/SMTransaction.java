package com.allen.statemachine.sm;

/**
 * @author Allen Wan
 * @version 1.0
 */
public interface SMTransaction {

    SMTransaction role(String role);

    SMTransaction src(String src);

    String getSrc();

    SMTransaction event(String event);

    String getEvent();

    SMProcessResult process(SMEvent event);

    boolean support(SMEvent event);

    StateMachine end();
}
