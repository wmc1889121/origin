package com.allen.statemachine.common;

/**
 * @author Allen Wan
 * @version 1.0
 */
public class AuthorizationException extends RuntimeException {
    private final String operatorRole;
    private final String needRole;

    public AuthorizationException(String operatorRole, String needRole) {
        super("当前角色：" + operatorRole + ", 合法角色：" + needRole);
        this.operatorRole = operatorRole;
        this.needRole = needRole;
    }
}
