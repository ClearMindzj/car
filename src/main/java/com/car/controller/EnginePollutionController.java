package com.car.controller;

import com.car.consts.Carconst;
import com.car.pojo.CarInfo;
import com.car.service.ICarInfoService;
import com.car.service.IEnginePollutionService;
import com.car.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by zhengjie on 2020/4/28.
 */
@RestController
@RequestMapping("/car")
public class EnginePollutionController {

    @Autowired
    private IEnginePollutionService iEnginePollutionService;

    @Autowired
    private ICarInfoService iCarInfoService;

    @GetMapping("/list")
    public ModelAndView listNewOne(Map<String,Object> map){
        ResponseVo<List<EngineLatestVo>> engineLatestVoList=iEnginePollutionService.findOneDate();
        map.put("engineLatestVoList",engineLatestVoList);
        return new ModelAndView("engineOne",map);
    }

    @GetMapping("/history")
    public ModelAndView listOneHistory(@RequestParam("carId")Integer carId, Map<String,Object> map){
        ResponseVo<List<EngineHistoryVo>> engineHistoryVoList =iEnginePollutionService.findOneAll(carId);
        CarInfo carInfo=iCarInfoService.findByCarId(carId);
        map.put("carId",carId);
        map.put("engineHistoryVoList",engineHistoryVoList);
        map.put("engineId",carInfo.getEngineId());
        return new ModelAndView("engineOneHistory") ;
    }

    @GetMapping("/detail")
    public ModelAndView listOneDetail(@RequestParam("carId") Integer carId, @RequestParam("time")String time,
                                      @RequestParam("longitude") String longitude,@RequestParam("latitude") String latitude,
                                      Map<String,Object> map){

        ResponseVo<List<EngineSpecificVo>> engineSpecificVoList=iEnginePollutionService.findSpecificDate(carId,time,longitude,latitude);
        CarInfo carInfo=iCarInfoService.findByCarId(carId);
        map.put("engineId",carInfo.getEngineId());
        map.put("carId",carId);
        map.put("time",time);
        map.put("engineSpecificVoList",engineSpecificVoList);
        return new ModelAndView("engineSpecific");
    }

    @GetMapping("/map")
    public ModelAndView showMapMarker(@RequestParam("longitude") String longitude,@RequestParam("latitude") String latitude,
                                      Map<String,Object> map){
        map.put("longitude",longitude);
        map.put("latitude",latitude);
        map.put("key", Carconst.LocationKey);
        return new ModelAndView("showMapMarker");
    }

    @GetMapping("/echart")
    public ResponseVo<List<EngineEchartsOneVo>> show(@RequestParam("carId") Integer carId, @RequestParam("time")String time){
        return iEnginePollutionService.findEchartsData(carId,time);
    }

    @GetMapping("/echarts")
    public ResponseVo<List<EngineHistoryEchartsVo>> showHisto(@RequestParam("carId") Integer carId){
       return iEnginePollutionService.findEchartsHistory(carId);
    }


}
