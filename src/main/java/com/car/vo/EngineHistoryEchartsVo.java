package com.car.vo;

import lombok.Data;

import java.math.BigDecimal;


/**
 * Created by zhengjie on 2020/5/15.
 * 返回汽车最新几次污染的对象
 */
@Data
public class EngineHistoryEchartsVo {


    private String time;

    private BigDecimal pm;

    private BigDecimal no;

    private BigDecimal co;

    private BigDecimal hc;


}
