package com.car.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhengjie on 2020/5/6.
 */
@Data
public class EngineLatestVo {

    private Integer carId;

    private String engineId;

    private Date time;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private BigDecimal pm;

    private BigDecimal no;

    private BigDecimal co;

    private BigDecimal hc;

}
