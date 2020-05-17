package com.car.dao;

import com.car.pojo.CarInfo;

import java.util.List;

public interface CarInfoMapper {
    int deleteByPrimaryKey(Integer carId);

    int insert(CarInfo record);

    int insertSelective(CarInfo record);

    CarInfo selectByPrimaryKey(Integer carId);

    int updateByPrimaryKeySelective(CarInfo record);

    int updateByPrimaryKey(CarInfo record);

    List<CarInfo> selectAll();
}