package com.car.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhengjie on 2020/5/14.
 */
@Data
public class EngineEchartsOneVo {

    private String name;

    private BigDecimal num;

    public EngineEchartsOneVo(String name, BigDecimal num) {
        this.name = name;
        this.num = num;
    }
}
