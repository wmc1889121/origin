package com.allen.statemachine.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Data
public class SMConfigurerVO {
    private long tenantId;
    private List<TransactionVO> transactions;

    @Data
    public static class TransactionVO {
        private Long id;
        private int type; // 0 - default, 1 - choice, 2 - branch
        private String role;
        private String src;
        private String event;
        private List<Target> targets;
    }

    @Data
    public static class Target {
        private String guard;
        private String action;
        private String target;
    }
}
