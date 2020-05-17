package com.car.service.impl;

import com.car.CarApplicationTests;
import com.car.pojo.CarInfo;
import com.car.service.ICarInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhengjie on 2020/5/13.
 */
@Slf4j
public class CarInfoServiceImplTest extends CarApplicationTests {

    @Autowired
    private ICarInfoService iCarInfoService;

    @Test
    public void findAll() {
        List<CarInfo> list=iCarInfoService.findAll();
        log.info("list={}" ,list);
    }
}