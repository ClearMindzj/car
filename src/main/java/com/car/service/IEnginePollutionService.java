package com.car.service;

import com.car.vo.*;

import java.util.List;

/**
 * Created by zhengjie on 2020/4/27.
 */
public interface IEnginePollutionService {
   //查询所有发动机最新的污染数据
   ResponseVo<List<EngineLatestVo>> findOneDate();

   //查询单个汽车发动机历史数据
   ResponseVo<List<EngineHistoryVo>> findOneAll(Integer carId);

   //查询单个汽车发动机具体信息
   ResponseVo<List<EngineSpecificVo>> findSpecificDate(Integer carId, String time,String longitude,String latitude);

   //单个汽车发动机信息显示在Echart
   ResponseVo<List<EngineEchartsOneVo>> findEchartsData(Integer carId, String time);

   //查询最新几次污染数据显示在Echart
   ResponseVo<List<EngineHistoryEchartsVo>> findEchartsHistory(Integer carId);
}
