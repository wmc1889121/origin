package com.allen.statemachine.sm;

import com.allen.statemachine.common.AuthorizationException;
import lombok.Getter;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Allen Wan
 * @version 1.0
 */
public class DefaultSMTransaction implements SMTransaction {
    private final StateMachine stateMachine;
    private String role;
    @Getter
    private String src;
    @Getter
    private String event;
    private SMAction<SMEvent> action;
    private String target;
    private Predicate<SMEvent> guard;

    public DefaultSMTransaction(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public SMProcessResult process(SMEvent event) {
        Objects.requireNonNull(event);

        if (!Objects.equals(event.getRole(), role)) {
            throw new AuthorizationException(event.getRole(), role);
        }

        if (!support(event)) {
            return new SMProcessResult(src);
        }

        if (action != null) {
            event.setTargetState(target);
            action.accept(event);
        }

        return new SMProcessResult(target);
    }

    @Override
    public DefaultSMTransaction role(String role) {
        this.role = role;
        return this;
    }

    @Override
    public DefaultSMTransaction src(String src) {
        this.src = src;
        return this;
    }

    @Override
    public DefaultSMTransaction event(String event) {
        this.event = event;
        return this;
    }

    @Override
    public boolean support(SMEvent event) {
        return Objects.equals(this.event, event.getEvent()) && (guard == null || guard.test(event));
    }

    @Override
    public StateMachine end() {
        stateMachine.addTransaction(this);
        return stateMachine;
    }

    public DefaultSMTransaction target(Predicate<SMEvent> guard, String target) {
        this.guard = guard;
        this.target = target;
        return this;
    }

    public DefaultSMTransaction action(SMAction<SMEvent> action) {
        this.action = action;
        return this;
    }
}
