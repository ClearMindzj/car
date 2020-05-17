package com.car.service;

import com.car.pojo.CarInfo;

import java.util.List;

/**
 * Created by zhengjie on 2020/5/13.
 */
public interface ICarInfoService {
    //查询所有汽车信息
    List<CarInfo>  findAll();

    //查询单个汽车信息
    CarInfo  findByCarId(Integer carId);
}
