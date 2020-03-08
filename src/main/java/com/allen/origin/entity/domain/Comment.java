package com.allen.origin.entity.domain;

import lombok.Data;

@Data
public class Comment {
    private long id;
    private long user_id; // 被评论
    private long comment_by;
    private int stars;
    private String remark;
    private long createTime;
}
