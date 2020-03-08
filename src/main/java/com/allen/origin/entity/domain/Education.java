package com.allen.origin.entity.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Education {
    private long id;
    private long userId;
    private String college;
    private String major;
    private String degree;
    private long begin;
    private long end;
}
