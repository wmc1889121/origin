package com.allen.statemachine.sm;

import com.allen.statemachine.common.AuthorizationException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Allen Wan
 * @version 1.0
 */
public class ChoiceSMTransaction implements SMTransaction {
    private final StateMachine stateMachine;
    private String role;
    @Getter
    private String src;
    @Getter
    private String event;
    private boolean hasLast;
    private List<SMTransaction> branches;

    public ChoiceSMTransaction(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
        this.branches = new ArrayList<>();
    }

    @Override
    public SMProcessResult process(SMEvent smEvent) {
        if (!Objects.equals(smEvent.getRole(), role)) {
            throw new AuthorizationException(smEvent.getRole(), role);
        }

        if (!Objects.equals(this.event, smEvent.getEvent())) {
            return new SMProcessResult(src);
        }

        SMTransaction branch = branches.stream().filter(tran -> tran.support(smEvent))
                .findFirst().orElse(null);

        if (branch != null) {
            return branch.process(smEvent);
        } else {
            throw new IllegalStateException("没有分支能够处理event:" + smEvent.toString());
        }
    }

    @Override
    public ChoiceSMTransaction role(String role) {
        this.role = role;
        return this;
    }

    @Override
    public ChoiceSMTransaction src(String src) {
        this.src = src;
        return this;
    }

    @Override
    public ChoiceSMTransaction event(String event) {
        this.event = event;
        return this;
    }

    @Override
    public boolean support(SMEvent event) {
        if (!Objects.equals(this.event, event.getEvent())) {
            return false;
        }
        for (SMTransaction tran : branches) {
            if (tran.support(event)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public StateMachine end() {
        if (!hasLast) {
            throw new IllegalStateException("缺少默认处理分支");
        }
        stateMachine.addTransaction(this);
        return stateMachine;
    }

    public ChoiceSMTransaction first(Predicate<SMEvent> guard, String target, SMAction<SMEvent> action) {
        SMTransaction branch = new DefaultSMTransaction(stateMachine)
                .role(role)
                .src(src).target(guard, target)
                .event(event)
                .action(action);
        branches.add(branch);
        return this;
    }

    public ChoiceSMTransaction last(String target, SMAction<SMEvent> action) {
        this.hasLast = true;

        SMTransaction branch = new DefaultSMTransaction(stateMachine)
                .role(role)
                .src(src).target(null, target)
                .event(event)
                .action(action);
        branches.add(branch);
        return this;
    }
}
