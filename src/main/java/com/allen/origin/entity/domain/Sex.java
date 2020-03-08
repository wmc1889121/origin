package com.allen.origin.entity.domain;

import lombok.Getter;

import java.util.Objects;

public enum Sex {
    female("女"), male("男"), unknown("未知");

    @Getter
    private String nameZh;

    Sex(String nameZh) {
        this.nameZh = nameZh;
    }

    public static Sex valueOfZh(String nameZh) {
        for (Sex sex : Sex.values()) {
            if (Objects.equals(nameZh, sex.nameZh))
                return sex;
        }
        return null;
    }
}
