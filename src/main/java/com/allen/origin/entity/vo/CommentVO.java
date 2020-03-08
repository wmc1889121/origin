package com.allen.origin.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.allen.origin.entity.domain.Comment;
import com.allen.origin.utils.TimestampToDateConverter;
import com.hd123.rumba.commons.util.converter.Converter;
import com.hd123.rumba.commons.util.converter.ConverterBuilder;
import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    public static final Converter<Comment, CommentVO> TO_VO_CONVERTER = ConverterBuilder //
            .newBuilder(Comment.class, CommentVO.class) //
            .map("createTime", TimestampToDateConverter.getInstance())//
            .build();

    private long id;
    @JSONField(ordinal = 1)
    private long user_id; // 被评论
    @JSONField(ordinal = 2)
    private long comment_by;
    @JSONField(ordinal = 3)
    private int stars;
    @JSONField(ordinal = 4)
    private String remark;
    @JSONField(ordinal = 5, format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
