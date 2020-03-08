package com.allen.origin.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.allen.origin.entity.domain.Location;
import com.allen.origin.entity.domain.Sex;
import com.allen.origin.entity.domain.User;
import com.hd123.rumba.commons.util.converter.ArrayListConverter;
import com.hd123.rumba.commons.util.converter.Converter;
import com.hd123.rumba.commons.util.converter.ConverterBuilder;
import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    public static final Converter<User, UserVO> TO_VO_CONVERTER = ConverterBuilder //
            .newBuilder(User.class, UserVO.class) //
            .map("educations", ArrayListConverter.newConverter(EducationVO.TO_VO_CONVERTER))//
            .map("comments", ArrayListConverter.newConverter(CommentVO.TO_VO_CONVERTER))//
            .build();

    private long id;
    @JSONField(ordinal = 1)
    private String openId;
    @JSONField(ordinal = 2)
    private String avatar;
    @JSONField(ordinal = 3)
    private String name;
    @JSONField(ordinal = 4)
    private Sex sex;
    @JSONField(ordinal = 5)
    private String birthday;
    @JSONField(ordinal = 6)
    private long mobile;
    @JSONField(ordinal = 7)
    private String email;
    @JSONField(ordinal = 8)
    private String ability;
    @JSONField(ordinal = 9)
    private Location location = new Location();
    @JSONField(ordinal = 10)
    private int stars;
    @JSONField(ordinal = 11)
    private String certificate;
    @JSONField(ordinal = 12)
    private String selfDescription;
    @JSONField(ordinal = 13)
    private List<EducationVO> educations;
    @JSONField(ordinal = 14)
    private List<CommentVO> comments;
}
