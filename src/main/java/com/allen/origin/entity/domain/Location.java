package com.allen.origin.entity.domain;

import lombok.Data;

@Data
public class Location {
    private Location parent;
    private Integer id;
    private Integer level; // 0 - 国家，1 - 省， 2 - 市
    private String name;
}
