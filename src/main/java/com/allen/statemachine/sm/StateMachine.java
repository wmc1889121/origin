package com.allen.statemachine.sm;

/**
 * @author Allen Wan
 * @version 1.0
 */
public interface StateMachine {

    long getTenant();

    SMProcessResult onEvent(String state, SMEvent event);

    DefaultSMTransaction withTransaction();

    ChoiceSMTransaction withChoice();

    void addTransaction(SMTransaction transaction);
}
