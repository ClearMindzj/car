package com.car.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EngineInfo {
    private Integer eid;

    private Integer carId;

    private BigDecimal pm;

    private BigDecimal co;

    private BigDecimal no;

    private BigDecimal hc;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date time;


}