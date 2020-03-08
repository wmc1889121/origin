package com.allen.origin.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.allen.origin.entity.domain.Education;
import com.allen.origin.utils.TimestampToDateConverter;
import com.hd123.rumba.commons.util.converter.Converter;
import com.hd123.rumba.commons.util.converter.ConverterBuilder;
import lombok.Data;

import java.util.Date;

@Data
public class EducationVO {
    public static final Converter<Education, EducationVO> TO_VO_CONVERTER = ConverterBuilder //
            .newBuilder(Education.class, EducationVO.class) //
            .map("begin", TimestampToDateConverter.getInstance())//
            .map("end", TimestampToDateConverter.getInstance())//
            .build();

    private long id;
    @JSONField(ordinal = 1)
    private long userId;
    @JSONField(ordinal = 2)
    private String college;
    @JSONField(ordinal = 3)
    private String major;
    @JSONField(ordinal = 4)
    private String degree;
    @JSONField(ordinal = 5, format = "yyyy")
    private Date begin;
    @JSONField(ordinal = 6, format = "yyyy")
    private Date end;
}
