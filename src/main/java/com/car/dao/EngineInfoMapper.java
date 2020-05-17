package com.car.dao;

import com.car.pojo.EngineInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EngineInfoMapper {
    int deleteByPrimaryKey(Integer eid);

    int insert(EngineInfo record);

    int insertSelective(EngineInfo record);

    List<EngineInfo> selectByPrimaryKey(Integer carId);

    List<EngineInfo> selectByPrimaryKeyLimit(Integer carId);

    int updateByPrimaryKeySelective(EngineInfo record);

    int updateByPrimaryKey(EngineInfo record);


    List<EngineInfo>selectByCarIdAndTime();

    EngineInfo selectByIdAndTime(@Param("carId")Integer carId, @Param("time") Date time);
}