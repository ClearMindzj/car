package com.car.service.impl;

import com.car.utils.ShowAPIInfo;
import com.car.utils.URLConnectionAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by zhengjie on 2020/5/4.
 */
@Slf4j
public class TestOne {

    private static String url="https://restapi.amap.com/v3/geocode/regeo?location=118.7764,32.0572&key=69c6894b34f7c9229b8e30cf23c15828";

    private static String weatherUrl="https://free-api.heweather.net/s6/weather/now?location=%E5%8D%97%E4%BA%AC&key=4445451f9e5c4d88b6c9d60948ce4ea7";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Test
    public void Show(){

    }

    @Test
    public void Weather(){

    }

}
