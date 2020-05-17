package com.car.service.impl;

import com.car.dao.CarInfoMapper;
import com.car.pojo.CarInfo;
import com.car.service.ICarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhengjie on 2020/5/13.
 */
@Service
public class CarInfoServiceImpl implements ICarInfoService {

    @Autowired
    private CarInfoMapper carInfoMapper;
    @Override
    public List<CarInfo> findAll() {
        return carInfoMapper.selectAll();
    }

    @Override
    public CarInfo findByCarId(Integer carId) {
        return carInfoMapper.selectByPrimaryKey(carId);
    }
}
