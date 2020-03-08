package com.allen.origin.entity.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private long id;
    private String openId;
    private String avatar;
    private String name;
    private Sex sex;
    private String birthday;
    private long mobile;
    private String email;
    private String ability;
    private Location location = new Location();
    private int stars;
    private String certificate;
    private String selfDescription;
    private List<Education> educations;
    private List<Comment> comments;
    private long createTime;
    private long modifyTime;
}
