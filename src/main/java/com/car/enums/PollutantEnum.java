package com.car.enums;

import lombok.Getter;

/**
 * Created by zhengjie on 2020/5/14.
 */
@Getter
public enum PollutantEnum {
    PM(1,"PM颗粒物排放"),
    CO(2,"一氧化碳排放"),
    NO(3,"氮氧化物排放"),
    HC(4,"碳氢化物排放"),
    ;

    Integer code;

    String desc;

    PollutantEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
