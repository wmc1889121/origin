package com.allen.statemachine.sm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Component
public class SMGuardProvider {
    @Autowired
    private Map<String, Predicate<SMEvent>> guards;

    public Predicate<SMEvent> getGuard(String guard) {
        return guards.get(guard);
    }
}
