package com.allen.statemachine.sm;

import lombok.Data;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Data
public class SMConfigurer {
    private long id;
    private long tenantId;
    private long pid = -1;
    private int type; // 0 - default, 1 - choice
    private String role;
    private String src;
    private String event;
    private String target;
    private String action;
    private String guard;
}
