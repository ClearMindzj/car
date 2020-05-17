package com.car.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhengjie on 2020/5/6.
 */
@Data
public class EngineSpecificVo {

    private String engineId;

    private Date time;

    private String location;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String windDirection;

    private String windSpeed;

    private String temperature;

    private String pressure;

    private BigDecimal pm;

    private BigDecimal no;

    private BigDecimal co;

    private BigDecimal hc;
}
