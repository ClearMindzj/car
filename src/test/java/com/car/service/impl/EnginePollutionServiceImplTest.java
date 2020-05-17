package com.car.service.impl;

import com.car.CarApplicationTests;
import com.car.pojo.EngineInfo;
import com.car.service.IEnginePollutionService;
import com.car.utils.String2Date;
import com.car.vo.EngineHistoryVo;
import com.car.vo.EngineLatestVo;
import com.car.vo.EngineSpecificVo;
import com.car.vo.ResponseVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by zhengjie on 2020/4/28.
 */
@Slf4j
public class EnginePollutionServiceImplTest extends CarApplicationTests {

    @Autowired
    private IEnginePollutionService iEnginePollutionService;

    private  SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static Integer carId=1;

    private String time="2020-04-28 17:01:32";

    private static String longitude="118.7764";

    private static String latitude="32.0572";

    public EnginePollutionServiceImplTest() throws ParseException {
    }


    @Test
    public void findOneDate() {
         ResponseVo<List<EngineLatestVo>> info=iEnginePollutionService.findOneDate();
         log.info("info={}",info);
    }

    @Test
    public void findOneAll(){
        ResponseVo<List<EngineHistoryVo>> info=iEnginePollutionService.findOneAll(carId);
        log.info("info={}",info);
    }

    @Test
    public void  findSpecificDate(){
        ResponseVo<List<EngineSpecificVo>> info=iEnginePollutionService.findSpecificDate(carId,time,longitude,latitude);
        log.info("info={}",info);
    }


}