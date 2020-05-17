package com.car.service.impl;

import com.car.consts.Carconst;
import com.car.dao.EngineInfoMapper;
import com.car.enums.PollutantEnum;
import com.car.pojo.CarInfo;
import com.car.pojo.EngineInfo;
import com.car.service.ICarInfoService;
import com.car.service.IEnginePollutionService;
import com.car.utils.ShowAPIInfo;
import com.car.utils.String2Date;
import com.car.vo.*;
import com.google.gson.JsonObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhengjie on 2020/4/27.
 */
@Service
public class EnginePollutionServiceImpl implements IEnginePollutionService {

    @Autowired
    private EngineInfoMapper engineInfoMapper;
    @Autowired
    private ICarInfoService iCarInfoService;



    @Override
    public ResponseVo<List<EngineLatestVo>> findOneDate() {

        //查询汽车信息
        List<CarInfo> carInfoList = iCarInfoService.findAll();
        List<EngineInfo> engineInfoList = engineInfoMapper.selectByCarIdAndTime();
       /* List<EngineLatestVo> engineLatestVoList = engineInfoList.stream().map(e -> {
            EngineLatestVo engineLatestVo = new EngineLatestVo();
            BeanUtils.copyProperties(e, engineLatestVo);
            return engineLatestVo;
        }).collect(Collectors.toList());*/
       //返回前端需要的对象
       List<EngineLatestVo> engineLatestVoList=new ArrayList<>();
       for(EngineInfo engineInfo : engineInfoList){
           for ( CarInfo carInfo:carInfoList) {
               if(carInfo.getCarId().equals(engineInfo.getCarId())){
                   EngineLatestVo engineLatestVo=new EngineLatestVo();
                   BeanUtils.copyProperties(engineInfo,engineLatestVo);
                   BeanUtils.copyProperties(carInfo,engineLatestVo);
                   engineLatestVoList.add(engineLatestVo);
               }
           }

       }
        return ResponseVo.success(engineLatestVoList);
    }

    @Override
    public ResponseVo<List<EngineHistoryVo>> findOneAll(Integer carId) {

        List<EngineInfo> engineInfoList = engineInfoMapper.selectByPrimaryKey(carId);
        List<EngineHistoryVo> engineHistoryVoList = engineInfoList.stream().map(e -> {
            EngineHistoryVo engineHistoryVo = new EngineHistoryVo();
            BeanUtils.copyProperties(e, engineHistoryVo);
            return engineHistoryVo;
        }).collect(Collectors.toList());
        return ResponseVo.success(engineHistoryVoList);
    }

    @Override
    public ResponseVo<List<EngineSpecificVo>> findSpecificDate(Integer carId, String time, String longitude, String latitude) {
        Date date = null;
        try {
            date = String2Date.StringToTime(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EngineInfo engineInfo = engineInfoMapper.selectByIdAndTime(carId, date);
        //EngineId要去Car表中去拿
        CarInfo carInfo=iCarInfoService.findByCarId(carId);

        EngineSpecificVo engineSpecificVo = new EngineSpecificVo();
        engineSpecificVo.setEngineId(carInfo.getEngineId());
        BeanUtils.copyProperties(engineInfo, engineSpecificVo);
        String locationUrl = Carconst.LocationURL + "location=" + longitude + "," + latitude + "&" + "key=" + Carconst.LocationKey;
        JsonObject addressObject = ShowAPIInfo.Location(locationUrl);
        String location = ShowAPIInfo.specificLocation(addressObject);
        //天气API参数
        String city = ShowAPIInfo.cityLocation(addressObject);
        String weatherUrl = Carconst.WeatherURL + "location=" + city + "&" + "key=" + Carconst.WeatherKey;
        JsonObject nowObject = ShowAPIInfo.getNow(weatherUrl);
        String windDirection = ShowAPIInfo.getWindDirection(nowObject);
        String windSpeed = ShowAPIInfo.getWindSpeed(nowObject);
        String temperature = ShowAPIInfo.getTemperature(nowObject);
        String pressure = ShowAPIInfo.getPressure(nowObject);
        //设置到对象中
        engineSpecificVo.setLocation(location);
        engineSpecificVo.setWindDirection(windDirection);
        engineSpecificVo.setWindSpeed(windSpeed);
        engineSpecificVo.setTemperature(temperature);
        engineSpecificVo.setPressure(pressure);
        List<EngineSpecificVo> engineSpecificVoList = new ArrayList<>();
        engineSpecificVoList.add(engineSpecificVo);
        return ResponseVo.success(engineSpecificVoList);
    }

    @Override
    public ResponseVo<List<EngineEchartsOneVo>> findEchartsData(Integer carId, String time) {
        Date date = null;
        try {
            date = String2Date.StringToTime(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EngineInfo engineInfo = engineInfoMapper.selectByIdAndTime(carId, date);
        List<EngineEchartsOneVo> engineEchartsOneVoList =new ArrayList<>();
        engineEchartsOneVoList.add(new EngineEchartsOneVo(PollutantEnum.PM.getDesc(),engineInfo.getPm()));
        engineEchartsOneVoList.add(new EngineEchartsOneVo(PollutantEnum.CO.getDesc(),engineInfo.getCo()));
        engineEchartsOneVoList.add(new EngineEchartsOneVo(PollutantEnum.NO.getDesc(),engineInfo.getNo()));
        engineEchartsOneVoList.add(new EngineEchartsOneVo(PollutantEnum.HC.getDesc(),engineInfo.getHc()));
        return ResponseVo.success(engineEchartsOneVoList);
    }

    @Override
    public ResponseVo<List<EngineHistoryEchartsVo>> findEchartsHistory(Integer carId) {
        List<EngineInfo> engineInfoList=engineInfoMapper.selectByPrimaryKeyLimit(carId);
        List<EngineHistoryEchartsVo>  engineHistoryEchartsVoList=new ArrayList<>();
        for(EngineInfo engineInfo:engineInfoList){
            EngineHistoryEchartsVo engineHistoryEchartsVo=new EngineHistoryEchartsVo();
            BeanUtils.copyProperties(engineInfo,engineHistoryEchartsVo);
            String time=String2Date.DateToString(engineInfo.getTime());
            engineHistoryEchartsVo.setTime(time);
            engineHistoryEchartsVoList.add(engineHistoryEchartsVo);
        }

        return ResponseVo.success(engineHistoryEchartsVoList);
    }


}
